package com.online.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.shopping.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	public List<Product> findAllByCategory_Id(int id);

	@Query("SELECT p from Product p WHERE p.name LIKE %?1%"
			+" OR p.description LIKE %?1%"
			+" OR p.imageName LIKE %?1%")
	public List<Product> listAll(String keyword);
	
	@Query("SELECT p from Product p WHERE p.category.id =?1 AND (p.name LIKE %?2%"
			+" OR p.description LIKE %?2%"
			+" OR p.imageName LIKE %?2%)")
	public List<Product> findProductByCategory_IdAndKeyword(int id,String keyword);


	public List<Product> findByCategory_IdAndPriceBetween(int id,int min,int max);

	@Query("SELECT p from Product p WHERE p.name LIKE %?1%"
			+" OR p.description LIKE %?1%"+" OR p.category.name LIKE %?1%"
			+" OR p.imageName LIKE %?1%")
	public List<Product> getProductByKeyword(String keyword);

}
