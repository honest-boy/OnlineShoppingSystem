 package com.online.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.shopping.model.CartItem;
import com.online.shopping.model.Product;
import com.online.shopping.model.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer>{

	public List<CartItem> findByUser(Object object);

	public List<CartItem> findByUser(Optional<User> user);

	public List<CartItem> findByUser_Id(int i);

	public List<CartItem> findById(int i);

	public CartItem findByUserAndProduct(User user,Product product);

	@Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.product.id = ?2 "
			+"AND c.user.id = ?3")
	@Modifying
	public void updateQuantity(int quantity,int productId,int user_Id);

	@Query("DELETE FROM CartItem c WHERE c.user.id = ?1 AND c.product.id = ?2")
	@Modifying
	public void deleteByUserAndProduct(int user_Id,int productId);

	@Query("DELETE FROM CartItem c WHERE c.user.id = ?1")
	@Modifying
	public void deleteAllProductByUser(int user_Id);


}
