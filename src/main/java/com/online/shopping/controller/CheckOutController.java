package com.online.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.online.shopping.global.GlobalData;
import com.online.shopping.model.Address;
import com.online.shopping.model.User;
import com.online.shopping.service.AddressService;
import com.online.shopping.service.ShoppingCartServices;
import com.online.shopping.service.UserService;

@Controller
public class CheckOutController {

	@Autowired
	private UserService userService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private ShoppingCartServices shoppingCartServices;

	// add address view page
	@GetMapping(value = "/cart/addAddress")
	public String getAddAddress(Model model, @AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		authentication = securityContext.getAuthentication();
		User user = userService.getCurrentLoggedInUser(authentication);
		User dbUser = userService.findUserByEmail(user.getEmail()).get();
		int userId = dbUser.getId();
		try {
			if (!(shoppingCartServices.findByUser_Id(userId)).isEmpty()) {
				if (addressService.findAddressByUserId(userId).isPresent()) {
					Address address = addressService.findAddressByUserId(userId).get();
					model.addAttribute("address", address);
					return "user/checkout";
				} else {
					model.addAttribute("address", new Address());
					return "user/checkout";
				}
			} else {
				return "redirect:/cart";
			}
		} catch (Exception e) {
			return "redirect:/cart";
		}
	}

	// payNow view page
	@GetMapping(value = "/cart/payNow")
	public String payNow(Model model, @AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		authentication = securityContext.getAuthentication();
		User user = userService.getCurrentLoggedInUser(authentication);
		User dbUser = userService.findUserByEmail(user.getEmail()).get();
		int userId = dbUser.getId();
		try {
			if (!(shoppingCartServices.findByUser_Id(userId)).isEmpty()) {
				if (addressService.findAddressByUserId(userId).isPresent()) {
					model.addAttribute("msg", "pay now");
					model.addAttribute("total", GlobalData.amounts.get(0));
					return "user/payNow";
				} else {
					model.addAttribute("address", new Address());
					return "user/checkout";
				}
			} else {
				return "redirect:/cart";
			}
		} catch (Exception e) {
			return "redirect:/cart";
		}
	}

}
