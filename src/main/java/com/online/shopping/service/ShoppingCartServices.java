package com.online.shopping.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.model.CartItem;
import com.online.shopping.model.Product;
import com.online.shopping.model.User;
import com.online.shopping.repository.CartItemRepository;
import com.online.shopping.repository.ProductRepository;

@Service
@Transactional
public class ShoppingCartServices {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ProductRepository productRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(ShoppingCartServices.class);

	public List<CartItem> listCartItems(int i){
		return cartItemRepository.findByUser_Id(i);
	}

	public int addProduct(int productId,int quantity,User user) {
		int addedQuantity = quantity;
		Product product = productRepository.findById(productId).get();

		CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
		if(cartItem != null) {
			addedQuantity = cartItem.getQuantity()+quantity;
			cartItem.setQuantity(addedQuantity);
		}else {
			cartItem = new CartItem();
			cartItem.setQuantity(quantity);
			cartItem.setUser(user);
			cartItem.setProduct(product);
		}
		cartItemRepository.save(cartItem);
		return addedQuantity;
	}

	public float updateQuantity(int productId,int quantity,User user) {
		cartItemRepository.updateQuantity(quantity, productId, user.getId());

		Product product = productRepository.findById(productId).get();
		float subtotal = product.getPrice() * quantity;
		return subtotal;
	}

	public void removeProduct(int productId,User user) {
		try {		
			cartItemRepository.deleteByUserAndProduct(user.getId(), productId);
		}catch (Exception e) {
			logger.info("content not found");
		}
	}

	public void deleteAllProductByUser_Id(User user) {
		try {		
			cartItemRepository.deleteAllProductByUser(user.getId());
		}catch (Exception e) {
			logger.info("content not found");
		}
	}
	
	public List<CartItem> findByUser_Id(int i){
		return cartItemRepository.findByUser_Id(i);
	}
}
