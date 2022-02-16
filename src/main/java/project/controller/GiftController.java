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
import project.models.Gift;
import project.services.GiftService;

@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/gifts")
public class GiftController {

	@Autowired
	GiftService service;
	
	/**
	 * Devuelve una respuesta HTTP con una lista de regalos dependiendo de si la consulta se
	 * ha realizado correctamente o no.
	 * 
	 * @return la respuesta HTTP con la lista de regalos.
	 */
	@GetMapping
	public ResponseEntity<List<Gift>> getAll(){
		List<Gift> all=service.getAll();
		return new ResponseEntity<List<Gift>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	
	/**
	 * Devuelve una respuesta HTTP con una lista de regalos paginada dependiendo de si la
	 * consulta se ha realizado correctamente o no.
	 * 
	 * @param element n� de elementos a buscar.
	 * @param page el n� de pagina por el que empieza la paginaci�n.
	 * @return la respuesta HTTP con la lista de regalos paginada.
	 */
	@GetMapping("/element/{element}/page/{page}")
	public ResponseEntity<List<Gift>> getAllPaged(@PathVariable("element") int element, @PathVariable("page") int page){
		List<Gift> paged;
		
		try {
			
			paged = service.getAllPaged(element, page);
			
			return new ResponseEntity<List<Gift>>(paged,new HttpHeaders(),HttpStatus.OK);
		
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<Gift>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Devuelve una respuesta HTTP con un regalo filtrado por id.
	 * 
	 * @param id la id por la que se filtrar� el regalo.
	 * @return respuesta con el regalo encontrado con ese id.
	 * @throws RecordNotFoundException
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<Gift> getById(@PathVariable("id") Long id){
		try {
			
			Gift result=service.getById(id);
			
			return new ResponseEntity<Gift>(result,new HttpHeaders(),HttpStatus.OK);
			
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			
			return new ResponseEntity<Gift>(HttpStatus.BAD_REQUEST);
		
		}
	}
	
	/**
	 * Devuelve una respuesta HTTP con una lista de regalos paginada filtrada por nombre
	 * dependiendo de si la consulta se ha realizado correctamente o no.
	 * 
	 * @param name nombre por el cual filtrar la b�squeda.
	 * @param element n� de elementos a buscar.
	 * @param page p�gina para empezar la paginaci�n en la b�squeda.
	 * @return lista de regalos paginada y filtrada por nombre
	 */
	@GetMapping("/name/{name}/element/{element}/page/{page}")
	public ResponseEntity<List<Gift>> getByNamePaged(@PathVariable("name")String name, @PathVariable("element") int element, @PathVariable("page") int page){
		List<Gift> result;
		try {
			
			result = service.getByNamePaged(name,element,page);
			
			return new ResponseEntity<List<Gift>>(result,new HttpHeaders(),HttpStatus.OK);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<Gift>>(HttpStatus.BAD_REQUEST);		
			
		}
	}
	
	/**
	 * Devuelve una respuesta HTTP con una lista de regalos paginada filtrada por
	 * disponibilidadvdependiendo de si la consulta se ha realizado correctamente o no.
	 * 
	 * @param avaliable disponibilidad para filtrar la b�squeda.
	 * @param element n� de elementos a buscar.
	 * @param page p�gina para empezar la paginaci�n en la b�squeda.
	 * @return lista de regalos paginada y filtrada por nombre
	 */
	@GetMapping("/available/{available}/element/{element}/page/{page}")
	public ResponseEntity<List<Gift>> getByAvaliablePaged(@PathVariable("available")boolean avaliable, @PathVariable("element") int element, @PathVariable("page") int page) {
		
		List<Gift> result;
		
		try {
			
			result = service.getByAvaliablePaged(avaliable,element, page);
			
			return new ResponseEntity<List<Gift>>(result,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<Gift>>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Recibe un regalo. Crea o updatea el regalo.
	 * 
	 * @param g regalo a crear o updatear recibido en el cuerpo.
	 * @return respuesta con el regalo actualido o insertado con su id correspondiente.
	 * @throws RecordNotFoundException
	 */
	@PostMapping()
	public ResponseEntity<Gift> createOrUpdate(@Valid @RequestBody Gift g) {
			
		Gift gg;
		
		try {
			gg = service.createOrUpdate(g);
			
			return new ResponseEntity<Gift>(gg,new HttpHeaders(),HttpStatus.OK);
		} catch (RecordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<Gift>(HttpStatus.BAD_REQUEST);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<Gift>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Recibe un regalo y devuelve una respuesta HTTP en funci�n de si ha podido
	 * eliminarlo correctamente.
	 * 
	 * @param g regalo a crear o updatear recibido en el cuerpo.
	 * @return respuesta http sobre el status de la petici�n.
	 * @throws RecordNotFoundException
	 */
	@DeleteMapping()
	public HttpStatus delete(@Valid @RequestBody Gift g) throws RecordNotFoundException {
		
		try {
			service.delete(g);
			
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
