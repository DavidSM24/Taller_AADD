package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Agency;
import repositories.AgencyRepository;
import services.AgencyService;

@RestController
@RequestMapping("/agencies")
public class AgencyController {
	
	@Autowired
	AgencyService service;
	AgencyRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Agency>> getAllNotes(){
		List<Agency> all=service.getAll();
		return new ResponseEntity<List<Agency>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	
	public ResponseEntity<List<Agency>> getAllNotesPaged(int offset,int page){
		List<Agency> paged=repository.getAllPaged(offset,page);
		return new ResponseEntity<List<Agency>>(paged,new HttpHeaders(),HttpStatus.OK);
	}
}
