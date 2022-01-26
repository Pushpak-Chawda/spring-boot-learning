package com.pc.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pc.springboot.entity.User;
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
	public User createUser(User user) {

		return userRepository.save(user);
	}

	//findUSerByID
	public Optional<User> findUserById(Long id) {

		return userRepository.findById(id);
	}

	//updateUSerByID
	public User updateUserById(Long id, User user) {

		user.setId(id);

		return userRepository.save(user);
	}

	//deleteUSerByID
	public void deleteUserById(Long id) {

		if (findUserById(id).isPresent()) {
			userRepository.deleteById(id);
		}
	}

	
	public User findUserByName(String username) {

		
		return	userRepository.findByUsername(username); 
	}
}
