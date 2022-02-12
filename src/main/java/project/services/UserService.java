package project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.exception.RecordNotFoundException;
import project.models.Agency;
import project.models.User;
import project.repositories.UserRepository;

@Service
public class UserService {
	/**
	 * Repositorio de Usuarios asociado a este servicio.
	 */
	@Autowired
	UserRepository repository;

	/**
	 * Método que devuelve todos los Usuarios.
	 * 
	 * @return lista de usuarios
	 */
	public List<User> getall() {
		return repository.findAll();
	}

	/***
	 * Método para conseguir un usuario a partir de su id. Recibe un Long.
	 * Posibilidad de dar una excepción NotFound.
	 *
	 * @param id
	 * @return el usuario con ese id
	 * @throws RecordNotFoundException
	 */
	public User getbyId(Long id) throws RecordNotFoundException {
		Optional<User> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new RecordNotFoundException("User no existe", id);
		}

	}

	/***
	 * Método para insertar o actualizar un usuario dependiendo de si existe un
	 * registro con este id en la BBDD. Lanza una excepción si no se encuentra al
	 * usuario en la BBDD.
	 * 
	 * @param Agency: El usuario a actualizar/insertar.
	 * @return Devuelve el usuario con el id generado.
	 * @throws RecordNotFoundException
	 */
	public User createorupdate(User user) throws RecordNotFoundException {
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
				
				newUser=repository.save(newUser);
				return newUser;
			
			} else { // insert
				user.setId(null);
				user = repository.save(user);
				return user;
			}

		}
		
		else {
			user=repository.save(user);
			return user;
		}
	}

	/***
	 * Método que recibe un usuario y la elimina de la BBDD. Lanza una excepción si
	 * no se encuentra el usuario en la BBDD.
	 * 
	 * @param Agency: El Usuario a eliminar.
	 * @return Devuelve true si el usuario se ha borrado False si no.
	 * @throws RecordNotFoundException
	 */
	public boolean delete(User user) throws RecordNotFoundException {
		boolean result = false;
		Optional<User> optional = repository.findById(user.getId());
		if (optional.isPresent()) {
			repository.deleteById(user.getId());
			result = true;
		} else {
			result = false;
			throw new RecordNotFoundException("El usuario no existe", user.getId());
		}
		return result;
	}

	/**
	 * Devuelve una lista de usuarios paginada en función de la página que se está
	 * buscando.
	 * 
	 * @param element nº de elementos a buscar
	 * @param page    nº de página a partir del cual buscar.
	 * @return Una lista de usuarios intercambiados.
	 */
	public List<User> getAllPaged(int element, int page) {
		return repository.getAllPaged(element, page);
	}

	/**
	 * Metodo que devuelve un usuario a partir de su codigo.
	 * 
	 * @param code codigo del usuario a recivir
	 * @return Usuario encontrado.
	 */
	public User getByCode(int code) {
		return repository.getByCode(code);
	}

	/**
	 * Metodo que devuelve un usuario a partir de su nombre.
	 * 
	 * @param name nombre del usuario a recivir
	 * @return Usuario encontrado.
	 */
	public User getByName(String name) {
		System.out.println(repository.getByName(name.toLowerCase()));
		return repository.getByName(name.toLowerCase());
	}

	/**
	 * Devuelve una lista de usuarios paginada en función de la página que se está
	 * buscando y si es administrador.
	 * 
	 * @param administrator booleano para identificar el rol del usuario
	 * @param element       nº de elementos a buscar
	 * @param page          nº de página a partir del cual buscar.
	 * @return Una lista de usuarios intercambiados.
	 */
	public List<User> getByAdministratorPaged(Boolean administrator, int element, int page) {
		return repository.getAllAdminPaged(administrator, element, page);
	}
}
