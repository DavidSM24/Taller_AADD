package project.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import project.exception.ServiceException;
import project.models.Agency;
import project.models.CarRepair;
import project.services.CarRepairService;

@RestController
@RequestMapping("/carRepairs")
public class CarRepairController {
	@Autowired
	CarRepairService service;
	
	/**
	 * Método que devuelve todas las reparaciones de la base de datos
	 * @return
	 */
	@GetMapping()
	public ResponseEntity<List<CarRepair>> getAll(){
		List<CarRepair> result=service.getAll();
		
		return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Método que devuelve una reparación según el id pasado
	 * @param id
	 * @return ResponseEntity<CarRepair>
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<CarRepair> getById(@PathVariable("id")Long id){
		CarRepair result=service.getById(id);
		
		return new ResponseEntity<CarRepair>(result,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * Método que devuelve toda la lista de reparaciones paginada
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 * @throws Exception 
	 */
	@GetMapping("/elements/{elements}/page/{page}")
	public ResponseEntity<List<CarRepair>> getAllPaged(@PathVariable(
			"elements")int element,@PathVariable("page")int page) {
		List<CarRepair> result=new ArrayList<CarRepair>();
		
		try {
			result = service.getAllPaged(element, page);
			
			return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
		
		} catch (ServiceException e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			
		}
		
	}
	
	/**
	 * Método que devuelve una lista de reparaciones en función del código de operación
	 * @param operation
	 * @param elements
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@GetMapping("/operation/{operation}/elements/{elements}/page/{page}")
	public ResponseEntity<List<CarRepair>> getByOperationPaged(
			@PathVariable("operation")Long operation,@PathVariable("elements") int elements,@PathVariable("page")int page){
		
		List<CarRepair> result=new ArrayList<CarRepair>();
		
		try {
			result=service.getByOperationPaged(operation, elements, page);
			
			return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	/**
	 * Método que devuelve una lista de reparaciones en función de la matrícula del coche
	 * @param carPlate
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@GetMapping("/carPlate/{carPlate}/elements/{elements}/page/{page}")
	public ResponseEntity<List<CarRepair>> getByCarPlate(
			@PathVariable("carPlate")String carPlate,@PathVariable("elements")int element,@PathVariable("page")int page){
		
		List<CarRepair> result=new ArrayList<CarRepair>();
		
		try {
			result=service.getByCarPlatePaged(carPlate, element, page);
			
			return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	/**
	 * Método que devuelve una lista de reparaciones según el 	nombre del cliente
	 * @param clientName
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@GetMapping("/clientName/{clientName}/elements/{elements}/page/{page}")
	public ResponseEntity<List<CarRepair>> getByClientName(
			@PathVariable("clientName")String clientName,@PathVariable("elements")int element,@PathVariable("page")int page){
	
		List<CarRepair> result=new ArrayList<CarRepair>();
		
		try {
			result=service.getByClientNamePaged(clientName, element, page);
			
			return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	/**
	 * Método que devuelve una lista de reparaciones que se encuentren dentro de una fecha
	 * @param ini fecha de más antigua entre las que se encuetre la reparación deseada
	 * @param end fecha más reciente entre las que se encuentre la reparación deseada
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@GetMapping("/ini/{ini}/end{end}/elements{elements}/page/{page}")
	public ResponseEntity<List<CarRepair>> getByDateOrderPaged(
			@PathVariable("ini")LocalDateTime ini,@PathVariable("end")LocalDateTime end,
			@PathVariable("elements")int element,@PathVariable("page")int page){
	
		List<CarRepair> result=new ArrayList<CarRepair>();
		
		try {
			result=service.getByDateOrderPaged(ini,end, element, page);
			
			return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	/**
	 * Método que devuelve una lista de reparaciones que se encuentre entre los puntos especificados
	 * @param min Número mínimo de puntos que tenga la reparación
	 * @param max Número máximo de puntos que tenga la reparación
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@GetMapping("/min/{min}/max/{max}/elements/{elements}/page/{page}")
	public ResponseEntity<List<CarRepair>> getByPointsPaged(
			@PathVariable("min")int min,@PathVariable("max")int max,
			@PathVariable("elements")int element,@PathVariable("page")int page){
		
		List<CarRepair> result=new ArrayList<CarRepair>();
		
		try {
			result=service.getByPointsPaged(min,max, element, page);
			
			return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	/**
	 * Método que devuelve las reparaciones en función de si esta 
	 * @param repaired boleano que especifica si se busca un reparación terminada o no
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@GetMapping("/repaired/{t-f}/elements/{elements}/page/{page}")
	public ResponseEntity<List<CarRepair>> getByStatedPaged(
			@PathVariable("t-f")boolean repaired,@PathVariable("elements")int element,@PathVariable("page")int page){

		List<CarRepair> result=new ArrayList<CarRepair>();
		
		try {
			result=service.getByStatePaged(repaired, element, page);
			
			return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
	/**
	 * Método que guarda o actualiza una reparación en la base de datos
	 * @param carRepair
	 * @return ResponseEntity<CarRepair>
	 */
	@PostMapping()
	public ResponseEntity<CarRepair> createOrUpdateCarRepair(@Valid @RequestBody CarRepair carRepair) {
			
		CarRepair result=new CarRepair();
		
		try {
			result=service.createOrUpdateCarRepair(carRepair);
			return new ResponseEntity<CarRepair>(result,new HttpHeaders(),HttpStatus.OK);
			
			
		} catch (ServiceException e) {
			e.printStackTrace();

			return new ResponseEntity<CarRepair>(HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
	}
	/**
	 * Método que borra una reparación de la base de datos
	 * @param carRepair
	 * @return ResponseEntity<CarRepair>
	 */
	@DeleteMapping()
	public HttpStatus delete(@Valid @RequestBody CarRepair c) throws RecordNotFoundException {
		try {
			service.delete(c);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return HttpStatus.OK;
	}

}
