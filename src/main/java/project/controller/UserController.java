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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.User;
import project.services.UserService;

@CrossOrigin(origins = "*")
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
	@ApiOperation(value = "Return all Users", notes="Return a Users List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get Users"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> all=service.getall();
		return new ResponseEntity<List<User>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Devuelve una respuesta HTTP con una lista de usuarios paginados dependiendo de si la consulta se
	 * ha realizado correctamente o no.
	 * 
	 * @param element n� de elementos a buscar.
	 * @param page el n� de pagina por el que empieza la paginaci�n.
	 * @return la respuesta HTTP con la lista de usuarios.
	 */
	@ApiOperation(value = "Return all Users Paged", notes="Return a Users List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get Users"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/element/{element}/page/{page}")
	public ResponseEntity<List<User>> getAllUsersPaged(@PathVariable("element") int element,@PathVariable("page")int page) throws RecordNotFoundException{
		List<User> all;
		
		try {
			all = service.getAllPaged(element,page);
			
			return new ResponseEntity<List<User>>(all,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Devuelve una respuesta HTTP con una lista de usuarios paginado y filtrados
	 * dependiendo de si la consulta se ha realizado correctamente o no.
	 * 
	 * @param element n� de elementos a buscar.
	 * @param page el n� de pagina por el que empieza la paginaci�n.
	 * @param administrator booleano para identificar si es admnistrador o no.
	 * @return la respuesta HTTP con la lista de usuarios.
	 */
	@ApiOperation(value = "Return all Users Paged filtered if they are administrator", notes="Return a Users List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get Users"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/administrator/{administrator}/element/{element}/page/{page}")
	public ResponseEntity<List<User>> getByAdministratorPaged(@PathVariable("administrator")Boolean administrator,@PathVariable("element") int element,@PathVariable("page")int page) throws RecordNotFoundException{
		List<User> all;
		try {
			all = service.getByAdministratorPaged(administrator,element, page);
			
			
			return new ResponseEntity<List<User>>(all,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * Devuelve una respuesta HTTP con un usuario filtrada por id.
	 * 
	 * @param id la id por la que se filtrar� el usuario.
	 * @return respuesta con el usuario encontrada con ese id.
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Return a User filtered by ID", notes="Return a User")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get User"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/id/{id}")
	public ResponseEntity<User> getUserByID(@PathVariable("id")Long id){
		try {
			User user=service.getbyId(id);
			
			return new ResponseEntity<User>(user,new HttpHeaders(),HttpStatus.OK);
			
		} catch (RecordNotFoundException e) {
			
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * Devuelve una respuesta HTTP con un usuario filtrada por nombre.
	 * 
	 * @param name el nombre por el que se filtrar� el usuario.
	 * @return respuesta con el usuario encontrada con ese nombre.
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Return a User filtered by Name", notes="Return a User")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get User"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/name/{name}")
	public ResponseEntity<List<User>> getUserByName(@PathVariable("name")String name) throws RecordNotFoundException{
		List<User> user;
		try {
			user = service.getByName(name.toLowerCase());
			
			return new ResponseEntity<List<User>>(user,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Devuelve una respuesta HTTP con un usuario filtrada por codigo.
	 * 
	 * @param code el codigo por el que se filtrar� el usuario.
	 * @return respuesta con el usuario encontrada con ese nombre.
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Return a User filtered by Code", notes="Return a User")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get User"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/code/{code}")
	public ResponseEntity<User> getUserByName(@PathVariable("code")int code) throws RecordNotFoundException{
		User user;
		try {
			user = service.getByCode(code);

			return new ResponseEntity<User>(user,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}

    /**
     * Devuelve una respuesta HTTP con un usuario filtrada por mail.
     *
     * @param mail el mail por el que se filtrar� el usuario.
     * @return respuesta con el usuario encontrada con ese nombre.
     * @throws RecordNotFoundException
     */
    @ApiOperation(value = "Return a User filtered by mail", notes="Return a User")
    @ApiResponses(value = {
            @ApiResponse(code=200,message="Successful Operation"),
            @ApiResponse(code=400,message="Bad Request"),
            @ApiResponse(code=404,message="ERROR, Can't get User"),
            @ApiResponse(code=500,message="Internal Error"),
    })
    @GetMapping("/mail/{mail}")
    public ResponseEntity<List<User>> getByMail(@PathVariable("mail")String mail) throws RecordNotFoundException{
        List<User>result;
        try {
            result = service.getByMail(mail.toLowerCase());

            return new ResponseEntity<List<User>>(result,new HttpHeaders(),HttpStatus.OK);
        } catch (ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
        }
    }

	/**
	 * Devuelve una respuesta HTTP con una lista de Usuarios disponibles dependiendo de si la consulta
	 * para asignar a agencias, y de  si la consulta se ha realizado correctamente o no.
	 * 
	 * @return la respuesta HTTP con la lista de usuarios disponibles.
	 */
	@ApiOperation(value = "Return available Users", notes="Return a Users List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get Users"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/available")
	public ResponseEntity<List<User>> getByAvailable(){
		List<User> all=service.getByAvailable();
		return new ResponseEntity<List<User>>(all,new HttpHeaders(),HttpStatus.OK);
	}


	
	/**
	 * Recibe un usuario. Crea o updatea un usuario.
	 * 
	 * @param u usuario a crear o updatear recibida en el cuerpo.
	 * @return respuesta con el usuario actualido o insertado con su id correspondiente.
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Create or Update a new User", notes="Return a User")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, It was not possible to create or update"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@PostMapping
	public ResponseEntity<User> createorUpdateUser(@Valid @RequestBody User u) throws RecordNotFoundException{
		User user;
		try {
			user = service.createorupdate(u);
			
			return new ResponseEntity<User>(user,new HttpHeaders(),HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	/**
	 * Recibe un usuario y devuelve una respuesta HTTP en funci�n de si ha podido eliminarla
	 * correctamente.
	 * 
	 * @param u a eliminar recibida en el cuerpo.
	 * @return respuesta http sobre el status de la petici�n.
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Delete a User")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, It was not possible to delete"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@DeleteMapping
	public HttpStatus deleteUserById(@Valid @RequestBody User u) throws RecordNotFoundException {
		try {
			if(service.delete(u)) {
				return HttpStatus.OK;
				
			}else {
				return HttpStatus.BAD_REQUEST;
			}
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return HttpStatus.BAD_REQUEST;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return HttpStatus.BAD_REQUEST;
		}
	}
}
