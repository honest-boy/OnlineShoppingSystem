package com.online.shopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.online.shopping.model.Address;
import com.online.shopping.model.User;
import com.online.shopping.service.AddressService;
import com.online.shopping.service.UserService;

@RestController
public class CheckOutRestController {

	private static final Logger logger = LoggerFactory.getLogger(CheckOutRestController.class);

	@Autowired
	private AddressService addressService;

	@Autowired
	private UserService userService;
	
	// add address in to database
	@PostMapping(value = "/cart/addAddress")
	public void postAddress(@RequestBody Address address, @AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		authentication = securityContext.getAuthentication();
		User user = userService.getCurrentLoggedInUser(authentication);
		User dbUser = userService.findUserByEmail(user.getEmail()).get();
		try {
			address.setCountry("INDIA");
			address.setUser(dbUser);
			addressService.postAddress(address);
			logger.info("address added successfully");
		} catch (Exception e) {
			logger.warn("Transaction silently rolled back because it has been marked as rollback-only");
		}
	}
}
