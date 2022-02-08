package project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exception.RecordNotFoundException;
import project.models.Agency;
import project.repositories.AgencyRepository;

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

	/**
	 * Devuelve una lista de agencias paginada en función de la página que se está buscando.
	 * 
	 * @param element nº de elementos a buscar
	 * @param page nº de página a partir del cual buscar.
	 * @return Una lista de agencias.
	 */
	public List<Agency> getAllPaged(int element, int page){
		
		return repository.getAllPaged(element,page);
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
	
	/**
	 * Devuelve una lista paginada de agencias cuyo nombre de usuario contenga el
	 * parametro username.
	 * 
	 * @param username el nombre del usuario.
	 * @param element nº de elementos a buscar
	 * @param page comienzo de la paginación.
	 * @return La lista paginada y filtrada de agencias.
	 */
	public List<Agency> getByUsernamePaged(String username, int element, int page){
		
		return repository.getByUsernamePaged(username.toLowerCase(),element,page);
	}
	
	
	/**
	 *  Devuelve todas las agencias que coincidan con el parámetro isActive paginadas,
	 *  pudiendo ser las que estén o no activas en función de lo que se reciba.
	 * 
	 * @param active boolean con el parámetro para filtrar por active/inactive
	 * @param element nº de elementos a buscar
	 * @param page pagina por la que se empieza a paginar
	 * @return Lista de agencias paginada y flitrada por isActive.
	 */
	public List<Agency> getByActivePaged(boolean active, int element, int page){
		return repository.getByActivePaged(active,element,page);
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
