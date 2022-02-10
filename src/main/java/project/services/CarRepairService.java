package project.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.CarRepair;
import project.repositories.CarRepairRepository;

@Service
public class CarRepairService {

	@Autowired
	CarRepairRepository repository;

	/**
	 * M�todo que devuelve una lista con todas las reparaciones de la base de datos
	 * 
	 * @return List<CarRepair>
	 */
	public List<CarRepair> getAll() {
		return repository.findAll();
	}
	public List<CarRepair> getAllPaged(int element,int paged) throws ServiceException{
		if(element>0&&paged>0) {
			return repository.getAllPaged(element, paged);
		}else {
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
					return result.get();
				} else {
					throw new RecordNotFoundException("El id introducido no es v�lido", id);
				}

			} else {
				throw new RecordNotFoundException("El id introducido inv�lido", id);
			}
		} else {
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
			if (carRepair.getId() != null && carRepair.getId() > 0) {// si tiene id y es mayor de 0
				Optional<CarRepair> result = repository.findById(carRepair.getId());
				if (result.isPresent()) {// si lo encuentra en la base de datos
					CarRepair newCarRepair = result.get();
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
					return newCarRepair;
				} else {// Si no esta en la base de datos
					carRepair.setId(null);
					carRepair = repository.save(carRepair);
					return carRepair;
				}
			} else {// en caso de que el id sea nulo o menor de 1
				carRepair = repository.save(carRepair);
				return carRepair;
			}
		} else {
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
				if (element > 0 && paged > 0) {
					return repository.getByCarPlatePaged(carPlate, element, paged);
				} else {
					throw new ServiceException("la paginaci�n no es correcta");
				}
			} else {
				throw new ServiceException("La matr�cula es invalida");
			}
		} else {
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
	public List<CarRepair> getByOperationPaged(String operation, int element, int paged) throws ServiceException{
		if (operation != null) {
			if (!operation.equals("")) {
				if (element > 0 && paged > 0) {
					return repository.getByIdOperationPaged(operation, element, paged);
				} else {
					throw new ServiceException("la paginaci�n no es correcta");
				}
			} else {
				throw new ServiceException("La operaci�n introducida no es invalida");
			}
		} else {
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
				if (nElement > 1 && paged > 1) {
					return repository.getByClientNamePaged(name, nElement, paged);
				} else {
					throw new ServiceException("la paginaci�n no es correcta");
				}
			} else {
				throw new ServiceException("El nombre introducido es invalido");
			}
		} else {
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
				if (nELement > 0 && paged > 0) {
					return repository.getByDateOrderPaged(ini,end, nELement, paged);
				} else {
					throw new ServiceException("la paginaci�n no es correcta");
				}
			} else {
				throw new ServiceException("La fecha introducida no es invalida");
			}
		} else {
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
		if (min >0&&max>0) {			
				if (nElement > 0 && paged > 0) {
					return repository.getByPointsPaged(min,max, nElement, paged);
				} else {
					throw new ServiceException("la paginaci�n no es correcta");
				}
		} else {
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
		if(nElement>0&&paged>0) {
			return repository.getByStatePaged(repaired, nElement, paged);
		}else {
			throw new ServiceException("La paginaci�n no es correcta");
		}
	}

}
