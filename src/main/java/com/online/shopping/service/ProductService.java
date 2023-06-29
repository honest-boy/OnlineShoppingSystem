package com.online.shopping.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.model.Product;
import com.online.shopping.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	public void addProduct(Product product) {
		try {
			productRepository.save(product);
		} catch (Exception e) {
			logger.warn("invalid, validation exception, Please fill all details carefully");
		}

	}
	
	public Optional<Product> getProductById(int id){
		return productRepository.findById(id);
	}
	
	public List<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	public List<Product> getAllProductsByCategoryId(int id){
		return productRepository.findAllByCategory_Id(id);
	}
	
	public List<Product> listAll(String keyword) {
		return productRepository.listAll(keyword);
	}
	
	public List<Product> findProductByCategory_IdAndKeyword(int id,String keyword){
		return productRepository.findProductByCategory_IdAndKeyword(id,keyword);
	}

	public List<Product> findByCategory_IdAndPriceBetween(int id,int min,int max){
		return productRepository.findByCategory_IdAndPriceBetween(id, min, max);
	}

	public void removeProductById(int id) {
		try {
			productRepository.deleteById(id);
		} catch (Exception e) {
			logger.warn("Data Integrity Violation Exception");
		}
	}
	
	public Product getById(int id) {
		return productRepository.getById(id);
	}
	
	public Optional<Product> findById(int id) {
		return productRepository.findById(id);
	}
	
	public void save(Product product) {
		productRepository.save(product);
	}

	public List<Product> getProductByKeyword(String keyword) {
		return productRepository.getProductByKeyword(keyword);
		
	}
	
}
