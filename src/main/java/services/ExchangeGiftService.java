package services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.RecordNotFoundException;
import models.ExchangeGift;
import repositories.ExchangeGiftRepository;

@Service
public class ExchangeGiftService {
	@Autowired
	ExchangeGiftRepository repository;
	
	public List<ExchangeGift> getAll(){
		return repository.findAll();
	}
	public ExchangeGift getallbyId(Long id){
		Optional<ExchangeGift> result=repository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new RecordNotFoundException("ExchangeGift no existe",id);
		}
		
	}
	public ExchangeGift createorupdate(ExchangeGift exgift) {
		if(exgift.getId()>0) {
			Optional<ExchangeGift>n=repository.findById(exgift.getId());
			if(n.isPresent()) {
				ExchangeGift newexchangegift=n.get();
				newexchangegift.setId(exgift.getId());
				newexchangegift.setDateExchange(exgift.getDateExchange());
				newexchangegift.setObservations(exgift.getObservations());
				newexchangegift.setDelivered(exgift.isDelivered());
				newexchangegift.setAgency(exgift.getAgency());
				newexchangegift.setGift(exgift.getGift());
				newexchangegift=repository.save(newexchangegift);
				return exgift;
			}else {
				
				exgift=repository.save(exgift);
				return exgift;
			}
			
		}
		return exgift;
	}
	public void deletenotebyId(Long id) throws RecordNotFoundException{
		Optional<ExchangeGift> carp=repository.findById(id);
		if(carp.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new RecordNotFoundException("CarRepair no existe",id);
		}
	}
}
