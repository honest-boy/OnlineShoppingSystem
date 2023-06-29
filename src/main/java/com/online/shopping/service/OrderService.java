package com.online.shopping.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.model.Order;
import com.online.shopping.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	private static final Logger logger = LoggerFactory.getLogger(OrderService.class);
	
	public void save(Order order) {
		try {		
			orderRepository.save(order);
		}catch (Exception e) {
			logger.warn("invalid, please provide valid details");
		}
	}
	
	public List<Order> findAllByUser_Id(int id){
		return orderRepository.findAllByUser_Id(id);
	}
	
	
	public List<Order> findAllByCustomerOrderId(String CustomerOrderId){
		return orderRepository.findAllByCustomerOrderId(CustomerOrderId);
	}
}
