package project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.Gift;
import project.models.InsuranceCompany;
import project.repositories.InsuranceCompanyRepository;

@Service
public class InsuranceCompanyService {
	// log4j2
		private static final Logger logger = LoggerFactory.getLogger(InsuranceCompany.class);
	
	/**
	 * Repositorio del regalo asociado a este servicio
	 */
	@Autowired
	InsuranceCompanyRepository repository;

	/**
	 * Método para obtener todas las compañias de seguros
	 * 
	 * @return List<InsuranceCompany> todas las compañias de seguros de la base de
	 *         datos
	 */
	public List<InsuranceCompany> getAll() {
		logger.info("Petición realizada correctamente");
		
		return repository.findAll();
	}
	
	/**
	 * Método que devuelve una lista con las compañias de seguros paginadas
	 * @param nElement número de elemtos que se quieran devolver
	 * @param page
	 * @return List<InsuranceCompany>
	 * @throws ServiceException
	 */
	public List<InsuranceCompany> getAllPaged(int nElement, int page) throws ServiceException{
		if(nElement>0) {
			if(page>-1) {
				logger.info("Petición realizada correctamente");
				
				return repository.getAllPaged(nElement, (page - 1) * nElement);
			}else {
				logger.error("El número de páginas introducidas es menor de -1");
				
				throw new ServiceException("Número de páginas introducidas menor de -1");
			}
			
		}else {
			logger.error("El número de elementos introducido es menor de 0");
			
			throw new ServiceException("nElemnt intruducido menor de 0");
			
		}
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
					logger.info("Petición realizada correctamente");
					
					return result.get();
					
				} else {
					logger.error("No se ha encontrado un compañia de seguros con el id "+id);
					
					throw new RecordNotFoundException("No se ha encontrado una compañia de seguros con ese id", id);
				}

			} else {
				logger.error("El id "+id+" introducido no es válido");
				
				throw new RecordNotFoundException("El id introducido no es válido", id);
			}
		} else {
			logger.error("El id introducido es nulo");
			
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
					newInsuranceCompany.setAgencies(insuranceCompany.getAgencies());
					
					newInsuranceCompany = repository.save(insuranceCompany);
					logger.info("Petición realizada correctamente");
					
					return newInsuranceCompany;
				} else {// guardado sin estar en la base de datos
					insuranceCompany.setId(null);
					insuranceCompany = repository.save(insuranceCompany);
					logger.info("Petición realizada correctamente");
					
					return insuranceCompany;
				}
			} else {// guardado sin estar en la base de datos
				insuranceCompany = repository.save(insuranceCompany);
				logger.info("Petición realizada correctamente");
				
				return insuranceCompany;
			}

		} else {
			logger.error("La compañia de seguros introducida es nula");
			
			throw new ServiceException("La compañia de seguros introducida es nula");
		}
	}

	/**
	 * Método que borra la compañia de seguros
	 * 
	 * @param insurance
	 * @return boolean que especifica si se ha borrado o no de la base de datos
	 * @throws ServiceException
	 */
	public boolean deleteInsuranceCompany(InsuranceCompany insurance) throws ServiceException {
		boolean result = false;
		if (insurance!=null && insurance.getId() != null) {
			if (insurance.getId() > 0) {
				Optional<InsuranceCompany> insuranceCompany = repository.findById(insurance.getId());
				if (insuranceCompany.isPresent()) {
					repository.deleteById(insurance.getId());
					result = true;
				} else {
					result = false;
					logger.error("El id "+insurance.getId()+" No existe en  la base de datos");
					
					throw new RecordNotFoundException(
							"La nota no se ha podido borrar por que su id no existe en la base de datos", insurance.getId());
				}
			} else {
				result = false;
				logger.error("El id es menor de 0");
				
				throw new RecordNotFoundException("El id introducido no es valido", insurance.getId());
			}
		} else {
			result = false;
			logger.error("El id introducido es nulo");
			
			throw new ServiceException("El id introducido es nulo");
		}
		logger.info("Petición realizada correctamente");
		
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
			if(nElements>0) {
				if(page>-1) {
					if (!name.equals("")) {
						List<InsuranceCompany> insuranceCompany = repository.getByNamePaged(name.toLowerCase(), nElements,
								(page - 1) * nElements);
						if (insuranceCompany != null) {
							if (insuranceCompany.size() > 0) {
								logger.info("Petición realizada correctamente");
								
								return insuranceCompany;

							} else {
								logger.error("La lista introducida esta vacía");
								
								throw new ServiceException("La lista obtenida esta vacia");
							}
						} else {
							logger.error("La lista obtenida esta a nulo");
							
							throw new ServiceException("La lista obtenida esta a nulo");
						}

					} else {
						logger.error("El nombre introducido esta vacio");
						
						throw new ServiceException("El nombre introducido esta vacio");
					}
					
				}else {
					logger.error("El número de página es menor que -1");
					
					throw new ServiceException("El numero de página no es valido");
				}
			}else {
				logger.error("El número de elementos solicitado es menor que 0");
				
				throw new ServiceException("Numero de elementos insuficiente");
			}
		} else {
			logger.error("El nombre introducido es nulo");
			
			throw new ServiceException("El nombre introducido es nulo");
		}
	}

	

	/**
	 * Método que devuelve todas las compañias de seguros que tengan un determinado
	 * nombre
	 * 
	 * @param name
	 * @return List<InsuranceCompany>
	 * @throws exception
	 */
	public List<InsuranceCompany> getByCIAName(String name) throws ServiceException {
		if (name != null) {
			if (name.equals("")) {
				
				List<InsuranceCompany> insuranceCompany = repository.getByCIAName(name.toLowerCase());
				
				if (insuranceCompany != null) {
					if (insuranceCompany.size() > 0) {
						logger.info("Petición realizada correctamente");
						
						return insuranceCompany;

					} else {
						logger.error("La lista obtenida esta vacia");
						
						throw new ServiceException("La lista obtenida esta vacia");
					}
				} else {
					logger.error("La lista obtenida es nula");
					
					throw new ServiceException("La lista obtenida esta a nulo");
				}
			} else {
				logger.error("El nombre introducido esta vacio");
				
				throw new ServiceException("El nombre introducido esta vacio");
			}
		} else {
			logger.error("El nombre introducido es nulo");
			
			throw new ServiceException("El nombre introducido es nulo");
		}

	}

}
