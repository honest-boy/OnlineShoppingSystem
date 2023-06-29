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

import com.online.shopping.model.CartItem;
import com.online.shopping.model.User;
import com.online.shopping.service.ShoppingCartServices;
import com.online.shopping.service.UserService;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartServices cartService;

	@Autowired
	private UserService userService;

	// view page of cart
	@GetMapping("/cart")
	public String showShoppingCart(Model model,@AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
        authentication = securityContext.getAuthentication();
		User user = userService.getCurrentLoggedInUser(authentication);
		User dbUser = userService.findUserByEmail(user.getEmail()).get();
		List<CartItem> cartItems = cartService.listCartItems(dbUser.getId());
		model.addAttribute("cartItems",cartItems);
		model.addAttribute("pagerTitle","Shopping Cart");
		return "shoppingCart";
	}
}
