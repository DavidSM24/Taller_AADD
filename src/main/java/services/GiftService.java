package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import exception.RecordNotFoundException;
import models.Gift;
import repositories.GiftRepository;

public class GiftService {
	
	@Autowired
	GiftRepository repository;

	/**
	 * M�todo que devuelve todos los regalos.
	 * 
	 * @return lista de regalos.
	 */
	public List<Gift> getAll() {
		return repository.findAll();
	}
	
	/***
	 * M�todo para conseguir un regalotir a partir de su id. Recibe un Long.
	 * Posibilidad de dar una excepci�n NotFound.
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

	
	/***
	 * M�todo para insertar o actualizar un regalo dependiendo de si existe un
	 * registro con este id en la BBDD. Lanza una excepci�n
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
	 * M�todo que recibe un regalo y lo elimina de la BBDD. Lanza una excepci�n
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
