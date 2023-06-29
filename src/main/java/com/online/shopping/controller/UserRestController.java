package com.online.shopping.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.online.shopping.model.User;
import com.online.shopping.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	// register user in to database
	@RequestMapping(method = RequestMethod.POST,value = "/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user){
		try {
			userService.registerUser(user);
			logger.info("user added successfully");
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			logger.warn("invalid input validation exception");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}
	
	// update user in to database
	@RequestMapping(method = RequestMethod.PUT,value = "/updateUser")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user){
		try {
			userService.updateUser(user);
			logger.info("user updated successfully");
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			logger.warn("invalid input validation exception");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}
}
