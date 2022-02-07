package project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.InsuranceCompany;
import project.repositories.InsuranceCompanyRepository;

@Service
public class InsuranceCompanyService {

	@Autowired
	InsuranceCompanyRepository repository;

	/**
	 * Método para obtener todas las compañias de seguros
	 * 
	 * @return List<InsuranceCompany> todas las compañias de seguros de la base de
	 *         datos
	 */
	public List<InsuranceCompany> getAll() {
		return repository.findAll();
	}

	/**
	 * Método para obtener una compañia de seguros através de su identificador
	 * 
	 * @param id
	 * @return InsuranceCompany
	 * @throws ServiceException
	 */
	public InsuranceCompany getInsuranceCompanyById(Long id) throws ServiceException {
		if (id != null) {
			if (id > 0) {
				Optional<InsuranceCompany> result = repository.findById(id);
				if (result.isPresent()) {
					return result.get();
				} else {
					throw new RecordNotFoundException("No se ha encontrado una compañia de seguros con ese id", id);
				}

			} else {
				throw new RecordNotFoundException("El id introducido no es válido", id);
			}
		} else {
			throw new ServiceException("El id introducido es nulo");
		}
	}

	/**
	 * Método para guardar o actualizar la compañia de seguros
	 * 
	 * @param insuranceCompany
	 * @return InsuranceCompany unida a la base de datos
	 * @throws ServiceException
	 */
	public InsuranceCompany createOrUpadateInsuranceCompany(InsuranceCompany insuranceCompany) throws ServiceException {
		if (insuranceCompany != null) {
			if (insuranceCompany.getId() != null && insuranceCompany.getId() > 0) {
				Optional<InsuranceCompany> insuranceCompanyDB = repository.findById(insuranceCompany.getId());
				if (insuranceCompanyDB.isPresent()) {// fusión
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

		} else {
			throw new ServiceException("La compañia de seguros introducida es nula");
		}
	}

	/**
	 * Método que borra la compañia de seguros
	 * 
	 * @param id
	 * @return boolean que especifica si se ha borrado o no de la base de datos
	 * @throws ServiceException
	 */
	public boolean deleteInsuranceCompany(Long id) throws ServiceException {
		boolean result = false;
		if (id != null) {
			if (id > 0) {
				Optional<InsuranceCompany> insuranceCompany = repository.findById(id);
				if (insuranceCompany.isPresent()) {
					repository.deleteById(id);
					result = true;
				} else {
					result = false;
					throw new RecordNotFoundException(
							"La nota no se ha podido borrar por que su id no existe en la base de datos", id);
				}
			} else {
				result = false;
				throw new RecordNotFoundException("El id introducido no es valido", id);
			}
		} else {
			result = false;
			throw new ServiceException("El id introducido es nulo");
		}
		return result;

	}

	/**
	 * Método que devuelve las compañias de seguros con un nombre deternimado
	 * paginadas
	 * 
	 * @param name nombre de la compañia de seguros
	 * @param nElements número de tuplas que se quieran devolver
	 * @param page número de pagina que se quiera buscar
	 * @return List<InsuranceCompany> Lista paginada con las compañias de seguros que tengan ese nombre
	 * @throws ServiceException
	 */
	public List<InsuranceCompany> getByNamePaged(String name,int nElements, int page) throws ServiceException {
		if (name != null) {
			if (!name.equals("")) {
				List<InsuranceCompany> insuranceCompany = repository.getByNamePaged(name, nElements, (page-1)*nElements);
				if (insuranceCompany != null) {
					if (insuranceCompany.size() > 0) {
						return insuranceCompany;

					} else {
						throw new ServiceException("La lista obtenida esta vacia");
					}
				} else {
					throw new ServiceException("La lista obtenida esta a nulo");
				}

			} else {
				throw new ServiceException("El nombre introducido esta vacio");
			}
		} else {
			throw new ServiceException("El nombre introducido es nulo");
		}
	}

	

	/**
	 * Método que devuelve todas las compañias de seguros que tengan un determinado
	 * nombre
	 * 
	 * @param name
	 * @return List<InsuranceCompany>
	 * @throws exception.ServiceException
	 */
	public List<InsuranceCompany> getByCIAName(String name) throws ServiceException {
		if (name != null) {
			if (name.equals("")) {
				List<InsuranceCompany> insuranceCompany = repository.getByCIAName(name);
				if (insuranceCompany != null) {
					if (insuranceCompany.size() > 0) {
						return insuranceCompany;

					} else {
						throw new ServiceException("La lista obtenida esta vacia");
					}
				} else {
					throw new ServiceException("La lista obtenida esta a nulo");
				}
			} else {
				throw new ServiceException("El nombre introducido esta vacio");
			}
		} else {
			throw new ServiceException("El nombre introducido es nulo");
		}

	}

}
