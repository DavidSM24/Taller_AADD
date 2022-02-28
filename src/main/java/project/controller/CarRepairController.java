package project.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
import project.models.Agency;
import project.models.CarRepair;
import project.services.CarRepairService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carRepairs")
public class CarRepairController {
	@Autowired
	CarRepairService service;
	
	/**
	 * M�todo que devuelve todas las reparaciones de la base de datos
	 * @return
	 */
	@ApiOperation(value = "Return all CarRepairs", notes="Return a CarRepairs List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get CarRepairs"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping()
	public ResponseEntity<List<CarRepair>> getAll(){
		List<CarRepair> result=service.getAll();
		
		return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * M�todo que devuelve una reparaci�n seg�n el id pasado
	 * @param id
	 * @return ResponseEntity<CarRepair>
	 */
	@ApiOperation(value = "Return a CarRepair By ID", notes="Return a CarRepair")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get CarRepair"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/id/{id}")
	public ResponseEntity<CarRepair> getById(@PathVariable("id")Long id){
		try {
			CarRepair result=service.getById(id);
			
			return new ResponseEntity<CarRepair>(result,new HttpHeaders(),HttpStatus.OK);
			
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
			
			return new ResponseEntity<CarRepair>(HttpStatus.BAD_REQUEST);
		
		}
	}
	/**
	 * M�todo que devuelve toda la lista de reparaciones paginada
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 * @throws Exception 
	 */
	@ApiOperation(value = "Return all CarRepairs Paged", notes="Return a CarRepairs List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get CarRepairs"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/elements/{elements}/page/{page}")
	public ResponseEntity<List<CarRepair>> getAllPaged(@PathVariable(
			"elements")int element,@PathVariable("page")int page) {
		List<CarRepair> result=new ArrayList<CarRepair>();
		
		try {
			result = service.getAllPaged(element, page);
			
			return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
		
		} catch (ServiceException e) {
			
			e.printStackTrace();
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.BAD_REQUEST);
		} finally {
			
		}
		
	}
	
	/**
	 * M�todo que devuelve una lista de reparaciones en funci�n del c�digo de operaci�n
	 * @param operation
	 * @param elements
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@ApiOperation(value = "Return all CarRepairs Paged By Operation", notes="Return a CarRepairs List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get CarRepairs"),
			@ApiResponse(code=500,message="Internal Error"),
	})
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
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.BAD_REQUEST);
			
		}
	}
	
	/**
	 * M�todo que devuelve una lista de reparaciones en funci�n de la matr�cula del coche
	 * @param carPlate
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@ApiOperation(value = "Return all CarRepairs Paged by CarPlate", notes="Return a CarRepairs List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get CarRepairs"),
			@ApiResponse(code=500,message="Internal Error"),
	})
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
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	/**
	 * M�todo que devuelve una lista de reparaciones seg�n el 	nombre del cliente
	 * @param clientName
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@ApiOperation(value = "Return all CarRepairs Paged by clientname", notes="Return a CarRepairs List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get CarRepairs"),
			@ApiResponse(code=500,message="Internal Error"),
	})
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
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.BAD_REQUEST);
			
		}
	}
	
	/**
	 * M�todo que devuelve una lista de reparaciones que se encuentren dentro de una fecha
	 * @param ini fecha de m�s antigua entre las que se encuetre la reparaci�n deseada
	 * @param end fecha m�s reciente entre las que se encuentre la reparaci�n deseada
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@ApiOperation(value = "Return all CarRepairs Paged by dateOrder", notes="Return a CarRepairs List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get CarRepairs"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/ini/{ini}/end/{end}/elements/{elements}/page/{page}")
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
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.BAD_REQUEST);
			
		}
	}
	
	/**
	 * M�todo que devuelve una lista de reparaciones que se encuentre entre los puntos especificados
	 * @param min N�mero m�nimo de puntos que tenga la reparaci�n
	 * @param max N�mero m�ximo de puntos que tenga la reparaci�n
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@ApiOperation(value = "Return all CarRepairs Paged by points", notes="Return a CarRepairs List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get CarRepairs"),
			@ApiResponse(code=500,message="Internal Error"),
	})
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
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.BAD_REQUEST);
			
		}
	}
	/**
	 * M�todo que devuelve las reparaciones en funci�n de si esta 
	 * @param repaired boleano que especifica si se busca un reparaci�n terminada o no
	 * @param element
	 * @param page
	 * @return ResponseEntity<List<CarRepair>>
	 */
	@ApiOperation(value = "Return all CarRepairs paged by her state", notes="Return a CarRepairs List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get CarRepairs"),
			@ApiResponse(code=500,message="Internal Error"),
	})
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
			
			return new ResponseEntity<List<CarRepair>>(HttpStatus.BAD_REQUEST);
		
		}
	}
	/**
	 * M�todo que guarda o actualiza una reparaci�n en la base de datos
	 * @param carRepair
	 * @return ResponseEntity<CarRepair>
	 */
	@ApiOperation(value = "Create or Update a new CarRepair", notes="Return a CarRepair")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, It was not possible to create or update"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@PostMapping()
	public ResponseEntity<CarRepair> createOrUpdateCarRepair(@Valid @RequestBody CarRepair carRepair) {
			
		CarRepair result=new CarRepair();
		
		try {
			result=service.createOrUpdateCarRepair(carRepair);
			
			return new ResponseEntity<CarRepair>(result,new HttpHeaders(),HttpStatus.OK);
			
			
		} catch (ServiceException e) {
			e.printStackTrace();

			return new ResponseEntity<CarRepair>(HttpStatus.BAD_REQUEST);
		
		}
	}
	/**
	 * M�todo que borra una reparaci�n de la base de datos
	 * @param carRepair
	 * @return ResponseEntity<CarRepair>
	 */
	@ApiOperation(value = "Delete a CarRepair")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, It was not possible to delete"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@DeleteMapping()
	public HttpStatus delete(@Valid @RequestBody CarRepair c) {
		try {
			service.delete(c);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return HttpStatus.BAD_REQUEST;
					
		}
		return HttpStatus.OK;
	}

}
