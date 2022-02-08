package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.repositories.CarRepairRepository;

@Service
public class CarRepairService {
	
	@Autowired
	CarRepairRepository repository;
	
	

}
