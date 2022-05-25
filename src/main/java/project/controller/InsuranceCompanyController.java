package project.controller;

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
import project.exception.ServiceException;
import project.models.InsuranceCompany;
import project.services.InsuranceCompanyService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/insuranceCompany")
public class InsuranceCompanyController {
	@Autowired
	InsuranceCompanyService service;
		
	/**
	 * M�todo que devuelve una lista de compa�ias de seguros con ese nombre
	 * @param name nombre de la compa�ia de seguros
	 * @return ResponseEntity<List<InsuranceCompany>> todas compa�ias de seguros con ese nombre
	 * @throws ServiceException 
	 */
	@ApiOperation(value = "Return all InsuranceCompanies filtered by CIA NAME", notes="Return a InsuranceCompanies List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get InsuranceCompanies"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/CIA_Name/{CIA_Name}")
	public ResponseEntity<List<InsuranceCompany>> getByCIAName(@PathVariable("CIA_Name") String name){
		
				List<InsuranceCompany> result;
				try {
					result = service.getByCIAName(name);
					
					return new ResponseEntity<List<InsuranceCompany>>(result,new HttpHeaders(),HttpStatus.OK);
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					return new ResponseEntity<List<InsuranceCompany>>(HttpStatus.BAD_REQUEST);
				}
	}
	
	/**
	 * 
	 * @param name
	 * @param elements
	 * @param page
	 * @return List<InsuranceCompany> Grupo reducido de compa�ias de seguros con ese nombre y dentro de una posici�n determinada
	 * @throws ServiceException 
	 */
	@ApiOperation(value = "Return all InsuranceCompanies paged filtered by CIA NAME", notes="Return a InsuranceCompanies List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get InsuranceCompanies"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/CIA_Name/{CIA_Name}/elements/{elements}/page/{page}")
	public ResponseEntity<List<InsuranceCompany>> getByNamePaged(@PathVariable("CIA_Name")String name,@PathVariable("elements")int elements,@PathVariable("page")int page){
		List<InsuranceCompany> result;
		try {
			result = service.getByNamePaged(name, elements, page);
			
			return new ResponseEntity<List<InsuranceCompany>>(result,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<InsuranceCompany>>(HttpStatus.BAD_REQUEST);
		}
	}
	/**
	 * M�todo que duvuelve la compa�ia que tenga ese id
	 * @param id id de la compa�ia que se quiera obtener
	 * @return ResponseEntity<InsuranceCompany>
	 * @throws ServiceException
	 */
	@ApiOperation(value = "Return a InsuranceCompany filtered by ID", notes="Return a InsuranceCompany")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get InsuranceCompany"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/id/{id}")
	public ResponseEntity<InsuranceCompany> getByID(@PathVariable("id")Long id){
		InsuranceCompany result;
		try {
			result = service.getInsuranceCompanyById(id);
			
			return new ResponseEntity<InsuranceCompany>(result,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<InsuranceCompany>(HttpStatus.BAD_REQUEST);
		}
		
	}
	/*
	 * M�todo que guarda o actualiza la compa�ia de seguros en la base de datos
	 * @Return ResponseEntity<InsuranceCompany>
	 */
	@ApiOperation(value = "Create or Update a new InsuranceCompany", notes="Return a InsuranceCompany")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, It was not possible to create or update"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@PostMapping()
	public ResponseEntity<InsuranceCompany> creatoOrUpdateIsuranceCompany(@Valid @RequestBody InsuranceCompany isuranceCompany){
		InsuranceCompany result;
		try {
			result = service.createOrUpadateInsuranceCompany(isuranceCompany);
			
			return new ResponseEntity<InsuranceCompany>(result,new HttpHeaders(),HttpStatus.OK);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<InsuranceCompany>(HttpStatus.BAD_REQUEST);
		}
		
	}
	/**
	 * M�todo que borra una compa�ia de seguros a partir del id
	 * @param id id de la compa�ia de seguros que se quiera borrar
	 * @return HttpStatus
	 * @throws ServiceException
	 */
	@ApiOperation(value = "Delete a InsuranceCompany")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, It was not possible to delete"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@DeleteMapping()
	public HttpStatus deleteInsuranceCompany(@Valid @RequestBody InsuranceCompany insurance){
		try {
			if(service.deleteInsuranceCompany(insurance)) {
				
				return HttpStatus.OK;
				
			}else {
				
				return HttpStatus.BAD_REQUEST;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return HttpStatus.BAD_REQUEST;
		}
		
	}
	/**
	 * M�todo que devuelva todas las compa�ias de seguros de la base de datos
	 * @return ResponseEntity<List<InsuranceCompany>>
	 */
	@ApiOperation(value = "Return all InsuranceCompanies", notes="Return a InsuranceCompanies List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get InsuranceCompanies"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping()
	public ResponseEntity<List<InsuranceCompany>> getAllInsuranceCompany(){
		
		List<InsuranceCompany> result=service.getAll();
		
		return new ResponseEntity<List<InsuranceCompany>>(result,new HttpHeaders(),HttpStatus.OK);
		
	}
	/**
	 * M�todo que devuelve las compa�ias de seguro puginadas
	 * @param elements n�mero de elementos que se quiera devolver
	 * @param page N�mero que establece a partir de que elemento se inicia a contar
	 * @return ResponseEntity<List<InsuranceCompany>>
	 * @throws ServiceException
	 */
	@ApiOperation(value = "Return all InsuranceCompanies Paged", notes="Return a InsuranceCompanies List")
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Successful Operation"),
			@ApiResponse(code=400,message="Bad Request"),
			@ApiResponse(code=404,message="ERROR, Can't get InsuranceCompanies"),
			@ApiResponse(code=500,message="Internal Error"),
	})
	@GetMapping("/elements/{elements}/page/{page}")
	public ResponseEntity<List<InsuranceCompany>> getAllInsuranceCompanyPaged(@PathVariable("elements")int elements,@PathVariable("page")int page){
		
		List<InsuranceCompany> result;
		
		try {
			result = service.getAllPaged(elements, page);
			
			return new ResponseEntity<List<InsuranceCompany>>(result,new HttpHeaders(),HttpStatus.OK);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return new ResponseEntity<List<InsuranceCompany>>(HttpStatus.BAD_REQUEST);
			
		}
		
		
	}

}
