package project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import project.exception.RecordNotFoundException;
import project.models.Gift;
import project.repositories.GiftRepository;

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
	 * @param page pagina por la que se comienza la paginación.
	 * @return lista de regalos paginada.
	 */
	public List<Gift> getAllPaged(int page) {
		return repository.getAllPaged((page-1)*15);
	}
	
	/***
	 * Método para conseguir un regalotir a partir de su id. Recibe un Long.
	 * Posibilidad de dar una excepción NotFound.
	 *
	 * @param id
	 * @return el regalo con ese id
	 * @throws RecordNotFoundException
	 */
	public Gift getById(Long id) throws RecordNotFoundException {
		Optional<Gift> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new RecordNotFoundException("El regalo no existe", id);
		}
	}

	/**
	 * Devuelve una lista de regalos filtrada por el nombre recibido y paginada comenzando por
	 * la pagina recivida.
	 * 
	 * @param name nombre con el que se filtrará la búsqueda.
	 * @param page pagina por la que comienza la paginación de la lista.
	 * @return lista de regalos filtrada por nombre y paginada.
	 */
	public List<Gift> getByNamePaged(String name, int page) {
		return repository.getByNamePaged(name, (page-1)*15);
	}
	
	/**
	 * Devuelve una lista de regalos paginada filtrada por la disponibilidad actual, comenzando
	 * por la pagina recivida.
	 * 
	 * @param avaliable disponibilidad para filtrar la búsqueda.
	 * @param page página para empezar la paginación en la búsqueda.
	 * @return lista de regalos paginada y filtrada por nombre
	 */
	public List<Gift> getByAvaliablePaged(boolean avaliable, int page) {
		return repository.getByAvaliablePaged(avaliable, (page-1)*15);
	}
	
	/***
	 * Método para insertar o actualizar un regalo dependiendo de si existe un
	 * registro con este id en la BBDD. Lanza una excepción
	 * si no se encuentra el regalo en la BBDD.
	 * 
	 * @param Gift: El regalo a actualizar/insertar.
	 * @return Devuelve el regalo con el id generado.
	 * @throws RecordNotFoundException
	 */
	public Gift createOrUpdate(Gift Gift) throws RecordNotFoundException {
		if (Gift.getId() != null && Gift.getId() > 0) {
			Optional<Gift> a = repository.findById(Gift.getId());

			if (a.isPresent()) { // update
				Gift newGift = a.get();
				
				newGift.setId(Gift.getId());
				newGift.setName(Gift.getName());
				newGift.setPoints(Gift.getPoints());
				newGift.setAvailable(Gift.isAvailable());
				newGift.setExchangeGifts(Gift.getExchangeGifts());
				
				newGift=repository.save(newGift);
				return newGift;
			
			} else { // insert
				Gift.setId(null);
				Gift = repository.save(Gift);
				return Gift;
			}

		}
		
		else {
			Gift=repository.save(Gift);
			return Gift;
		}
	}

	/***
	 * Método que recibe un regalo y lo elimina de la BBDD. Lanza una excepción
	 * si no se encuentra el regalo en la BBDD.
	 * 
	 * @param: Gift El regalo a eliminar.
	 * @throws RecordNotFoundException
	 */
	public void delete(Gift Gift) throws RecordNotFoundException{
		Optional<Gift> optional=repository.findById(Gift.getId());
		if(optional.isPresent()) {
			repository.deleteById(Gift.getId());
		}
		else {
			throw new RecordNotFoundException("El regalo no existe", Gift.getId());
		}
	}
}
