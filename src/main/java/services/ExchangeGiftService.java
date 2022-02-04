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
	/**
	 * Repositorio de el regalo intercambiado asociado a este servicio.
	 */
	@Autowired
	ExchangeGiftRepository repository;
	/**
	 * Método que devuelve todos los regalos intercambiado.
	 * 
	 * @return lista de los regalos intercambiado
	 */
	public List<ExchangeGift> getAll(){
		return repository.findAll();
	}
	/***
	 * Método para conseguir un regalo intercambiado a partir de su id. Recibe un Long.
	 * Posibilidad de dar una excepción NotFound.
	 *
	 * @param id
	 * @return el regalo intercambiado con ese id
	 * @throws RecordNotFoundException
	 */
	public ExchangeGift getbyId(Long id){
		Optional<ExchangeGift> result=repository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new RecordNotFoundException("ExchangeGift no existe",id);
		}
		
	}
	/***
	 * Método para insertar o actualizar un regalo intercambiado dependiendo de si existe 
	 * un registro con este id en la BBDD. Lanza una excepción si no se
	 * encuentra al regalo intercambiado en la BBDD.
	 * 
	 * @param Agency: El regalo intercambiado a actualizar/insertar.
	 * @return Devuelve el regalo intercambiado con el id generado.
	 * @throws RecordNotFoundException
	 */
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
	/***
	 * Método que recibe un regalo intercambiado y la elimina de la BBDD. Lanza una
	 * excepción si no se encuentra el regalo intercambiado en la BBDD.
	 * 
	 * @param Agency: El regalo intercambiado a eliminar.
	 * @return Devuelve true si el regalo intercambiado se ha borrado False si no.
	 * @throws RecordNotFoundException
	 */
	public boolean delete(ExchangeGift gift) throws RecordNotFoundException{
		boolean result=false;
		Optional<ExchangeGift> optional=repository.findById(gift.getId());
		if(optional.isPresent()) {
			repository.deleteById(gift.getId());
			result=true;
		}
		else {
			result=false;
			throw new RecordNotFoundException("El regalo intercambiado no existe", gift.getId());
			
		}
		return result;
	}
}
