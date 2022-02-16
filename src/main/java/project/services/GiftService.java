package project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.Gift;
import project.repositories.GiftRepository;

@Service
public class GiftService {
	
	@Autowired
	GiftRepository repository;

	/**
	 * Método que devuelve todos los regalos.
	 * 
	 * @return lista de regalos.
	 */
	public List<Gift> getAll() {
		return repository.findAll();
	}
	
	/**
	 * Devuelve todas los regalos paginados empezando en la pagina recibida.
	 * 
	 * @param element nº de elementos a buscar
	 * @param page pagina por la que se comienza la paginación.
	 * @return lista de regalos paginada.
	 * @throws ServiceException 
	 */
	public List<Gift> getAllPaged(int element, int page) throws ServiceException {
		if(element>0&&page>-1) {
			
			return repository.getAllPaged(element, page);

		}else {
			throw new ServiceException("El número de elementos pedido no es válido");
		}
	}
	
	/***
	 * Método para conseguir un regalotir a partir de su id. Recibe un Long.
	 * Posibilidad de dar una excepción NotFound.
	 *
	 *
	 * @param id
	 * @return el regalo con ese id
	 * @throws RecordNotFoundException
	 */
	public Gift getById(Long id) throws RecordNotFoundException {
		if(id!=null) {
			
			Optional<Gift> result = repository.findById(id);
			
			if (result.isPresent()) {
				
				return result.get();
				
			} else {
				throw new RecordNotFoundException("El regalo no existe", id);
			}
			
		}else {
			throw new RecordNotFoundException("La id introducida no es válida");
		}
		
	}

	/**
	 * Devuelve una lista de regalos filtrada por el nombre recibido y paginada comenzando por
	 * la pagina recivida.
	 * 
	 * @param name nombre con el que se filtrará la búsqueda.
	 * @param element nº de elementos a buscar
	 * @param page pagina por la que comienza la paginación de la lista.
	 * @return lista de regalos filtrada por nombre y paginada.
	 * @throws ServiceException 
	 */
	public List<Gift> getByNamePaged(String name, int element, int page) throws ServiceException {
		if(name!=null&&!name.equals("")) {
			if(element>0&&page>0) {
				
				return repository.getByNamePaged(name, element, page);

			}else {
				throw new ServiceException("El número de elementos pedido no es válido");
			}
			
		}else {
			throw new ServiceException("El nombre introducido no es válido");
		}
	}
	
	/**
	 * Devuelve una lista de regalos paginada filtrada por la disponibilidad actual, comenzando
	 * por la pagina recivida.
	 * 
	 * @param avaliable disponibilidad para filtrar la búsqueda.
	 * @param page página para empezar la paginación en la búsqueda.
	 * @return lista de regalos paginada y filtrada por nombre
	 * @throws ServiceException 
	 */
	public List<Gift> getByAvaliablePaged(boolean avaliable, int element, int page) throws ServiceException {
		if(element>0&&page>0) {
			
			return repository.getByAvaliablePaged(avaliable, element, page);
			
		}else {
			throw new ServiceException("El número de elementos pedido no es válido");
		}
	}
	
	/***
	 * Método para insertar o actualizar un regalo dependiendo de si existe un
	 * registro con este id en la BBDD. Lanza una excepción
	 * si no se encuentra el regalo en la BBDD.
	 * 
	 * @param Gift: El regalo a actualizar/insertar.
	 * @return Devuelve el regalo con el id generado.
	 * @throws RecordNotFoundException
	 * @throws ServiceException 
	 */
	public Gift createOrUpdate(Gift gift) throws RecordNotFoundException, ServiceException {
		if(gift!=null) {
			
		}else {
			throw new ServiceException("El regalo introducido es nulo");
		}
		if (gift.getId() != null && gift.getId() > 0) {
			Optional<Gift> a = repository.findById(gift.getId());

			if (a.isPresent()) { // update
				Gift newGift = a.get();
				
				newGift.setId(gift.getId());
				newGift.setName(gift.getName());
				newGift.setPoints(gift.getPoints());
				newGift.setAvailable(gift.isAvailable());
				newGift.setPicture(gift.getPicture());
				newGift.setExchangeGifts(gift.getExchangeGifts());
							
				newGift=repository.save(newGift);
				return newGift;
			
			} else { // insert
				gift.setId(null);
				gift = repository.save(gift);
				return gift;
			}

		}
		
		else {
			gift=repository.save(gift);
			return gift;
		}
	}

	/***
	 * Método que recibe un regalo y lo elimina de la BBDD. Lanza una excepción
	 * si no se encuentra el regalo en la BBDD.
	 * 
	 * @param: Gift El regalo a eliminar.
	 * @throws RecordNotFoundException
	 * @throws ServiceException 
	 */
	public void delete(Gift gift) throws RecordNotFoundException, ServiceException{
		if(gift!=null) {
			if(gift.getId()!=null) {
				if(gift.getId()>0) {
					Optional<Gift> optional=repository.findById(gift.getId());
					
					if(optional.isPresent()) {
						
						repository.deleteById(gift.getId());
					}
					else {
						throw new RecordNotFoundException("El regalo no existe", gift.getId());
					}
					
				}else {
					throw new ServiceException("El id introducido es nulo");
				}
				
			}else {
				throw new ServiceException("El regalo introducido es nulo");
			}
					
			}else {
				throw new RecordNotFoundException("El id introducido es invalido",gift.getId());
			}
				
	}
}
