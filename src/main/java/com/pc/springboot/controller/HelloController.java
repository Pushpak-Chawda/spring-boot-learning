package com.pc.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pc.springboot.model.UserDetails;

@RestController
public class HelloController {
	
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

}
