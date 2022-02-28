package project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.Agency;
import project.models.InsuranceCompany;
import project.models.User;
import project.repositories.UserRepository;

@Service
public class UserService {
	// log4j2
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	
	/**
	 * Repositorio de Usuarios asociado a este servicio.
	 */
	@Autowired
	UserRepository repository;

	/**
	 * M�todo que devuelve todos los Usuarios.
	 * 
	 * @return lista de usuarios
	 */
	public List<User> getall() {
		logger.info("Petici�n realizada correctamente");
		
		return repository.findAll();
	}
	
	/**
	 * M�todo que devuelve todos los Usuarios.
	 * 
	 * @return lista de usuarios
	 */
	public List<User> getByAvailable() {
		logger.info("Petici�n realizada correctamente");
		
		return repository.getByAvailable();
	}

	/***
	 * M�todo para conseguir un usuario a partir de su id. Recibe un Long.
	 * Posibilidad de dar una excepci�n NotFound.
	 *
	 * @param id
	 * @return el usuario con ese id
	 * @throws RecordNotFoundException
	 */
	public User getbyId(Long id) throws RecordNotFoundException {
		if (id != null) {
			if (id > 0) {
				Optional<User> result = repository.findById(id);
				if (result.isPresent()) {
					logger.info("Petici�n realizada correctamente");
					
					return result.get();
				} else {
					logger.error("El usario con la id "+id+" no existe");
					
					throw new RecordNotFoundException("User no existe", id);
				}

			} else {
				logger.error("El id es menor de 0");
				
				throw new RecordNotFoundException("El rango del id introducido no es valido", id);
			}

		} else {
			logger.error("El id es nulo");
			
			throw new RecordNotFoundException("El id es nulo", id);
		}

	}

	/***
	 * M�todo para insertar o actualizar un usuario dependiendo de si existe un
	 * registro con este id en la BBDD. Lanza una excepci�n si no se encuentra al
	 * usuario en la BBDD.
	 * 
	 * @param Agency: El usuario a actualizar/insertar.
	 * @return Devuelve el usuario con el id generado.
	 * @throws RecordNotFoundException
	 * @throws SerialException
	 * @throws ServiceException
	 */
	public User createorupdate(User user) throws RecordNotFoundException, ServiceException {
		if (user != null) {
			if (user.getCode() > 0 && user.getEmail() != null && user.getName() != null && user.getPassword() != null) {
				if (user.getId() != null && user.getId() > 0) {
					Optional<User> u = repository.findById(user.getId());

					if (u.isPresent()) { // update
						User newUser = u.get();

						newUser.setId(user.getId());
						newUser.setCode(user.getCode());
						newUser.setPassword(user.getPassword());
						newUser.setAdministrator(user.isAdministrator());
						newUser.setEmail(user.getEmail());
						newUser.setName(user.getName());
						
						newUser = repository.save(newUser);
						logger.info("Petici�n realizada correctamente");
						
						return newUser;

					} else { // insert
						user.setId(null);
						user = repository.save(user);
						logger.info("Petici�n realizada correctamente");
						
						return user;
					}

				}

				else {
					user = repository.save(user);
					logger.info("Petici�n realizada correctamente");
					
					return user;
				}

			} else {
				logger.error("Alguno de los atributos introducidos es nulo");
				
				throw new ServiceException("Algo es nulo, intetalo otra vez");
			}

		} else {
			logger.error("El usuario es nulo");
			
			throw new ServiceException("El usuario es nulo");
		}
	}

	/***
	 * M�todo que recibe un usuario y la elimina de la BBDD. Lanza una excepci�n si
	 * no se encuentra el usuario en la BBDD.
	 * 
	 * @param Agency: El Usuario a eliminar.
	 * @return Devuelve true si el usuario se ha borrado False si no.
	 * @throws RecordNotFoundException
	 * @throws ServiceException 
	 */
	public boolean delete(User user) throws RecordNotFoundException, ServiceException {
		boolean result = false;
		if(user!=null) {
			if(user.getId()!=null) {
				if(user.getId()>0) {
					Optional<User> optional = repository.findById(user.getId());
					if (optional.isPresent()) {
						repository.deleteById(user.getId());
						result = true;
					} else {
						result = false;
						logger.error("El usuario con la id "+user.getId()+" no existe en esta base de datos");
						
						throw new RecordNotFoundException("El usuario no existe", user.getId());
					}
					
				}else {
					result = false;
					logger.error("El rango de la id no es v�lido");
					
					throw new RecordNotFoundException("El rango del id no es v�lido",user.getId());
				}
			}else {
				result = false;
				logger.error("El id es nulo");
				
				throw new RecordNotFoundException("El id es nulo",user.getId());
			}
		}else {
			result = false;
			logger.error("EL usuario es nulo");
			
			throw new ServiceException("Usuario es nulo");
		}
		logger.info("Petici�n realizada correctamente");
		
		return result;
	}

	/**
	 * Devuelve una lista de usuarios paginada en funci�n de la p�gina que se est�
	 * buscando.
	 * 
	 * @param element n� de elementos a buscar
	 * @param page    n� de p�gina a partir del cual buscar.
	 * @return Una lista de usuarios intercambiados.
	 * @throws ServiceException 
	 */
	public List<User> getAllPaged(int element, int page) throws ServiceException {
		if(element>0&&page>-1) {
			logger.info("Petici�n realizada correctamente");
			
			return repository.getAllPaged(element, page);
		}else {
			logger.error("El rango de p�ginas no es v�lido");
			
			throw new ServiceException("El rango de p�ginas es invalido");
		}
	}

	/**
	 * Metodo que devuelve un usuario a partir de su codigo.
	 * 
	 * @param code codigo del usuario a recivir
	 * @return Usuario encontrado.
	 * @throws ServiceException 
	 */
	public User getByCode(int code) throws ServiceException {
		if(code>0) {
			logger.info("Petici�n realizada correctamente");
			
			return repository.getByCode(code);
		}else {
			logger.error("El c�digo introducido no es v�lido");
			
			throw new ServiceException("Codigo introducido inv�lido");
		}
	}

	/**
	 * Metodo que devuelve un usuario a partir de su nombre.
	 * 
	 * @param name nombre del usuario a recivir
	 * @return Usuario encontrado.
	 * @throws ServiceException 
	 */
	public User getByName(String name) throws ServiceException {
		if(name!=null) {
			if(!name.equals("")) {
				logger.info("Petici�n realizada correctamente");
				
				return repository.getByName(name.toLowerCase());
				
			}else {
				logger.error("El nombre esta vacio");
				
				throw new ServiceException("El nombre esta vacio");
			}
		}else {
			logger.error("El nombre es nulo");
			
			throw new ServiceException("El nombre es nulo");
		}
	}

	/**
	 * Devuelve una lista de usuarios paginada en funci�n de la p�gina que se est�
	 * buscando y si es administrador.
	 * 
	 * @param administrator booleano para identificar el rol del usuario
	 * @param element       n� de elementos a buscar
	 * @param page          n� de p�gina a partir del cual buscar.
	 * @return Una lista de usuarios intercambiados.
	 * @throws ServiceException 
	 */
	public List<User> getByAdministratorPaged(Boolean administrator, int element, int page) throws ServiceException {
		if(element>0&&page>-1) {
			logger.info("Petici�n realizada correctamente");
			
			return repository.getAllAdminPaged(administrator, element, page);
		}else {
			logger.error("El rango introducido no es v�lido");
			
			throw new ServiceException("El rango introducido no es v�lido");
		}
		
	}
}
