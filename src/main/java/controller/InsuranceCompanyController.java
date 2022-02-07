package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.InsuranceCompany;
import services.InsuranceCompanyService;

@RestController
@RequestMapping("/insuranceCompany")
public class InsuranceCompanyController {
	@Autowired
	InsuranceCompanyService service;
		
	/**
	 * M�todo que devuelve una lista de compa�ias de seguros con ese nombre
	 * @param name nombre de la compa�ia de seguros
	 * @return List<InsuranceCompany> todas compa�ias de seguros con ese nombre
	 */
	public List<InsuranceCompany> getByName(String name){
		
		return new ArrayList<InsuranceCompany>();
	}
	
	/**
	 * 
	 * @param name
	 * @param elements
	 * @param page
	 * @return List<InsuranceCompany> Grupo reducido de compa�ias de seguros con ese nombre y dentro de una posici�n determinada
	 */
	public List<InsuranceCompany> getByNamePaged(String name,int elements,int page){
		return new ArrayList<InsuranceCompany>();
	}
	
	public InsuranceCompany getByID(Long id) {
		
		return new InsuranceCompany();
		
	}
	public List<InsuranceCompany> getByCIAName(String name){
		return new ArrayList<InsuranceCompany>();
		
	}
	public InsuranceCompany creatoOrUpdateIsuranceCompany(InsuranceCompany isuranceCompany) {
		return new InsuranceCompany();
		
	}
	public void deleteInsuranceCompany(Long id) {
		
	}
	public List<InsuranceCompany> getAllInsuranceCompany(){
		return new ArrayList<InsuranceCompany>();
		
	}
	public List<InsuranceCompany> getAllInsuranceCompanyPaged(int elements,int page){
		return new ArrayList<InsuranceCompany>();
		
	}

}
