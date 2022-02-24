package project.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.Agency;
import project.models.CarRepair;
import project.repositories.AgencyRepository;
import project.repositories.CarRepairRepository;

@Service
public class CarRepairService {

	//log4j2
	private static final Logger logger=LoggerFactory.getLogger(CarRepairService.class);

	
	
	//repositiorio asicia al servicio
	@Autowired
	CarRepairRepository repository;
	@Autowired
	AgencyService agencyService;

	/**
	 * M�todo que devuelve una lista con todas las reparaciones de la base de datos
	 * 
	 * @return List<CarRepair>
	 */
	public List<CarRepair> getAll() {
		
		
		logger.info("Petici�n realizada correctamente");
		return repository.findAll();
		
	}
	
	public boolean delete(CarRepair carRepair) throws ServiceException {
		
		boolean result=false;
		
		if(carRepair!=null) {
			if(carRepair.getId()!=null&&carRepair.getId()>0) {
				Optional<CarRepair> aux=repository.findById(carRepair.getId());
				if(aux.isPresent()) {
					repository.deleteById(carRepair.getId());
					result=true;
					logger.info("Petici�n realizada correctamente");
					
					return result;
					
				}else {
					logger.error("EL id "+ carRepair.getId()+" no se encuentra en la base de datos");
					
					throw new RecordNotFoundException("El id introducido no se encuentra en la base de datos",carRepair.getId());
				}
			}else {
				logger.error("El id introducido no es v�lido");
				
				throw new RecordNotFoundException("El id introducido no es v�lido",carRepair.getId());
			}
		}else {
			logger.error("La reparaci�n introducida es nula");
			
			throw new ServiceException("La reparaci�n introducida es nula");
			
		}
	}
	
	/**
	 * M�todo que devuelve todas las reparaciones paginadas
	 * @param element
	 * @param paged
	 * @return
	 * @throws ServiceException
	 */
	public List<CarRepair> getAllPaged(int element,int paged) throws ServiceException{
		if(element>0&&paged>-1) {
			logger.info("Petici�n realizada correctamente");
			
			return repository.getAllPaged(element, paged);
			
		}else {
			logger.error("La paginaci�n no es correcta");
			
			throw new ServiceException("La paginaci�n no es correcta");
		}
	}

	/**
	 * M�todo que devuelve una reparaci�n con el id correspondiente
	 * 
	 * @param id n�mero de identificaci�n de la base de datos
	 * @return CarRepair
	 */
	public CarRepair getById(Long id) {
		if (id != null) {
			if (id > 0) {
				
				Optional<CarRepair> result = repository.findById(id);
				
				if (result.isPresent()) {
					logger.info("Petici�n realizada correctamente");
					
					return result.get();
					
				} else {
					logger.error("La reparaci�n con el id "+id+" no de encuentra en la base de datos");
					
					throw new RecordNotFoundException("El id introducido no es v�lido", id);
				}

			} else {
				logger.error("El id introducido no es v�lido");
				
				throw new RecordNotFoundException("El id introducido inv�lido", id);
			}
		} else {
			logger.error("El id introducido es nulo");
			
			throw new RecordNotFoundException("El id introducido es nulo", id);
		}

	}

	/**
	 * M�todo que guarda o actualiza las reparaciones en la base de datos
	 * 
	 * @param carRepair
	 * @return CarRepair
	 * @throws ServiceException
	 */
	public CarRepair createOrUpdateCarRepair(CarRepair carRepair) throws ServiceException {
		if (carRepair != null) {
			if(carRepair.getAmount()>-1&& carRepair.getAsigPoints()>-1&&
					(carRepair.getBrandCar()!=null&&(carRepair.getCarPlate().length()>4&&carRepair.getCarPlate().length()<11))&&
					(carRepair.getClienteName()!=null&&!carRepair.getClienteName().equals(""))&&
					(carRepair.getDateOrder()!=null)&&
					(carRepair.getModel()!=null&&!carRepair.getModel().equals(""))&&
					carRepair.getMyAgency()!=null) {
				if (carRepair.getId() != null && carRepair.getId() > 0) {// si tiene id y es mayor de 0
					
					Optional<CarRepair> result = repository.findById(carRepair.getId());
					
					if (result.isPresent()) {// si lo encuentra en la base de datos
						CarRepair newCarRepair = result.get();
						
						if(!newCarRepair.isRepaired()&&carRepair.isRepaired()) {
							if(!sumPoints(carRepair.getMyAgency(),carRepair.getAsigPoints())) {
								
							}
						}
						
						newCarRepair.setId(carRepair.getId());// id
						newCarRepair.setOperation(carRepair.getOperation());// operacion
						newCarRepair.setCarPlate(carRepair.getCarPlate());// matricula
						newCarRepair.setModel(carRepair.getModel());// modelo
						newCarRepair.setBrandCar(carRepair.getBrandCar());// marca
						newCarRepair.setClienteName(carRepair.getClienteName());// nombre del cliente
						newCarRepair.setDateOrder(carRepair.getDateOrder());// fecha de alta
						newCarRepair.setNor(carRepair.getNor());// n�mero de orden de registro
						newCarRepair.setAmount(carRepair.getAmount());// coste
						newCarRepair.setDateRepair(carRepair.getDateRepair());// fecha de reparaci�n
						newCarRepair.setAsigPoints(carRepair.getAsigPoints());// puntos
						newCarRepair.setRepaired(carRepair.isRepaired());// reparado
						newCarRepair.setMyAgency(carRepair.getMyAgency());// agencia
						
						newCarRepair=repository.save(newCarRepair);
						logger.info("Petici�n realizada correctamente");
						
						return newCarRepair;
						
					} else {// Si no esta en la base de datos
						carRepair.setId(null);
						carRepair = repository.save(carRepair);
						logger.info("Petici�n realizada correctamente");
						
						return carRepair;
						
					}
				} else {// en caso de que el id sea nulo o menor de 1
					carRepair = repository.save(carRepair);
					logger.info("Petici�n realizada correctamente");
					
					return carRepair;
					
				}
			} else {
				logger.error("Los atributos de la reparaci�n no son v�lidos");
				
				throw new ServiceException("Algo a fallado buscate la vida");
			}
				
			}else {
				logger.error("La reparaci�n introducida no es v�lida");
				
				throw new ServiceException("La reparaci�n introducida no es valida");
			}
	}

	/**
	 * M�todo que devuelve una lista de reparacipones busccando por matricula
	 * 
	 * @param carPlate
	 * @param element
	 * @param paged
	 * @return List<CarRepair>
	 * @throws ServiceException
	 */
	public List<CarRepair> getByCarPlatePaged(String carPlate, int element, int paged) throws ServiceException {
		if (carPlate != null) {
			if (!carPlate.equals("")) {
				if (element > 0 && paged > -1) {
					logger.info("Petici�n realizada correctamente");
					
					return repository.getByCarPlatePaged(carPlate.toLowerCase(), element, paged);
					
				} else {
					logger.error("La p�ginaci�n no es correcta");
					
					throw new ServiceException("la paginaci�n no es correcta");
				}
			} else {
				logger.error("La matr�cula introducida no es v�lida");
				
				throw new ServiceException("La matr�cula es invalida");
			}
		} else {
			logger.error("La matr�cula es nula");
			
			throw new ServiceException("Matriucla es nulo");
		}
	}
	
	/**
	 * M�todod que devuelve una lista de reparaciones buscando por la operaci�n
	 * @param operation
	 * @param element
	 * @param paged
	 * @return List<CarRepair>
	 * @throws ServiceException
	 */
	public List<CarRepair> getByOperationPaged(Long operation, int element, int paged) throws ServiceException{
		if (operation != null) {
			if (!operation.equals("")) {
				if (element > 0 && paged  > -1) {
					logger.info("Petici�n realizada correctamente");
					
					return repository.getByIdOperationPaged(operation, element, paged);
				
				} else {
					logger.error("La paginaci�n no es correcta");
					
					throw new ServiceException("la paginaci�n no es correcta");
				}
			} else {
				logger.error("La operaci�n introducida no es v�lida");
				
				throw new ServiceException("La operaci�n introducida es invalida");
			}
		} else {
			logger.error("La operaci�n introducida es nula");
			
			throw new ServiceException("La operaci�n introducida es nula");
		}
	}
	/**
	 * M�todo que devuelve una lista de reparaci�nes paginada seg�n el cliente del coche
	 * @param name
	 * @param nElement
	 * @param paged
	 * @return List<CarRepair>
	 * @throws ServiceException
	 */
	public List<CarRepair> getByClientNamePaged(String name,int nElement,int paged) throws ServiceException{
		if (name != null) {
			if (!name.equals("")) {
				if (nElement > 0 && paged  > -1) {
					logger.info("Petici�n realizada correctamente");
					
					return repository.getByClientNamePaged(name.toLowerCase(), nElement, paged);
					
				} else {
					logger.error("La paginaci�n no es correcta");
					
					throw new ServiceException("la paginaci�n no es correcta");
				}
			} else {
				logger.error("El nombre introducido no es v�lido");
				
				throw new ServiceException("El nombre introducido es invalido");
			}
		} else {
			logger.error("El nombre introducido es nulo");
			
			throw new ServiceException("El nombre introducido es nulo");
		}
	}

	/**
	 * M�todo que devuelve todas las reparaciones paginadas seg�n la fecha de entrada
	 * @param ini
	 * @param end
	 * @param nELement
	 * @param paged
	 * @return List<CarRepair>
	 * @throws ServiceException
	 */
	public List<CarRepair> getByDateOrderPaged(LocalDateTime ini,LocalDateTime end, int nELement, int paged) throws ServiceException{
		if (ini != null&&end!=null) {
			if (ini.equals("")&&end.equals("")) {
				if (nELement > 0 && paged  > -1) {
					logger.info("Petici�n realizada correctamente");
					
					return repository.getByDateOrderPaged(ini,end, nELement, paged);
					
				} else {
					logger.error("La paginaci�n no es correcta");
					
					throw new ServiceException("la paginaci�n no es correcta");
				}
			} else {
				logger.error("La fecha introducida no es v�lida");
				
				throw new ServiceException("La fecha introducida  es invalida");
			}
		} else {
			logger.error("La fecha introducida es nula");
			
			throw new ServiceException("La fecha introducida es nula");
		}
	}

	/**
	 * M�todo que devuelve las reparaciones que esten entre los puntos aasignados
	 * @param min
	 * @param max
	 * @param nElement
	 * @param paged
	 * @return List<CarRepair>
	 * @throws ServiceException
	 */
	public List<CarRepair> getByPointsPaged(int min, int max, int nElement,int paged) throws ServiceException{
		System.out.println(min+" "+max);
		if (min >=0&&max>=0) {			
				if (nElement > 0 && paged  > -1) {
					logger.info("Petici�n realizada correctamente");
					
					return repository.getByPointsPaged(min,max, nElement, paged);
					
				} else {
					logger.error("La paginaci�n no es correcta");
					
					throw new ServiceException("la paginaci�n no es correcta");
				}
		} else {
			logger.error("La operaci�n introducia es nula");
			
			throw new ServiceException("La operaci�n introducida es nula");
		}
	}

	/**
	 * M�todo que devuelve las reparaciones en funci�n de su estado
	 * @param repaired
	 * @param nElement
	 * @param paged
	 * @return List<CarRepair>
	 * @throws ServiceException
	 */
	public List<CarRepair> getByStatePaged(boolean repaired,int nElement, int paged) throws ServiceException{
		if(nElement>0&&paged > -1) {
			logger.info("Petici�n realizada correctamente");
			
			return repository.getByStatePaged(repaired, nElement, paged);
			
		}else {
			logger.error("La paginaci�n no es correcta");
			
			throw new ServiceException("La paginaci�n no es correcta");
		}
	}
	
	
	/**
	 * M�todo que suma los puntos cuando un coche se actualiza como reparado
	 * @throws ServiceException 
	 */
	public boolean sumPoints(Agency agency, long points) throws ServiceException {
		if(agency!=null) {
			if(agency.getId()!=null&&agency.getId()>0) {
				if(points>0) {
					
					Agency newAgency=agencyService.getById(agency.getId());
					
					newAgency.setPoints(newAgency.getPoints()+points);
			
					
					agencyService.createOrUpdate(newAgency);
					
					return true;
					
				}else {
					logger.error("Los puntos introducidos son menores que 0");
					
					throw new ServiceException("Los puntos introducidos son menores que 0");
				}
			}else {
				logger.error("El id introducido no es v�lido");
				
				throw new RecordNotFoundException("El id introducido no es v�liso", agency.getId());
			}
			
		}else {
			logger.error("La agencia introducida es nula");
			
			throw new ServiceException("La agencia introducida es nula");
		}
	}
	
}
