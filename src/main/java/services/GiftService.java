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
	 * Devuelve todas las agencias.
	 * @return
	 */
	public List<Gift> getAll() {
		return repository.findAll();
	}

	public Gift getById(Long id) throws RecordNotFoundException {
		Optional<Gift> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new RecordNotFoundException("La agencia no existe", id);
		}
	}

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
