package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.exception.ServiceException;
import project.models.InsuranceCompany;
import project.services.InsuranceCompanyService;

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
	@GetMapping("/CIA_Name/{CIA_Name}")
	public ResponseEntity<List<InsuranceCompany>> getByCIAName(String name) throws ServiceException {
		if(name!=null) {
			if(!name.equals("")) {
				List<InsuranceCompany> result=service.getByCIAName(name);
				return new ResponseEntity<List<InsuranceCompany>>(result,new HttpHeaders(),HttpStatus.OK);
				
				
			}else {
				throw new ServiceException("El nombre introduccido esta vacio");
			}
		}else {
			throw new ServiceException("El nombre introducido es nulo");
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
	@GetMapping("/CIA_Name{CIA_Name}elements{elements}/page/{page}")
	public ResponseEntity<List<InsuranceCompany>> getByNamePaged(String name,int elements,int page) throws ServiceException{
		List<InsuranceCompany> result=service.getByNamePaged(name, elements, page);
		return new ResponseEntity<List<InsuranceCompany>>(result,new HttpHeaders(),HttpStatus.OK);
	}
	/**
	 * M�todo que duvuelve la compa�ia que tenga ese id
	 * @param id id de la compa�ia que se quiera obtener
	 * @return ResponseEntity<InsuranceCompany>
	 * @throws ServiceException
	 */
	@GetMapping("/id/{id}")
	public ResponseEntity<InsuranceCompany> getByID(Long id) throws ServiceException {
		InsuranceCompany result=service.getInsuranceCompanyById(id);
		return new ResponseEntity<InsuranceCompany>(result,new HttpHeaders(),HttpStatus.OK);
		
	}
	/*
	 * M�todo que guarda o actualiza la compa�ia de seguros en la base de datos
	 * @Return ResponseEntity<InsuranceCompany>
	 */
	@PostMapping()
	public ResponseEntity<InsuranceCompany> creatoOrUpdateIsuranceCompany(InsuranceCompany isuranceCompany) throws ServiceException {
		InsuranceCompany result=service.createOrUpadateInsuranceCompany(isuranceCompany);
		return new ResponseEntity<InsuranceCompany>(result,new HttpHeaders(),HttpStatus.OK);
		
	}
	/**
	 * M�todo que borra una compa�ia de seguros a partir del id
	 * @param id id de la compa�ia de seguros que se quiera borrar
	 * @return HttpStatus
	 * @throws ServiceException
	 */
	@DeleteMapping()
	public HttpStatus deleteInsuranceCompany(Long id) throws ServiceException {
		service.deleteInsuranceCompany(id);
		return HttpStatus.OK;
		
	}
	/**
	 * M�todo que devuelva todas las compa�ias de seguros de la base de datos
	 * @return ResponseEntity<List<InsuranceCompany>>
	 */
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
	@GetMapping("/elements{elements}/page/{page}")
	public ResponseEntity<List<InsuranceCompany>> getAllInsuranceCompanyPaged(int elements,int page) throws ServiceException{
		List<InsuranceCompany> result=service.getAllPaged(elements, page);
		return new ResponseEntity<List<InsuranceCompany>>(result,new HttpHeaders(),HttpStatus.OK);
		
		
	}

}
