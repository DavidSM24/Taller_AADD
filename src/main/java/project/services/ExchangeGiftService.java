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
import project.models.Agency;
import project.models.ExchangeGift;
import project.repositories.ExchangeGiftRepository;

@Service
public class ExchangeGiftService {
	
	//log4j2
		private static final Logger logger=LoggerFactory.getLogger(ExchangeGift.class);
	/**
	 * Repositorio del regalo intercambiado asociado a este servicio.
	 */
	@Autowired
	ExchangeGiftRepository repository;
	@Autowired
	AgencyService agencyService;

	/**
	 * Método que devuelve todos los regalos intercambiado.
	 * 
	 * @return lista de los regalos intercambiado
	 */
	public List<ExchangeGift> getAll() {
		logger.info("Petición realizada correctamente");
		
		return repository.findAll();
	}

	/***
	 * Método para conseguir un regalo intercambiado a partir de su id. Recibe un
	 * Long. Posibilidad de dar una excepción NotFound.
	 *
	 * @param id
	 * @return el regalo intercambiado con ese id
	 * @throws RecordNotFoundException
	 */
	public ExchangeGift getbyId(Long id) {
		if (id != null) {
			if (id > -1) {

				Optional<ExchangeGift> result = repository.findById(id);

				if (result.isPresent()) {
					logger.info("Petición realizada correctamente");
					
					return result.get();

				} else {
					logger.error("No se encuentra el ExchangeGift con el id "+id+" En la base de datos");
					
					throw new RecordNotFoundException("ExchangeGift no existe", id);
				}

			} else {
				logger.error("El id introducido es menor a -1");
				
				throw new RecordNotFoundException("El id introducido no es valido");
			}
		} else {
			logger.error("El id introducido es nulo");
			
			throw new RecordNotFoundException("El id introducido es nulo", id);
		}

	}

	/***
	 * Método para insertar o actualizar un regalo intercambiado dependiendo de si
	 * existe un registro con este id en la BBDD. Lanza una excepción si no se
	 * encuentra al regalo intercambiado en la BBDD.
	 * 
	 * @param Agency: El regalo intercambiado a actualizar/insertar.
	 * @return Devuelve el regalo intercambiado con el id generado.
	 * @throws ServiceException
	 * @throws RecordNotFoundException
	 */
	public ExchangeGift createorupdate(ExchangeGift exgift) throws ServiceException {

		if (exgift != null) {
			if (exgift.getDateExchange() != null && exgift.getAgency() != null && exgift.getGift() != null) {
				if (exgift.getId() != null && exgift.getId() > 0) {
					
					Optional<ExchangeGift> e = repository.findById(exgift.getId());

					if (e.isPresent()) { // update
						ExchangeGift newExchange = e.get();

						newExchange.setId(exgift.getId());
						newExchange.setDateExchange(exgift.getDateExchange());
						newExchange.setObservations(exgift.getObservations());
						newExchange.setDelivered(exgift.isDelivered());
						newExchange.setAgency(exgift.getAgency());
						newExchange.setGift(exgift.getGift());
						
						newExchange = repository.save(newExchange);
						logger.info("Petición realizada correctamente");
						
						return newExchange;

					} else { // insert
						exgift.setId(null);
						exgift = repository.save(exgift);
						logger.info("Petición realizada correctamente");
						
						return exgift;
					}

				}

				else {
					exgift = repository.save(exgift);
					logger.info("Petición realizada correctamente");
					
					return exgift;
				}

			} else {
				logger.error("Los atributos introducidos no están dentro de las características pedidas");
				
				throw new ServiceException("Algo ha fallado, buscate la vida");
			}
		} else {
			logger.error("El pedido es nulo");
			
			throw new ServiceException("El pedido es nulo");
		}

	}

	/***
	 * Método que recibe un regalo intercambiado y la elimina de la BBDD. Lanza una
	 * excepción si no se encuentra el regalo intercambiado en la BBDD.
	 * 
	 * @param Agency: El regalo intercambiado a eliminar.
	 * @return Devuelve true si el regalo intercambiado se ha borrado False si no.
	 * @throws RecordNotFoundException
	 * @throws ServiceException 
	 */
	public boolean delete(ExchangeGift gift) throws RecordNotFoundException, ServiceException {
		
		boolean result = false;
		
		if(gift!=null) {
			if(gift.getId()!=null&&gift.getId()>-1) {
				
				Optional<ExchangeGift> optional = repository.findById(gift.getId());
				
				if (optional.isPresent()) {
					repository.deleteById(gift.getId());
					result = true;
				} else {
					result = false;
					logger.error("El ExchangeGift introducido no esta registrado en la base de datos");
					
					throw new RecordNotFoundException("El regalo intercambiado no existe", gift.getId());
				}
				
			}else {
				result = false;
				logger.error("El id "+gift.getId()+" No se encuentra en la base de datos");
				
				throw new RecordNotFoundException("El id introducido no es válido",gift.getId());
			}
		}else {
			result = false;
			logger.error("EL pedido introducido es nulo");
			
			throw new ServiceException("El pedido introducido es nulo");
		}
		logger.info("Petición realizada correctamente");
		
		return result;
	}

	/**
	 * Devuelve una lista de regalos intercambiados paginada en función de la página
	 * que se está buscando.
	 * 
	 * @param element nº de elementos a buscar
	 * @param page    nº de página a partir del cual buscar.
	 * @return Una lista de regalos intercambiados intercambiados.
	 * @throws ServiceException 
	 */
	public List<ExchangeGift> getAllPaged(int element, int page) throws ServiceException {
		if(element>0&&page>-1) {
			logger.info("Petición realizada correctamente");
			
			return repository.getAllPaged(element, page);
			
		}else {
			logger.error("El número de elementos introducidos no es correcto");
			
			throw new ServiceException("El número de elementos introducido no es correcto");
		}
	}

	/**
	 * Devuelve todos los regalos intercambiados que coincidan con el parámetro
	 * isDelivered paginadas, pudiendo ser los que estén o no enviados en función de
	 * lo que se reciba.
	 * 
	 * @param isdelivered boolean con el parámetro para filtrar.
	 * @param element     nº de elementos a buscar
	 * @param page        pagina por la que se empieza a paginar
	 * @return Lista de regalos intercambiados paginados y flitrados por
	 *         isDelivered.
	 * @throws ServiceException 
	 */
	public List<ExchangeGift> getByDeliveredPaged(boolean isdelivered, int element, int page) throws ServiceException {
		if(element>0&&page>-1) {
			logger.info("Petición realizada correctamente");
			
			return repository.getByDeliveredPaged(isdelivered, element, page);
			
		}else {
			logger.error("El número de elementos introducido no es correcto");
			
			throw new ServiceException("El número de elementos introducido no es correcto");
		}
	}

	/**
	 * Devuelve una lista paginada de regalos intercambiados cuyo id de agencia
	 * contenga el parametro agency.
	 * 
	 * @param agency  el id de la agencia.
	 * @param element nº de elementos a buscar
	 * @param page    comienzo de la paginación.
	 * @return La lista paginada y filtrada de regalos intercambiados.
	 * @throws ServiceException 
	 */
	public List<ExchangeGift> getByAgencyPaged(int agency, int element, int page) throws ServiceException {
		if(agency>-1) {
			if(element>0&&page>-1) {
				logger.info("Petición realizada correctamente");
				
				return repository.getByAgencyPaged(agency, element, page);
				
			}else {
				logger.error("El número de elementos introducido no es correcto");
				
				throw new ServiceException("El número de elementos introducido no es correcto");
			}
			
		}else {
			logger.error("El id de la agencia es incorrecto");
			
			throw new ServiceException("El id de la agencia es incorrecto");
		}
	}
	
	public boolean sumPoints(Agency agency,long points) throws ServiceException {
		if(agency!=null) {
			if(agency.getId()!=null&&agency.getId()>0) {
				if(points>0) {
					agency.setPoints(agency.getPoints()+points);
					
					agencyService.createOrUpdate(agency);
					
					return true;
					
				}else {
					logger.error("Los puntos introducidos son menores que 0");
					
					throw new ServiceException("Los puntos introducidos son menores que 0");
				}
			}else {
				logger.error("El id introducido no es válido");
				
				throw new RecordNotFoundException("El id introducido no es váliso", agency.getId());
			}
			
		}else {
			logger.error("La agencia introducida es nula");
			
			throw new ServiceException("La agencia introducida es nula");
		}
	}
}
