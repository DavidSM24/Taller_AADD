package controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.User;
import services.UserService;
@RestController
@RequestMapping("/user")
public class UserController {
	UserService service;
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> all=service.getall();
		return new ResponseEntity<List<User>>(all,new HttpHeaders(),HttpStatus.OK);
	}
}
