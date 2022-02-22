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
	 * M�todo que devuelve todas las agencias.
	 * 
	 * @return lista de agencias
	 */
	public List<Agency> getAll() {
		return repository.findAll();
	}

	/**
	 * Devuelve una lista de agencias paginada en funci�n de la p�gina que se est�
	 * buscando.
	 * 
	 * @param element n� de elementos a buscar
	 * @param page    n� de p�gina a partir del cual buscar.
	 * @return Una lista de agencias.
	 */
	public List<Agency> getAllPaged(int element, int page) {

		return repository.getAllPaged(element, page);
	}

	/***
	 * M�todo para conseguir una agencia a partir de su id. Recibe un Long.
	 * Posibilidad de dar una excepci�n NotFound.
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
					logger.info("Onbenido con �xito");
					return result.get();

				} else {
					logger.error("La agencia buscada no existe, en getById");
					throw new RecordNotFoundException("La agencia no existe", id);
				}
			} else {
				logger.error("El id "+id+" no es v�lido");
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
	 * @param element  n� de elementos a buscar
	 * @param page     comienzo de la paginaci�n.
	 * @return La lista paginada y filtrada de agencias.
	 * @throws ServiceException
	 */
	public List<Agency> getByUsernamePaged(String userName, int element, int page) throws ServiceException {
		if (userName != null) {
			if (!userName.equals("")) {
				if (element > 0 && page > 0) {

					return repository.getByUsernamePaged(userName.toLowerCase(), element, page);

				} else {
					throw new ServiceException("Los n�meros introducidos para el pagin�do no son v�lidos");
				}

			} else {
				throw new ServiceException("El nombre del usuario no es valido");
			}

		} else {
			throw new ServiceException("El nomnbre del usuario es nulo");
		}

	}

	/**
	 * Devuelve todas las agencias que coincidan con el par�metro isActive
	 * paginadas, pudiendo ser las que est�n o no activas en funci�n de lo que se
	 * reciba.
	 * 
	 * @param active  boolean con el par�metro para filtrar por active/inactive
	 * @param element n� de elementos a buscar
	 * @param page    pagina por la que se empieza a paginar
	 * @return Lista de agencias paginada y flitrada por isActive.
	 * @throws ServiceException
	 */
	public List<Agency> getByActivePaged(boolean active, int element, int page) throws ServiceException {
		if (element > 0 && page > 0) {

			return repository.getByActivePaged(active, element, page);

		} else {
			throw new ServiceException("Los n�meros introducidos para el paginado no son v�lidos");
		}
	}

	/***
	 * M�todo para insertar o actualizar una agencia dependiendo de si existe un
	 * registro con este id en la BBDD. Lanza una excepci�n si no se encuentra la
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
											} else {//compa�ia de seguros
												throw new ServiceException("La compa�ia de seguros es nula");
											}
										} else {//usuario
											throw new ServiceException("El usuario es nulo");
										}
									} else {//c�digo postal
										throw new ServiceException("El c�digo postal introducido no es v�lido");
									}
								} else {//total
									throw new ServiceException("El total no es v�lido");
								}
							}else {//telefono
								throw new ServiceException("El tel�fono introducido no es v�lido");
							}
						} else {//puntos gastados
							throw new ServiceException("Los puntos gastados no son v�lidos");
						}
					} else {//puntos
						throw new ServiceException("Los puntos introducidos no son v�lidos");
					}
				} else {//locaclizaci�n
					throw new ServiceException("La localizaci�n no es correcta");
				}

			} else {//direccion
				throw new ServiceException("La direcci�n introducida no es correcta");
			}
		} else {//agencia
			throw new ServiceException("La agencia introducida no es v�lida");
		}

	}

	/***
	 * M�todo que recibe una agencia y la elimina de la BBDD. Lanza una excepci�n si
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
