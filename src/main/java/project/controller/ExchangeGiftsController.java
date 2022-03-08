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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import project.exception.RecordNotFoundException;
import project.exception.ServiceException;
import project.models.ExchangeGift;
import project.services.ExchangeGiftService;

@CrossOrigin(origins = "*",methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
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
	@ApiOperation(value = "Return all ExchangesGifts", notes="Return a ExchangesGifts List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get ExchangesGifts"),
			@ApiResponse(code=500,message="Internal Error"),
	})
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
	@ApiOperation(value = "Return all ExchangesGifts Paged", notes="Return a ExchangesGifts List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get ExchangesGifts"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/element/{element}/page/{page}")
	public ResponseEntity<List<ExchangeGift>> getAllPaged(@PathVariable("element") int element, @PathVariable("page") int page){
		List<ExchangeGift> all;
		try {
			all = service.getAllPaged(element, page);
			
			return new ResponseEntity<List<ExchangeGift>>(all,new HttpHeaders(),HttpStatus.OK);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<ExchangeGift>>(HttpStatus.BAD_REQUEST);
		}
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
	@ApiOperation(value = "Return all ExchangesGifts Paged by delivered", notes="Return a ExchangesGifts List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get ExchangesGifts"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/delivered/{delivered}/element/{element}/paged/{page}")
	public ResponseEntity<List<ExchangeGift>> getByDelivered(@PathVariable("delivered")Boolean isdelivered, @PathVariable("element") int element, @PathVariable("page") int page){
		List<ExchangeGift> all;
		try {
			all = service.getByDeliveredPaged(isdelivered,element, page);
			
			return new ResponseEntity<List<ExchangeGift>>(all,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<ExchangeGift>>(HttpStatus.BAD_REQUEST);
		}
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
	@ApiOperation(value = "Return all ExchangesGifts Paged By Agency", notes="Return a ExchangesGifts List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get ExchangesGifts"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/id_agency/{id_agency}/element/{element}/paged/{page}")
	public ResponseEntity<List<ExchangeGift>> getByAgency(@PathVariable("id_agency")int agency,@PathVariable("element") int element, @PathVariable("page") int page){
		List<ExchangeGift> all;
		try {
			all = service.getByAgencyPaged(agency,element,page);

			return new ResponseEntity<List<ExchangeGift>>(all,new HttpHeaders(),HttpStatus.OK);
	
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
			return new ResponseEntity<List<ExchangeGift>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Devuelve una respuesta HTTP con un regalo intercambiado filtrada por id.
	 * 
	 * @param id la id por la que se filtrar� del regalos intercambiados.
	 * @return respuesta con el regalos intercambiados encontrada con ese id.
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Return a ExchangeGift By ID", notes="Return a ExchangeGift")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get ExchangeGift"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("id/{id}")
	public ResponseEntity<ExchangeGift> getByID(@PathVariable("id")Long id){
		try {
			ExchangeGift gift=service.getbyId(id);
			
			return new ResponseEntity<ExchangeGift>(gift,new HttpHeaders(),HttpStatus.OK);
			
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			
			return new ResponseEntity<ExchangeGift>(HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * Recibe un regalo intercambiado. Crea o updatea un regalo intercambiado.
	 * 
	 * @param a regalo intercambiado a crear o updatear recibida en el cuerpo.
	 * @return respuesta con el regalo intercambiado actualido o insertado con su id correspondiente.
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Create or Update a new ExchangeGift", notes="Return a ExchangeGift")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, It was not possible to create or update"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	
	@CrossOrigin(origins = "*")
	@PostMapping()
	public ResponseEntity<ExchangeGift> createorUpdateUser(@Valid @RequestBody ExchangeGift ex){
		ExchangeGift gift;
		try {
			gift = service.createorupdate(ex);
			
			return new ResponseEntity<ExchangeGift>(gift,new HttpHeaders(),HttpStatus.OK);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<ExchangeGift>(HttpStatus.BAD_REQUEST);
			
		}
	}
	/**
	 * Recibe un regalo intercambiado y devuelve una respuesta HTTP en funci�n de si ha podido eliminarla
	 * correctamente.
	 * 
	 * @param a regalo intercambiado a eliminar recibida en el cuerpo.
	 * @return respuesta http sobre el status de la petici�n.
	 * @throws RecordNotFoundException
	 */
	@ApiOperation(value = "Delete a ExchangeGift")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, It was not possible to delete"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	
	@CrossOrigin(origins = "*")
	@DeleteMapping()
	public HttpStatus delete(@Valid @RequestBody ExchangeGift ex) throws RecordNotFoundException{
		try {
			if(service.delete(ex)) {
				
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
