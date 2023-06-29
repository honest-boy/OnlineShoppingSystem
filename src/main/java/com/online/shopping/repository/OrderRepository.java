package com.online.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.online.shopping.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	public List<Order> findAllByUser_Id(int id);
	
	public List<Order> findAllByCustomerOrderId(String CustomerOrderId);

}
