package controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.RecordNotFoundException;
import models.Agency;
import services.AgencyService;

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
	 * @param page el nº de pagina por el que empieza la paginación.
	 * @return la respuesta HTTP con la lista de agencias paginadas.
	 */
	@GetMapping("/page/{page}")
	public ResponseEntity<List<Agency>> getAllPaged(@PathVariable("page") int page){
		List<Agency> paged=service.getAllPaged(page);
		return new ResponseEntity<List<Agency>>(paged,new HttpHeaders(),HttpStatus.OK);
	}
	
	/**
	 * Devuelve una respuesta HTTP con una agencia filtrada por id.
	 * 
	 * @param id la id por la que se filtrará la agencia.
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
	 * @param page paginar para empezar la paginación.
	 * @return respuesta con lista de agencias paginada y filtrada por username.
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/username/{username}/page/{page}")
	public ResponseEntity<List<Agency>> getByUsernamePaged(
			@PathVariable("username") String username, 
			@PathVariable("page") int page)
					throws RecordNotFoundException{
		
		List<Agency> result=service.getByUsernamePaged(username,page);
		return new ResponseEntity<List<Agency>>(result,new HttpHeaders(),HttpStatus.OK);
	}
	
	/**
	 * Devuelve una respuesta HTTP con una lista de agencias paginadas y filtradas por active 
	 * dependiendo de si la consulta se ha realizado correctamente o no.
	 * 
	 * @param active boolean con el parametro active para filtrar.
	 * @param page pagina por la cual empieza la paginación.
	 * @return respuesta con lista de agencias paginada y filtrada por active.
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/active/{active}/page/{page}")
	public ResponseEntity<List<Agency>> getByActivePaged(
			@PathVariable("active") boolean active,
			@PathVariable("page") int page)
					throws RecordNotFoundException {
		
		List<Agency> result=service.getByActivePaged(active,page);
		return new ResponseEntity<List<Agency>>(result,new HttpHeaders(),HttpStatus.OK);
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
		
		Agency ag=service.createOrUpdate(a);
		return new ResponseEntity<Agency>(ag,new HttpHeaders(),HttpStatus.OK);
	}
	
	/**
	 * Recibe una agencia y devuelve una respuesta HTTP en función de si ha podido eliminarla
	 * correctamente.
	 * 
	 * @param a agencia a eliminar recibida en el cuerpo.
	 * @return respuesta http sobre el status de la petición.
	 * @throws RecordNotFoundException
	 */
	@DeleteMapping()
	public HttpStatus delete(@Valid @RequestBody Agency a) throws RecordNotFoundException {
		service.delete(a);
		return HttpStatus.OK;
	}
}
