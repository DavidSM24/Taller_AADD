package project.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.ExchangeGift;
import project.models.Gift;
import project.repositories.CloudinaryRepository;
import project.repositories.GiftRepository;

@Service
public class GiftService {
	// log4j2
	private static final Logger logger = LoggerFactory.getLogger(Gift.class);

	/**
	 * Repositorio del regalo asociado a este servicio
	 */
	@Autowired
	GiftRepository repository;

	/**
	 * M�todo que devuelve todos los regalos.
	 * 
	 * @return lista de regalos.
	 */
	public List<Gift> getAll() {
		logger.info("Petici�n realizada correctamente");

		return repository.findAll();
	}

	/**
	 * Devuelve todas los regalos paginados empezando en la pagina recibida.
	 * 
	 * @param element n� de elementos a buscar
	 * @param page    pagina por la que se comienza la paginaci�n.
	 * @return lista de regalos paginada.
	 * @throws ServiceException
	 */
	public List<Gift> getAllPaged(int element, int page) throws ServiceException {
		if (element > 0 && page > -1) {
			logger.info("Petici�n realizada correctamente");

			return repository.getAllPaged(element, page);

		} else {
			logger.error("El n�mero de elementos pedido no es v�lido");

			throw new ServiceException("El n�mero de elementos pedido no es v�lido");
		}
	}

	/***
	 * M�todo para conseguir un regalotir a partir de su id. Recibe un Long.
	 * Posibilidad de dar una excepci�n NotFound.
	 *
	 *
	 * @param id
	 * @return el regalo con ese id
	 * @throws RecordNotFoundException
	 */
	public Gift getById(Long id) throws RecordNotFoundException {
		if (id != null) {

			Optional<Gift> result = repository.findById(id);

			if (result.isPresent()) {
				logger.info("Petici�n realizada correctamente");

				return result.get();

			} else {
				logger.error("El regalo con la id " + id + " no existe");

				throw new RecordNotFoundException("El regalo no existe", id);
			}

		} else {
			logger.error("La id introducida no es v�lida");

			throw new RecordNotFoundException("La id introducida no es v�lida");
		}

	}

	/**
	 * Devuelve una lista de regalos filtrada por el nombre recibido y paginada
	 * comenzando por la pagina recivida.
	 * 
	 * @param name    nombre con el que se filtrar� la b�squeda.
	 * @param element n� de elementos a buscar
	 * @param page    pagina por la que comienza la paginaci�n de la lista.
	 * @return lista de regalos filtrada por nombre y paginada.
	 * @throws ServiceException
	 */
	public List<Gift> getByNamePaged(String name, int element, int page) throws ServiceException {
		if (name != null && !name.equals("")) {
			if (element > 0 && page > -1) {
				logger.info("Petici�n realizada correctamente");

				return repository.getByNamePaged(name, element, page);

			} else {
				logger.error("El n�mero de elementos pedido no es v�lido");

				throw new ServiceException("El n�mero de elementos pedido no es v�lido");
			}

		} else {
			logger.error("El nombre introducido no es v�lido");

			throw new ServiceException("El nombre introducido no es v�lido");
		}
	}

	/**
	 * Devuelve una lista de regalos paginada filtrada por la disponibilidad actual,
	 * comenzando por la pagina recivida.
	 * 
	 * @param avaliable disponibilidad para filtrar la b�squeda.
	 * @param page      p�gina para empezar la paginaci�n en la b�squeda.
	 * @return lista de regalos paginada y filtrada por nombre
	 * @throws ServiceException
	 */
	public List<Gift> getByAvaliablePaged(boolean avaliable, int element, int page) throws ServiceException {
		if (element > 0 && page > -1) {
			logger.info("Petici�n realizada correctamente");

			return repository.getByAvaliablePaged(avaliable, element, page);

		} else {
			logger.error("El n�mero de elementos pedido no es v�lido");

			throw new ServiceException("El n�mero de elementos pedido no es v�lido");
		}
	}

	/***
	 * M�todo para insertar o actualizar un regalo dependiendo de si existe un
	 * registro con este id en la BBDD. Lanza una excepci�n si no se encuentra el
	 * regalo en la BBDD.
	 * 
	 * @param Gift: El regalo a actualizar/insertar.
	 * @return Devuelve el regalo con el id generado.
	 * @throws RecordNotFoundException
	 * @throws ServiceException
	 */
	public Gift createOrUpdate(Gift gift, MultipartFile file) throws RecordNotFoundException, ServiceException {
		if (gift != null) {
			if (gift.getId() != null && gift.getId() > 0) {
				Optional<Gift> a = repository.findById(gift.getId());

				if (a.isPresent()) { // update
					Gift newGift = a.get();

					CloudinaryRepository.delete(newGift.getPicture()); // elimina la anterior foto.

					newGift.setId(gift.getId());
					newGift.setName(gift.getName());
					newGift.setPoints(gift.getPoints());
					newGift.setAvailable(gift.isAvailable());
					newGift.setPicture(CloudinaryRepository.upload(file, newGift)); // crea una nueva.
					newGift.setExchangeGifts(gift.getExchangeGifts());

					newGift = repository.save(newGift);
					logger.info("Petici�n realizada correctamente");

					return newGift;

				} else { // insert
					gift.setId(null);
					gift.setPicture(CloudinaryRepository.upload(file, gift));
					gift = repository.save(gift);
					logger.info("Petici�n realizada correctamente");

				}
			} else {
				gift.setPicture(CloudinaryRepository.upload(file, gift));
				gift = repository.save(gift);
				logger.info("Petici�n realizada correctamente");

				return gift;
			}

		} else {
			logger.error("El regalo introducido es nulo");
			throw new ServiceException("El regalo introducido es nulo");
			
		}
		return null;
	}

	/***
	 * M�todo que recibe un regalo y lo elimina de la BBDD. Lanza una excepci�n si
	 * no se encuentra el regalo en la BBDD.
	 * 
	 * @param: Gift El regalo a eliminar.
	 * @throws RecordNotFoundException
	 * @throws ServiceException
	 */
	public void delete(Gift gift) throws RecordNotFoundException, ServiceException {
		if (gift != null) {
			if (gift.getId() != null) {
				if (gift.getId() > 0) {
					Optional<Gift> optional = repository.findById(gift.getId());

					if (optional.isPresent()) {
						CloudinaryRepository.delete(gift.getPicture());

						logger.info("Petici�n realizada correctamente");

						repository.deleteById(gift.getId());
					} else {
						logger.error("El regaloc on la id" + gift.getId() + " no existe");

						throw new RecordNotFoundException("El regalo no existe", gift.getId());
					}

				} else {
					logger.error("El id introducido es nulo");

					throw new ServiceException("El id introducido es nulo");
				}

			} else {
				logger.error("El id introducido no es v�lido");

				throw new RecordNotFoundException("El id introducido es invalido", gift.getId());
			}
		} else {
			logger.error("El regalo introducido es nulo");

			throw new ServiceException("El regalo introducido es nulo");
		}

	}
}
