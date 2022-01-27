package com.pc.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pc.springboot.entity.Order;
import com.pc.springboot.entity.User;
import com.pc.springboot.exception.UserNotFound;
import com.pc.springboot.repository.OrderRepository;
import com.pc.springboot.service.UserService;

@RestController
@RequestMapping("/users")
public class OrderController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFound {

		Optional<User> user = userService.findUserById(userId);

		if (!user.isPresent()) {
			throw new UserNotFound("User Not Found");

		}

		return user.get().getOrders();

	}

	
	@PostMapping("/{userId}/orders")
    public void createOrder(@PathVariable Long userId,@RequestBody Order order) throws UserNotFound {
		
    	Optional<User> optuser=userService.findUserById(userId);
		
		if(!optuser.isPresent()) {
			throw new UserNotFound("User not Exist");
		}
		
		User user=optuser.get();
		user.setId(userId);
		order.setUser(user);
		order.setOrderId(order.getOrderId());
		orderRepository.save(order);
	}
}
