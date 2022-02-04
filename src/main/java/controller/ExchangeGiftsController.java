package controller;

import java.util.List;

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

import exception.RecordNotFoundException;
import models.ExchangeGift;
import services.ExchangeGiftService;
@RestController
@RequestMapping("/exchangegift")
public class ExchangeGiftsController {
	@Autowired
	ExchangeGiftService service;
	@GetMapping
	public ResponseEntity<List<ExchangeGift>> getAllUsers(){
		List<ExchangeGift> all=service.getAll();
		return new ResponseEntity<List<ExchangeGift>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	@GetMapping("/page/{page}")
	public ResponseEntity<List<ExchangeGift>> getAllUsersPaged(@PathVariable("page")int page){
		List<ExchangeGift> all=service.getAllPaged(page);
		return new ResponseEntity<List<ExchangeGift>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ExchangeGift> getUserByID(@PathVariable("id")Long id){
		ExchangeGift gift=service.getbyId(id);
		return new ResponseEntity<ExchangeGift>(gift,new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ExchangeGift> createorUpdateUser(@RequestBody ExchangeGift ex){
		ExchangeGift gift=service.createorupdate(ex);
		return new ResponseEntity<ExchangeGift>(gift,new HttpHeaders(),HttpStatus.OK);
	}
	
	@DeleteMapping
	public HttpStatus deleteUserById(ExchangeGift ex) throws RecordNotFoundException{
		service.delete(ex);
		return HttpStatus.OK;
	}
}
