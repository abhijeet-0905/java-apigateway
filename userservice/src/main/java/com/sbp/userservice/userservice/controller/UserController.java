package com.sbp.userservice.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbp.userservice.userservice.entity.User;
import com.sbp.userservice.userservice.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	@GetMapping("/{userId}")
//	@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name = "ratingHotelRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getUserById(@PathVariable String userId) {
		User user1 = userService.getUser(userId);
		return ResponseEntity.ok(user1);
	}
	
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
		System.out.println("rating hotel fucked !!!!");
		User user = new User();
		user.setEmail("haha@haha.com");
		user.setName("haha");
		user.setUserId("1231231");
		return ResponseEntity.ok(user);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}
	
	
	
}
