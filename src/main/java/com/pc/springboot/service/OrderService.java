package com.pc.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.pc.springboot.entity.Order;
import com.pc.springboot.entity.User;
import com.pc.springboot.exception.UserExistException;
import com.pc.springboot.exception.UserNameNotFoundException;
import com.pc.springboot.exception.UserNotFound;
import com.pc.springboot.repository.OrderRepository;
import com.pc.springboot.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;


	
	
	
	
}
