package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.RecordNotFoundException;
import models.Agency;
import repositories.AgencyRepository;

@Service
public class AgencyService {

	/**
	 * Repositorio de agencias asociado a este servicio.
	 */
	@Autowired
	AgencyRepository repository;

	/**
	 * Método que devuelve todas las agencias.
	 * 
	 * @return lista de agencias
	 */
	public List<Agency> getAll() {
		return repository.findAll();
	}

	
	/***
	 * Método para conseguir una agencia a partir de su id. Recibe un Long.
	 * Posibilidad de dar una excepción NotFound.
	 *
	 * @param id
	 * @return la agencia con ese id
	 * @throws RecordNotFoundException
	 */
	public Agency getById(Long id) throws RecordNotFoundException {
		Optional<Agency> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new RecordNotFoundException("La agencia no existe", id);
		}
	}

	
	/***
	 * Método para insertar o actualizar una agencia dependiendo de si existe 
	 * un registro con este id en la BBDD. Lanza una excepción si no se
	 * encuentra la agencia en la BBDD.
	 * 
	 * @param Agency: La agencia a actualizar/insertar.
	 * @return Devuelve la agencia con el id generado.
	 * @throws RecordNotFoundException
	 */
	public Agency createOrUpdate(Agency agency) throws RecordNotFoundException {
		if (agency.getId() != null && agency.getId() > 0) {
			Optional<Agency> a = repository.findById(agency.getId());

			if (a.isPresent()) { // update
				Agency newAgency = a.get();
				
				newAgency.setId(agency.getId());
				newAgency.setZipCode(agency.getZipCode());
				newAgency.setAddress(agency.getAddress());
				newAgency.setLocation(agency.getLocation());
				newAgency.setPhoneNumber(agency.getPhoneNumber());
				newAgency.setAmount(agency.getAmount());
				newAgency.setPoints(agency.getPoints()	);
				newAgency.setPointsRedeemed(agency.getPointsRedeemed());
				newAgency.setActive(agency.isActive());
				newAgency.setMyInsurenceCompany(agency.getMyInsurenceCompany());
				newAgency.setMyCarRepairs(agency.getMyCarRepairs());
				newAgency.setMyExchangesGifts(agency.getMyExchangesGifts());
				newAgency.setMyUser(agency.getMyUser());
				
				newAgency=repository.save(newAgency);
				return newAgency;
			
			} else { // insert
				agency.setId(null);
				agency = repository.save(agency);
				return agency;
			}

		}
		
		else {
			agency=repository.save(agency);
			return agency;
		}
	}
	
	
	/***
	 * Método que recibe una agencia y la elimina de la BBDD. Lanza una
	 * excepción si no se encuentra la agencia en la BBDD.
	 * 
	 * @param Agency: La agencia a eliminar.
	 * @throws RecordNotFoundException
	 */
	public void delete(Agency agency) throws RecordNotFoundException{
		Optional<Agency> optional=repository.findById(agency.getId());
		if(optional.isPresent()) {
			repository.deleteById(agency.getId());
		}
		else {
			throw new RecordNotFoundException("La agencia no existe", agency.getId());
		}
	}
}
