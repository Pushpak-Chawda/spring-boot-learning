package com.pc.springboot.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.pc.springboot.entity.User;
import com.pc.springboot.exception.UserExistException;
import com.pc.springboot.exception.UserNameNotFoundException;
import com.pc.springboot.exception.UserNotFound;
import com.pc.springboot.model.UserDetails;
import com.pc.springboot.service.UserService;

@RestController
@Validated
public class HelloController {

	@Autowired
	private UserService userService;

	// @RequestMapping(method = RequestMethod.GET,path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {

		return "Hello Pushpak";
	}

	@GetMapping("hello-bean")

	public UserDetails getBean() {
		return new UserDetails("Pushpak", "Chawda", "Canada");

	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getAllUsers();

	}

	@PostMapping("/users")
	public ResponseEntity<String> createUser(@Valid @RequestBody User user,UriComponentsBuilder uri) {

		try {
			 userService.createUser(user);
			 HttpHeaders hs=new HttpHeaders();
			 hs.setLocation(uri.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			 return new ResponseEntity<String>("User Created",hs, HttpStatus.CREATED);
			 
		} catch (UserExistException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Already Exist");
		}

	}

	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable @Min(1) Long id) {

		try {
			return userService.findUserById(id);
		} catch (UserNotFound ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable Long id, @RequestBody User user) {

		try {
		return userService.updateUserById(id, user);
		}
		catch (UserNotFound ex) {
			throw new ResponseStatusException(HttpStatus .NOT_FOUND,"User not Present");
		}
	   
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) throws UserNotFound {
		userService.deleteUserById(id);

	}

	@GetMapping("users/userbyname/{username}")
	public User gerUserByName(@PathVariable String username) throws UserNameNotFoundException {

		User user= userService.findUserByName(username);
		
		if(null==user) {
			
			throw new UserNameNotFoundException("UserName Not Exist");
		}
		
		return user;

	}
}
