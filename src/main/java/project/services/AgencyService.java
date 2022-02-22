package project.services;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.Agency;
import project.repositories.AgencyRepository;

@Service
public class AgencyService {

	//Log4j2
	private static final Logger logger=LoggerFactory.getLogger(AgencyService.class);
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
	 * Devuelve una lista de agencias paginada en función de la página que se está
	 * buscando.
	 * 
	 * @param element nº de elementos a buscar
	 * @param page    nº de página a partir del cual buscar.
	 * @return Una lista de agencias.
	 */
	public List<Agency> getAllPaged(int element, int page) {

		return repository.getAllPaged(element, page);
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
		if (id != null) {
			if (id > 0) {

				Optional<Agency> result = repository.findById(id);

				if (result.isPresent()) {
					logger.info("Onbenido con éxito");
					return result.get();

				} else {
					logger.error("La agencia buscada no existe, en getById");
					throw new RecordNotFoundException("La agencia no existe", id);
				}
			} else {
				logger.error("El id "+id+" no es válido");
				throw new RecordNotFoundException("El id introducido no es valido", id);
			}

		} else {
			logger.error("El id introducido es nulo");
			throw new RecordNotFoundException("El id introducido es nulo");
		}

	}

	/**
	 * Devuelve una lista paginada de agencias cuyo nombre de usuario contenga el
	 * parametro username.
	 * 
	 * @param username el nombre del usuario.
	 * @param element  nº de elementos a buscar
	 * @param page     comienzo de la paginación.
	 * @return La lista paginada y filtrada de agencias.
	 * @throws ServiceException
	 */
	public List<Agency> getByUsernamePaged(String userName, int element, int page) throws ServiceException {
		if (userName != null) {
			if (!userName.equals("")) {
				if (element > 0 && page > 0) {

					return repository.getByUsernamePaged(userName.toLowerCase(), element, page);

				} else {
					throw new ServiceException("Los números introducidos para el paginádo no son válidos");
				}

			} else {
				throw new ServiceException("El nombre del usuario no es valido");
			}

		} else {
			throw new ServiceException("El nomnbre del usuario es nulo");
		}

	}

	/**
	 * Devuelve todas las agencias que coincidan con el parámetro isActive
	 * paginadas, pudiendo ser las que estén o no activas en función de lo que se
	 * reciba.
	 * 
	 * @param active  boolean con el parámetro para filtrar por active/inactive
	 * @param element nº de elementos a buscar
	 * @param page    pagina por la que se empieza a paginar
	 * @return Lista de agencias paginada y flitrada por isActive.
	 * @throws ServiceException
	 */
	public List<Agency> getByActivePaged(boolean active, int element, int page) throws ServiceException {
		if (element > 0 && page > 0) {

			return repository.getByActivePaged(active, element, page);

		} else {
			throw new ServiceException("Los números introducidos para el paginado no son válidos");
		}
	}

	/***
	 * Método para insertar o actualizar una agencia dependiendo de si existe un
	 * registro con este id en la BBDD. Lanza una excepción si no se encuentra la
	 * agencia en la BBDD.
	 * 
	 * @param Agency: La agencia a actualizar/insertar.
	 * @return Devuelve la agencia con el id generado.
	 * @throws RecordNotFoundException
	 * @throws ServiceException
	 */

	public Agency createOrUpdate(Agency agency) throws RecordNotFoundException, ServiceException {


		if (agency != null) {
			if (agency.getAddress() != null && !agency.getAddress().equals("")) {
				if (agency.getLocation() != null && !agency.getLocation().equals("")) {
					if (agency.getPoints() > -1) {
						if (agency.getPointsRedeemed() > -1) {
							if (agency.getPhoneNumber() != null) {
								if (agency.getAmount() > 0) {
									if (agency.getZipCode() != null && agency.getZipCode() > 0) {
										if (agency.getMyUser() != null) {
											if (agency.getMyInsuranceCompany() != null) {
												if (agency.getMyCarRepairs() != null) {
													if (agency.getMyExchangesGifts() != null) {
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
																newAgency.setPoints(agency.getPoints());
																newAgency.setPointsRedeemed(agency.getPointsRedeemed());
																newAgency.setActive(agency.isActive());
																newAgency.setMyInsuranceCompany(
																		agency.getMyInsuranceCompany());
																newAgency.setMyCarRepairs(agency.getMyCarRepairs());
																newAgency.setMyExchangesGifts(
																		agency.getMyExchangesGifts());
																newAgency.setMyUser(agency.getMyUser());

																newAgency = repository.save(newAgency);
																return newAgency;

															} else { // insert
																agency.setId(null);
																agency = repository.save(agency);
																return agency;
															}

														} else {
															agency = repository.save(agency);
															return agency;
														}

													} else {//lista de regalos
														throw new ServiceException("La lista de regalos es nula");
													}
												} else {//lista de reparacioones
													throw new ServiceException("La lista de reparaciones es nula");
												}
											} else {//compañia de seguros
												throw new ServiceException("La compañia de seguros es nula");
											}
										} else {//usuario
											throw new ServiceException("El usuario es nulo");
										}
									} else {//código postal
										throw new ServiceException("El código postal introducido no es válido");
									}
								} else {//total
									throw new ServiceException("El total no es válido");
								}
							}else {//telefono
								throw new ServiceException("El teléfono introducido no es válido");
							}
						} else {//puntos gastados
							throw new ServiceException("Los puntos gastados no son válidos");
						}
					} else {//puntos
						throw new ServiceException("Los puntos introducidos no son válidos");
					}
				} else {//locaclización
					throw new ServiceException("La localización no es correcta");
				}

			} else {//direccion
				throw new ServiceException("La dirección introducida no es correcta");
			}
		} else {//agencia
			throw new ServiceException("La agencia introducida no es válida");
		}

	}

	/***
	 * Método que recibe una agencia y la elimina de la BBDD. Lanza una excepción si
	 * no se encuentra la agencia en la BBDD.
	 * 
	 * @param Agency: La agencia a eliminar.
	 * @throws RecordNotFoundException
	 * @throws ServiceException 
	 */
	public void delete(Agency agency) throws RecordNotFoundException, ServiceException {
		
		if(agency!=null) {
			
			Optional<Agency> optional = repository.findById(agency.getId());
			
			if (optional.isPresent()) {
				repository.deleteById(agency.getId());
			} else {
				throw new RecordNotFoundException("La agencia no existe", agency.getId());
			}
			
		}else {
			throw new ServiceException("La agencia introducida es nula");
		}
	}
}
