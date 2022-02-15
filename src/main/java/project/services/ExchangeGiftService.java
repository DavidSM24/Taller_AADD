package project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.Agency;
import project.models.ExchangeGift;
import project.repositories.ExchangeGiftRepository;

@Service
public class ExchangeGiftService {
	/**
	 * Repositorio de el regalo intercambiado asociado a este servicio.
	 */
	@Autowired
	ExchangeGiftRepository repository;

	/**
	 * M�todo que devuelve todos los regalos intercambiado.
	 * 
	 * @return lista de los regalos intercambiado
	 */
	public List<ExchangeGift> getAll() {
		return repository.findAll();
	}

	/***
	 * M�todo para conseguir un regalo intercambiado a partir de su id. Recibe un
	 * Long. Posibilidad de dar una excepci�n NotFound.
	 *
	 * @param id
	 * @return el regalo intercambiado con ese id
	 * @throws RecordNotFoundException
	 */
	public ExchangeGift getbyId(Long id) {
		if (id != null) {
			if (id > -1) {

				Optional<ExchangeGift> result = repository.findById(id);

				if (result.isPresent()) {

					return result.get();

				} else {
					throw new RecordNotFoundException("ExchangeGift no existe", id);
				}

			} else {
				throw new RecordNotFoundException("El id introducido no es valido");
			}
		} else {
			throw new RecordNotFoundException("El id introducido es nulo", id);
		}

	}

	/***
	 * M�todo para insertar o actualizar un regalo intercambiado dependiendo de si
	 * existe un registro con este id en la BBDD. Lanza una excepci�n si no se
	 * encuentra al regalo intercambiado en la BBDD.
	 * 
	 * @param Agency: El regalo intercambiado a actualizar/insertar.
	 * @return Devuelve el regalo intercambiado con el id generado.
	 * @throws ServiceException
	 * @throws RecordNotFoundException
	 */
	public ExchangeGift createorupdate(ExchangeGift exgift) throws ServiceException {

		if (exgift != null) {
			if (exgift.getDateExchange() != null && exgift.getAgency() != null && exgift.getGift() != null) {
				if (exgift.getId() != null && exgift.getId() > 0) {
					
					Optional<ExchangeGift> e = repository.findById(exgift.getId());

					if (e.isPresent()) { // update
						ExchangeGift newExchange = e.get();

						newExchange.setId(exgift.getId());
						newExchange.setDateExchange(exgift.getDateExchange());
						newExchange.setObservations(exgift.getObservations());
						newExchange.setDelivered(exgift.isDelivered());
						newExchange.setAgency(exgift.getAgency());
						newExchange.setGift(exgift.getGift());
						newExchange = repository.save(newExchange);
						
						return newExchange;

					} else { // insert
						exgift.setId(null);
						exgift = repository.save(exgift);
						
						return exgift;
					}

				}

				else {
					exgift = repository.save(exgift);
					
					return exgift;
				}

			} else {
				throw new ServiceException("Algo ha fallado, buscate la vida");
			}
		} else {
			throw new ServiceException("El pedido es nulo");
		}

	}

	/***
	 * M�todo que recibe un regalo intercambiado y la elimina de la BBDD. Lanza una
	 * excepci�n si no se encuentra el regalo intercambiado en la BBDD.
	 * 
	 * @param Agency: El regalo intercambiado a eliminar.
	 * @return Devuelve true si el regalo intercambiado se ha borrado False si no.
	 * @throws RecordNotFoundException
	 * @throws ServiceException 
	 */
	public boolean delete(ExchangeGift gift) throws RecordNotFoundException, ServiceException {
		
		boolean result = false;
		
		if(gift!=null) {
			if(gift.getId()!=null&&gift.getId()>-1) {
				
				Optional<ExchangeGift> optional = repository.findById(gift.getId());
				
				if (optional.isPresent()) {
					repository.deleteById(gift.getId());
					result = true;
				} else {
					result = false;
					throw new RecordNotFoundException("El regalo intercambiado no existe", gift.getId());
				}
				
			}else {
				throw new RecordNotFoundException("El id introducido no es v�lido",gift.getId());
			}
		}else {
			throw new ServiceException("El pedido introducido es nulo");
		}
				
		return result;
	}

	/**
	 * Devuelve una lista de regalos intercambiados paginada en funci�n de la p�gina
	 * que se est� buscando.
	 * 
	 * @param element n� de elementos a buscar
	 * @param page    n� de p�gina a partir del cual buscar.
	 * @return Una lista de regalos intercambiados intercambiados.
	 * @throws ServiceException 
	 */
	public List<ExchangeGift> getAllPaged(int element, int page) throws ServiceException {
		if(element>-1&&page>-1) {
			
			return repository.getAllPaged(element, page);
			
		}else {
			throw new ServiceException("El n�mero de elementos introducido no es correcto");
		}
	}

	/**
	 * Devuelve todos los regalos intercambiados que coincidan con el par�metro
	 * isDelivered paginadas, pudiendo ser los que est�n o no enviados en funci�n de
	 * lo que se reciba.
	 * 
	 * @param isdelivered boolean con el par�metro para filtrar.
	 * @param element     n� de elementos a buscar
	 * @param page        pagina por la que se empieza a paginar
	 * @return Lista de regalos intercambiados paginados y flitrados por
	 *         isDelivered.
	 * @throws ServiceException 
	 */
	public List<ExchangeGift> getByDeliveredPaged(boolean isdelivered, int element, int page) throws ServiceException {
		if(element>-1&&page>-1) {
			
			return repository.getByDeliveredPaged(isdelivered, element, page);
			
		}else {
			throw new ServiceException("El n�mero de elementos introducido no es correcto");
		}
	}

	/**
	 * Devuelve una lista paginada de regalos intercambiados cuyo id de agencia
	 * contenga el parametro agency.
	 * 
	 * @param agency  el id de la agencia.
	 * @param element n� de elementos a buscar
	 * @param page    comienzo de la paginaci�n.
	 * @return La lista paginada y filtrada de regalos intercambiados.
	 * @throws ServiceException 
	 */
	public List<ExchangeGift> getByAgencyPaged(int agency, int element, int page) throws ServiceException {
		if(agency>-1) {
			if(element>-1&&page>-1) {
				
				return repository.getByAgencyPaged(agency, element, page);
				
			}else {
				throw new ServiceException("El n�mero de elementos introducido no es correcto");
			}
			
		}else {
			throw new ServiceException("El id de la agencia es incorrecto");
		}
	}
}
