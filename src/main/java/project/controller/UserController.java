package project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.exception.RecordNotFoundException;
import project.models.User;
import project.services.UserService;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService service;
	
	/**
	 * Devuelve una respuesta HTTP con una lista de Usuarios dependiendo de si la consulta se
	 * ha realizado correctamente o no.
	 * 
	 * @return la respuesta HTTP con la lista de usuarios.
	 */
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> all=service.getall();
		return new ResponseEntity<List<User>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Devuelve una respuesta HTTP con una lista de usuarios paginados dependiendo de si la consulta se
	 * ha realizado correctamente o no.
	 * 
	 * @param element nº de elementos a buscar.
	 * @param page el nº de pagina por el que empieza la paginación.
	 * @return la respuesta HTTP con la lista de usuarios.
	 */
	@GetMapping("/element/{element}/page/{page}")
	public ResponseEntity<List<User>> getAllUsersPaged(@PathVariable("element") int element,@PathVariable("page")int page) throws RecordNotFoundException{
		List<User> all=service.getAllPaged(element,page);
		return new ResponseEntity<List<User>>(all,new HttpHeaders(),HttpStatus.OK);
	}

	/**
	 * Devuelve una respuesta HTTP con una lista de usuarios paginado y filtrados
	 * dependiendo de si la consulta se ha realizado correctamente o no.
	 * 
	 * @param element nº de elementos a buscar.
	 * @param page el nº de pagina por el que empieza la paginación.
	 * @param administrator booleano para identificar si es admnistrador o no.
	 * @return la respuesta HTTP con la lista de usuarios.
	 */
	@GetMapping("/administrator/{administrator}/element/{element}/page/{page}")
	public ResponseEntity<List<User>> getByAdministratorPaged(@PathVariable("administrator")Boolean administrator,@PathVariable("element") int element,@PathVariable("page")int page) throws RecordNotFoundException{
		List<User> all=service.getByAdministratorPaged(administrator,element, page);
		return new ResponseEntity<List<User>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Devuelve una respuesta HTTP con un usuario filtrada por id.
	 * 
	 * @param id la id por la que se filtrará el usuario.
	 * @return respuesta con el usuario encontrada con ese id.
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<User> getUserByID(@PathVariable("id")Long id) throws RecordNotFoundException{
		User user=service.getbyId(id);
		return new ResponseEntity<User>(user,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Devuelve una respuesta HTTP con un usuario filtrada por nombre.
	 * 
	 * @param name el nombre por el que se filtrará el usuario.
	 * @return respuesta con el usuario encontrada con ese nombre.
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/name/{name}")
	public ResponseEntity<User> getUserByName(@PathVariable("name")String name) throws RecordNotFoundException{
		User user=service.getByName(name.toLowerCase());
		return new ResponseEntity<User>(user,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Devuelve una respuesta HTTP con un usuario filtrada por codigo.
	 * 
	 * @param code el codigo por el que se filtrará el usuario.
	 * @return respuesta con el usuario encontrada con ese nombre.
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/code/{code}")
	public ResponseEntity<User> getUserByName(@PathVariable("code")int code) throws RecordNotFoundException{
		User user=service.getByCode(code);
		return new ResponseEntity<User>(user,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Recibe un usuario. Crea o updatea un usuario.
	 * 
	 * @param a usuario a crear o updatear recibida en el cuerpo.
	 * @return respuesta con el usuario actualido o insertado con su id correspondiente.
	 * @throws RecordNotFoundException
	 */
	@PostMapping
	public ResponseEntity<User> createorUpdateUser(@Valid @RequestBody User u) throws RecordNotFoundException{
		System.out.println(u.getName());
		User user=service.createorupdate(u);
		
		
		return new ResponseEntity<User>(user,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Recibe un usuario y devuelve una respuesta HTTP en función de si ha podido eliminarla
	 * correctamente.
	 * 
	 * @param usuario a eliminar recibida en el cuerpo.
	 * @return respuesta http sobre el status de la petición.
	 * @throws RecordNotFoundException
	 */
	@DeleteMapping
	public HttpStatus deleteUserById(@Valid @RequestBody User u) throws RecordNotFoundException {
		service.delete(u);
		return HttpStatus.OK;
	}
}
