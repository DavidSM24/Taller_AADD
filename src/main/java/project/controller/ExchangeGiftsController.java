	package project.controller;

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

import project.exception.RecordNotFoundException;
import project.models.ExchangeGift;
import project.services.ExchangeGiftService;

@RestController
@RequestMapping("/exchangeGifts")
public class ExchangeGiftsController {
	@Autowired
	ExchangeGiftService service;
	/**
	 * Devuelve una respuesta HTTP con una lista de regalos intercambiados dependiendo de si la consulta se
	 * ha realizado correctamente o no.
	 * 
	 * @return la respuesta HTTP con la lista de regalos intercambiados.
	 */
	@GetMapping
	public ResponseEntity<List<ExchangeGift>> getAll(){
		List<ExchangeGift> all=service.getAll();
		return new ResponseEntity<List<ExchangeGift>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Devuelve una respuesta HTTP con una lista de regalos intercambiados paginados dependiendo de si la consulta se
	 * ha realizado correctamente o no.
	 * 
	 * @param page el n� de pagina por el que empieza la paginaci�n.
	 * @return la respuesta HTTP con la lista de regalos intercambiados.
	 */
	@GetMapping("/element/{element}/page/{page}")
	public ResponseEntity<List<ExchangeGift>> getAllPaged(@PathVariable("element") int element, @PathVariable("page") int page){
		List<ExchangeGift> all=service.getAllPaged(element, page);
		return new ResponseEntity<List<ExchangeGift>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Devuelve una respuesta HTTP con una lista de regalos intercambiados paginado y filtrados
	 * dependiendo de si la consulta se ha realizado correctamente o no.
	 * 
	 * @param element n� de elementos a buscar.
	 * @param page el n� de pagina por el que empieza la paginaci�n.
	 * @param boolean con el parametro deliverd para filtrar.
	 * @return la respuesta HTTP con la lista de regalos intercambiados.
	 */
	@GetMapping("/delivered/{delivered}/element/{element}/paged/{page}")
	public ResponseEntity<List<ExchangeGift>> getByDelivered(@PathVariable("delivered")Boolean isdelivered, @PathVariable("element") int element, @PathVariable("page") int page){
		List<ExchangeGift> all=service.getByDeliveredPaged(isdelivered,element, page);
		return new ResponseEntity<List<ExchangeGift>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Devuelve una respuesta HTTP con una lista de regalos intercambiados paginado y filtrados
	 * dependiendo de si la consulta se ha realizado correctamente o no.
	 * 
	 * @param element n� de elementos a buscar.
	 * @param page el n� de pagina por el que empieza la paginaci�n.
	 * @param id_agency agencia por la que se filtrar.
	 * @return la respuesta HTTP con la lista de regalos intercambiados.
	 */
	@GetMapping("/id_agency/{id_agency}/element/{element}/paged/{page}")
	public ResponseEntity<List<ExchangeGift>> getByAgency(@PathVariable("id_agency")int agency,@PathVariable("element") int element, @PathVariable("page") int page){
		List<ExchangeGift> all=service.getByAgencyPaged(agency,element,page);
		return new ResponseEntity<List<ExchangeGift>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	
	/**
	 * Devuelve una respuesta HTTP con un regalo intercambiado filtrada por id.
	 * 
	 * @param id la id por la que se filtrar� del regalos intercambiados.
	 * @return respuesta con el regalos intercambiados encontrada con ese id.
	 * @throws RecordNotFoundException
	 */
	@GetMapping("id/{id}")
	public ResponseEntity<ExchangeGift> getByID(@PathVariable("id")Long id){
		ExchangeGift gift=service.getbyId(id);
		return new ResponseEntity<ExchangeGift>(gift,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Recibe un regalo intercambiado. Crea o updatea un regalo intercambiado.
	 * 
	 * @param a regalo intercambiado a crear o updatear recibida en el cuerpo.
	 * @return respuesta con el regalo intercambiado actualido o insertado con su id correspondiente.
	 * @throws RecordNotFoundException
	 */
	@PostMapping()
	public ResponseEntity<ExchangeGift> createorUpdateUser(@Valid @RequestBody ExchangeGift ex){
		ExchangeGift gift=service.createorupdate(ex);
		return new ResponseEntity<ExchangeGift>(gift,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Recibe un regalo intercambiado y devuelve una respuesta HTTP en funci�n de si ha podido eliminarla
	 * correctamente.
	 * 
	 * @param a regalo intercambiado a eliminar recibida en el cuerpo.
	 * @return respuesta http sobre el status de la petici�n.
	 * @throws RecordNotFoundException
	 */
	@DeleteMapping()
	public HttpStatus delete(@Valid @RequestBody ExchangeGift ex) throws RecordNotFoundException{
		service.delete(ex);
		return HttpStatus.OK;
	}
}
