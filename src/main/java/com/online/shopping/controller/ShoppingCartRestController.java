package com.online.shopping.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.shopping.global.GlobalData;
import com.online.shopping.model.CartItem;
import com.online.shopping.model.Order;
import com.online.shopping.model.Product;
import com.online.shopping.model.User;
import com.online.shopping.service.OrderService;
import com.online.shopping.service.ProductService;
import com.online.shopping.service.ShoppingCartServices;
import com.online.shopping.service.UserService;

@RestController
public class ShoppingCartRestController {

	@Autowired
	private ShoppingCartServices cartServices;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ShoppingCartServices shoppingCartServices;

	@Autowired
	private OrderService orderService;

	// add product quantity in to cart 
	@PostMapping("/cart/add/{pid}/{qty}")
	public String addProductToCart(@PathVariable("pid") int productId, @PathVariable("qty") int quantity,
			@AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		authentication = securityContext.getAuthentication();

		if (authentication == null) {
			return "You must login to add this product to your shopping cart";
		} else {
			User user = userService.getCurrentLoggedInUser(authentication);
			User dbUser = userService.findUserByEmail(user.getEmail()).get();

			if (productService.getById(productId).getQuantity()-quantity >= 0) {
				int addedQuantity = cartServices.addProduct(productId, quantity, dbUser);
				return String.valueOf(addedQuantity);
			}
			return "Out of Stock";
		}
	}
	
	// update product quantity in to cart 
	@PostMapping("/cart/update/{pid}/{qty}")
	public String updateQuantity(@PathVariable("pid") int productId, @PathVariable("qty") int quantity,
			@AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		authentication = securityContext.getAuthentication();

		if (authentication == null) {
			return "You must login to update quantity ";
		} else {
			User user = userService.getCurrentLoggedInUser(authentication);
			User dbUser = userService.findUserByEmail(user.getEmail()).get();
			if (productService.getById(productId).getQuantity()-quantity >= 0) {
				float subtotal = cartServices.updateQuantity(productId, quantity, dbUser);
				return String.valueOf(subtotal);
			}
			return "Out of Stock";
		}
	}

	// remove product from cart 
	@PostMapping("/cart/remove/{pid}")
	public String removeFromCart(@PathVariable("pid") int productId,
			@AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		authentication = securityContext.getAuthentication();

		if (authentication == null) {
			return "You must login to remove product to your shopping cart";
		} else {
			User user = userService.getCurrentLoggedInUser(authentication);
			User dbUser = userService.findUserByEmail(user.getEmail()).get();
			cartServices.removeProduct(productId, dbUser);
			return "removed";
		}
	}

	// remove all product from cart
	@PostMapping("/cart/deleteAll")
	public String removeAll(@AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		authentication = securityContext.getAuthentication();

		if (authentication == null) {
			return "You must login to remove product to your shopping cart";
		} else {
			User user = userService.getCurrentLoggedInUser(authentication);
			User dbUser = userService.findUserByEmail(user.getEmail()).get();
			cartServices.deleteAllProductByUser_Id(dbUser);
			return "removed";
		}
	}
	
	// total amount of products
	@PostMapping("/cart/total/{total}")
	public String totalAmount(@PathVariable("total") String total,
			@AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		authentication = securityContext.getAuthentication();

		if (authentication == null) {
			return "You must login for total ";
		} else {
			User user = userService.getCurrentLoggedInUser(authentication);
			userService.findUserByEmail(user.getEmail()).get();
			GlobalData.amounts.add(0, Integer.parseInt(total));
			return "removed";
		}
	}

	// add order details in to database
	@PostMapping("/cart/order")
	public String orderDetails(@RequestParam String cOrderId, @AuthenticationPrincipal Authentication authentication) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		authentication = securityContext.getAuthentication();

		if (authentication == null) {
			return "You must login for order checkout";
		} else {
			User user = userService.getCurrentLoggedInUser(authentication);
			User dbUser = userService.findUserByEmail(user.getEmail()).get();
			List<CartItem> allProducts = shoppingCartServices.findByUser_Id(dbUser.getId());
			GlobalData.amounts.clear();
			for (CartItem c : allProducts) {
				Order order = new Order();
				order.setProduct(c.getProduct());
				order.setQuantity(c.getQuantity());
				Product product = productService.findById(c.getProduct().getId()).get();
				product.setQuantity(product.getQuantity() - c.getQuantity());
				productService.save(product);
				LocalDate myObj = LocalDate.now();
				order.setDate(myObj);
				order.setUser(dbUser);
				order.setCustomerOrderId(cOrderId);
				orderService.save(order);
			}
			return "order successful";
		}
	}

}
