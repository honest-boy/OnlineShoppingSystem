package com.online.shopping.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.model.Category;
import com.online.shopping.repository.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

	public void addCategory(Category category) {
		try {
			categoryRepository.save(category);
		} catch (Exception e) {
			logger.warn("invalid definition exception, Please fill all detaails carefully");
		}
	}

	public Category getCategoryById(int id) {
		return categoryRepository.getById(id);
	}

	public Category getCategoryByName(String name) {
		return categoryRepository.getByName(name);
	}

	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	public void removeCategoryById(int id) {
		try {
			categoryRepository.deleteById(id);
		} catch (Exception e) {
			logger.info("id not found");
		}
	}
	
	public Optional<Category> findCategoryByName(String name){
		return categoryRepository.findCategoryByName(name);
	}

	public List<Category> getCategoryByKeyword(String keyword) {
		return categoryRepository.getCategoryByKeyword(keyword);
	}

}
