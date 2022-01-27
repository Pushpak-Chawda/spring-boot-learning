package com.pc.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pc.springboot.entity.Order;
import com.pc.springboot.entity.User;
import com.pc.springboot.exception.UserExistException;
import com.pc.springboot.exception.UserNameNotFoundException;
import com.pc.springboot.exception.UserNotFound;
import com.pc.springboot.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	//getAllUser
	public List<User> getAllUsers() {

		return userRepository.findAll();
	}

	//createUSerBy\ID
	public User createUser(User user) throws UserExistException {
         
		User userExistInDb =userRepository.findByUsername(user.getUsername());
		
		if(null!=userExistInDb) {
			throw new UserExistException("User Already Exist");
		}
		
		return userRepository.save(user);
	}

	//findUSerByID
	public Optional<User> findUserById(Long id) throws UserNotFound {

		
		Optional<User> user=userRepository.findById(id);
		
		if (!user.isPresent()) {
			throw new UserNotFound("User Not Present in database");
			
		}
		
			
		return userRepository.findById(id);
	}

	//updateUSerByID
	public User updateUserById(Long id, User user) throws UserNotFound{

		Optional<User> optionalUser=userRepository.findById(id);
		if(!optionalUser.isPresent()) {
			throw new UserNotFound("User not Exist to Update");
		}
		
		user.setId(id);
	   return userRepository.save(user);
	}

	//deleteUSerByID
	public void deleteUserById(Long id) throws UserNotFound {

		if (!findUserById(id).isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Id Not Present");
		}
		
		userRepository.deleteById(id);
	}

	
	public User findUserByName(String username) {

		
		return	userRepository.findByUsername(username); 
	}
	
	
	
	
}
