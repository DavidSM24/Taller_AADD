package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.RecordNotFoundException;
import models.InsuranceCompany;
import repositories.InsuranceCompanyRepository;

@Service
public class InsuranceCompanyService {

	@Autowired
	InsuranceCompanyRepository repository;

	/**
	 * M�todo para obtener todas las compa�ias de seguros
	 * 
	 * @return List<InsuranceCompany> todas las compa�ias de seguros de la base de
	 *         datos
	 */
	public List<InsuranceCompany> getAll() {
		return repository.findAll();
	}

	/**
	 * M�todo para obtener una compa�ia de seguros atrav�s de su identificador
	 * 
	 * @param id
	 * @return InsuranceCompany
	 */
	public InsuranceCompany getInsuranceCompanyById(Long id) {
		Optional<InsuranceCompany> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new RecordNotFoundException("No se ha encontrado una compa�ia de seguros con ese id", id);
		}
	}

	/**
	 * M�todo para guardar o actualizar la compa�ia de seguros
	 * 
	 * @param insuranceCompany
	 * @return InsuranceCompany unida a la base de datos
	 */
	public InsuranceCompany createOrUpadateInsuranceCompany(InsuranceCompany insuranceCompany) {
		if (insuranceCompany.getId() != null && insuranceCompany.getId() > 0) {
			Optional<InsuranceCompany> insuranceCompanyDB = repository.findById(insuranceCompany.getId());
			if (insuranceCompanyDB.isPresent()) {// fusi�n
				InsuranceCompany newInsuranceCompany = insuranceCompanyDB.get();
				newInsuranceCompany.setId(insuranceCompany.getId());
				newInsuranceCompany.setCIA_Name(insuranceCompany.getCIA_Name());
				newInsuranceCompany.setAgencias(insuranceCompany.getAgencias());
				newInsuranceCompany = repository.save(insuranceCompany);
				return newInsuranceCompany;
			} else {// guardado sin estar en la base de datos
				insuranceCompany.setId(null);
				insuranceCompany = repository.save(insuranceCompany);
				return insuranceCompany;
			}
		} else {// guardado sin estar en la base de datos
			insuranceCompany = repository.save(insuranceCompany);
			return insuranceCompany;
		}
	}

	/**
	 * M�todo que borra la compa�ia de seguros 
	 * @param id
	 * @return boolean que especifica si se ha borrado o no de la base de datos
	 */
	public boolean deleteInsuranceCompany(Long id) {
		boolean result=false;
		if(id!=null) {
			if(id>0) {
				Optional<InsuranceCompany> insuranceCompany=repository.findById(id);
				if(insuranceCompany.isPresent()) {
					repository.deleteById(id);
					result= true;
				}else {
					result=false;				
				throw new RecordNotFoundException("La nota no se ha podido borrar por que su id no existe en la base de datos", id);
				}
			}else {
				result=false;
				throw new RecordNotFoundException("El id introducido no es valido", id);
			}
		}else {
			result=false;
			throw new RecordNotFoundException("El id introducido es nulo");
		}
		return result;
		
	}
	
	/**
	 * M�todo que devuelve las compa�ias de seguros con un nombre deternimado paginadas
	 * @param name
	 * @return List<InsuranceCompany>
	 */
	public List<InsuranceCompany> getByNamePaged(String name){
		return new ArrayList<InsuranceCompany>();
	}
	
	/**
	 * M�todo que devuelve todas las compa�ias de seguros que tengan un determinado nombre
	 * @param name
	 * @return List<InsuranceCompany>
	 */
	public List<InsuranceCompany> getByCIAName(String name){
		return new ArrayList<InsuranceCompany>();
	}

}
