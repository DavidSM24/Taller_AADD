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
import project.exception.ServiceException;
import project.models.Agency;
import project.services.AgencyService;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/agencies")
public class AgencyController {
	
	@Autowired
	AgencyService service;
	
	/**
	 * Devuelve una respuesta HTTP con una lista de agencias dependiendo de si la consulta se
	 * ha realizado correctamente o no.
	 * 
	 * @return la respuesta HTTP con la lista de agencias.
	 */
	@GetMapping
	public ResponseEntity<List<Agency>> getAll(){
		List<Agency> all=service.getAll();
		return new ResponseEntity<List<Agency>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	
	/**
	 * Devuelve una respuesta HTTP con una lista de agencias paginadas dependiendo de si la
	 * consulta se ha realizado correctamente o no.
	 * 
	 * @param element n� de elementos a buscar.
	 * @param page el n� de pagina por el que empieza la paginaci�n.
	 * @return la respuesta HTTP con la lista de agencias paginadas.
	 */
	@GetMapping("/element/{element}/page/{page}")
	public ResponseEntity<List<Agency>> getAllPaged(@PathVariable("element") int element, @PathVariable("page") int page){
		List<Agency> paged=service.getAllPaged(element,page);
		return new ResponseEntity<List<Agency>>(paged,new HttpHeaders(),HttpStatus.OK);
	}
	
	/**
	 * Devuelve una respuesta HTTP con una agencia filtrada por id.
	 * 
	 * @param id la id por la que se filtrar� la agencia.
	 * @return respuesta con la agencia encontrada con ese id.
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<Agency> getById(@PathVariable("id") Long id)
			throws RecordNotFoundException{
		
		Agency result=service.getById(id);
		return new ResponseEntity<Agency>(result,new HttpHeaders(),HttpStatus.OK);
	}
	
	/**
	 * Devuelve una respuesta HTTP con una lista de agencias paginadas y filtradas por username 
	 * dependiendo de si la consulta se ha realizado correctamente o no.
	 * 
	 * @param username nombre por el que filtrar.
	 * @param element n� de elementos a buscar.
	 * @param page paginar para empezar la paginaci�n.
	 * @return respuesta con lista de agencias paginada y filtrada por username.
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/username/{username}/element/{element}/page/{page}")
	public ResponseEntity<List<Agency>> getByUsernamePaged(
			@PathVariable("username") String username,
			@PathVariable("element") int element,
			@PathVariable("page") int page)
					throws RecordNotFoundException{
		
		List<Agency> result;
		try {
			result = service.getByUsernamePaged(username,element,page);
			return new ResponseEntity<List<Agency>>(result,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<Agency>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Devuelve una respuesta HTTP con una lista de agencias paginadas y filtradas por active 
	 * dependiendo de si la consulta se ha realizado correctamente o no.
	 * 
	 * @param active boolean con el parametro active para filtrar.
	 * @param element n� de elementos a buscar.
	 * @param page pagina por la cual empieza la paginaci�n.
	 * @return respuesta con lista de agencias paginada y filtrada por active.
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/active/{active}/element/{element}/page/{page}")
	public ResponseEntity<List<Agency>> getByActivePaged(
			@PathVariable("active") boolean active,
			@PathVariable("element") int element,
			@PathVariable("page") int page)
					throws RecordNotFoundException {
		
		List<Agency> result;
		try {
			result = service.getByActivePaged(active,element,page);
			return new ResponseEntity<List<Agency>>(result,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<List<Agency>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Recibe una agencia. Crea o updatea una agencia.
	 * 
	 * @param a agencia a crear o updatear recibida en el cuerpo.
	 * @return respuesta con la nota actualida o insertada con su id correspondiente.
	 * @throws RecordNotFoundException
	 */
	@PostMapping()
	public ResponseEntity<Agency> createOrUpdate(@Valid @RequestBody Agency a) 
			throws RecordNotFoundException {
		
		Agency ag;
		try {
			ag = service.createOrUpdate(a);
			return new ResponseEntity<Agency>(ag,new HttpHeaders(),HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Agency>(HttpStatus.BAD_REQUEST);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Agency>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Recibe una agencia y devuelve una respuesta HTTP en funci�n de si ha podido eliminarla
	 * correctamente.
	 * 
	 * @param a agencia a eliminar recibida en el cuerpo.
	 * @return respuesta http sobre el status de la petici�n.
	 * @throws RecordNotFoundException
	 */
	@DeleteMapping()
	public HttpStatus delete(@Valid @RequestBody Agency a) throws RecordNotFoundException {
		try {
			service.delete(a);
			return HttpStatus.OK;
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
