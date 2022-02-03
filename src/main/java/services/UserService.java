package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exception.RecordNotFoundException;
import models.User;
import repositories.UserRepository;
@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public List<User> getall(){
		return repository.findAll();
	}
	public User getallNotesbyId(Long id){
		Optional<User> result=repository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}else {
			throw new RecordNotFoundException("User no existe",id);
		}
		
	}
	public User createorupdatenotes(User user) {
		if(user.getId()>0) {
			Optional<User>n=repository.findById(user.getId());
			if(n.isPresent()) {
				User newUser=n.get();
				newUser.setId(user.getId());
				newUser.setCode(user.getCode());
				newUser.setPasword(user.getPasword());
				newUser.setAdministrator(user.isAdministrator());
				newUser.setName(user.getName());
				newUser=repository.save(newUser);
				return user;
			}else {
				
				user=repository.save(user);
				return user;
			}
			
		}
		return user;
	}
	public void deletenotebyId(Long id) throws RecordNotFoundException{
		Optional<User> note=repository.findById(id);
		if(note.isPresent()) {
			repository.deleteById(id);
		}else {
			throw new RecordNotFoundException("User no existe",id);
		}
	}
}
