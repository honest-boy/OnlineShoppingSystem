package com.online.shopping.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.online.shopping.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category getByName(String name);

	public Optional<Category> findCategoryByName(String name);

	@Query("SELECT c from Category c WHERE c.name LIKE %?1%")
	public List<Category> getCategoryByKeyword(String keyword);

}
