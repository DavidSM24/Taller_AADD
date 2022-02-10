package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.exception.ServiceException;
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
	 * 
	 * @param element
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("/elements{elements}/page/{page}")
	public ResponseEntity<List<CarRepair>> getAllPaged(@PathVariable("elements")int element,@PathVariable("paged")int page) {
		List<CarRepair> result=new ArrayList<CarRepair>();
		
		try {
			result = service.getAllPaged(element, page);
		} catch (ServiceException e) {
			
			e.printStackTrace();
			return new ResponseEntity<List<CarRepair>>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			
		}
		
		return new ResponseEntity<List<CarRepair>>(result,new HttpHeaders(),HttpStatus.OK);
	}
	
	

}
