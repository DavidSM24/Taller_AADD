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
import models.User;
import services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> all=service.getall();
		return new ResponseEntity<List<User>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	@GetMapping("/page/{page}")
	public ResponseEntity<List<User>> getAllUsersPaged(@PathVariable("page")int page){
		List<User> all=service.getAllPaged(page);
		return new ResponseEntity<List<User>>(all,new HttpHeaders(),HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserByID(@PathVariable("id")Long id){
		User user=service.getbyId(id);
		return new ResponseEntity<User>(user,new HttpHeaders(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> createorUpdateUser(@RequestBody User u){
		User user=service.createorupdate(u);
		return new ResponseEntity<User>(user,new HttpHeaders(),HttpStatus.OK);
	}
	@DeleteMapping
	public HttpStatus deleteUserById(User u) throws RecordNotFoundException{
		service.delete(u);
		return HttpStatus.OK;
	}
}
