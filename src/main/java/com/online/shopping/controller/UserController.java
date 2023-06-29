package com.online.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.online.shopping.model.Order;
import com.online.shopping.model.User;
import com.online.shopping.service.OrderService;
import com.online.shopping.service.UserService;



@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String registerGet() {
		return "register";
	}
	
	// view page of user profile
	@GetMapping("/profile")
	public String editProfile(Model model,@AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
        authentication = securityContext.getAuthentication();
		User user = userService.getCurrentLoggedInUser(authentication);
		User dbUser = userService.findUserByEmail(user.getEmail()).get();
		model.addAttribute("user",dbUser);
		return "user/editProfile";
	}

	// view page of order details
	@GetMapping("/viewOrders")
	public String viewOrders(Model model,@AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
        authentication = securityContext.getAuthentication();
		User user = userService.getCurrentLoggedInUser(authentication);
		User dbUser = userService.findUserByEmail(user.getEmail()).get();
		List<Order> customerOrders = orderService.findAllByUser_Id(dbUser.getId());
		
		Multimap<String,Order> filterOrders = ArrayListMultimap.create();
		for(Order o:customerOrders) {	
			filterOrders.put(o.getCustomerOrderId()+" and Order_Date is "+o.getDate(), o);
		}
		
		model.addAttribute("filterOrders",filterOrders);
		return "/user/viewOrder";
	}

}

