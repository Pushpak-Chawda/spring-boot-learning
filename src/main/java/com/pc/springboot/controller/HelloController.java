package com.pc.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pc.springboot.entity.User;
import com.pc.springboot.model.UserDetails;
import com.pc.springboot.repository.UserRepository;
import com.pc.springboot.service.UserService;

@RestController
public class HelloController {
	
	@Autowired
	private UserService userService;
	
	
	
	//@RequestMapping(method = RequestMethod.GET,path = "/helloworld")
	@GetMapping("/helloworld1")
	public String helloWorld() {
		
		return "Hello Pushpak";
	}
	
	@GetMapping("hello-bean")
	
	public UserDetails getBean()
	{
		return new UserDetails("Pushpak", "Chawda", "Canada");
		
	}

	@GetMapping("/users")
	public List<User> getUsers(){
		return userService.getAllUsers();
		
		
	}
	
	@PostMapping("/users")
	public User getUserById(@RequestBody User user) {
		
		return userService.createUser(user);
		
	}
	
	

	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable Long id) {
		
		return userService.findUserById(id);
		
	}
	
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable Long id,@RequestBody User user) {
		
		return userService.updateUserById(id, user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		
		
	}
	

	@GetMapping("users/userbyname/{username}")
	public User gerUserByName(@PathVariable String username) {
		
		return userService.findUserByName(username);
		
	}
}
