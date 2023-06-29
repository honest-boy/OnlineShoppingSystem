package com.online.shopping.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.online.shopping.model.Category;
import com.online.shopping.service.CategoryService;

@RestController
public class AdminCategoryRestController {

	private static final Logger logger = LoggerFactory.getLogger(AdminCategoryRestController.class);

	@Autowired
	private CategoryService categoryService;

	
	// Add category in to database
	@RequestMapping(method = RequestMethod.POST, value = "/admin/addCategory")
	public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
		try {
			if (categoryService.findCategoryByName(category.getName()).isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			} else {
				category.setName(category.getName());
				categoryService.addCategory(category);
				logger.info("category added successfully");
				return ResponseEntity.status(HttpStatus.CREATED).body(category);
			}
		} catch (Exception e) {
			logger.warn("please enter Name");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	// Get all categories from database
	@RequestMapping(method = RequestMethod.GET, value = "/admin/getCategories")
	public ResponseEntity<List<Category>> getAllCategories(@RequestParam String keyword) {
		if (keyword == null) {
			List<Category> list = categoryService.getAllCategory();
			if (list.size() <= 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.of(Optional.of(list));
		}else {
			List<Category> list = categoryService.getCategoryByKeyword(keyword);
			if (list.size() <= 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.of(Optional.of(list));
		}
	}

	// Delete category in to database
	@RequestMapping(method = RequestMethod.DELETE, value = "/admin/deleteCategory")
	public ResponseEntity<Void> removeCategory(@RequestParam("id") int id) {
		try {
			categoryService.removeCategoryById(id);
			logger.info("category deleted successfully");
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			logger.warn("cannot delete directly category while inside this category product will present");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	// Update category in to database
	@RequestMapping(method = RequestMethod.PUT, value = "/admin/updateCategory")
	public ResponseEntity<Category> updateCategory(@Valid @RequestParam("id") int id,
			@RequestParam("name") String name) {
		Category category = new Category();
		try {
			category.setId(id);
			category.setName(name);
			categoryService.addCategory(category);
			logger.info("category added successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(category);
		} catch (Exception e) {
			logger.warn("category name will not empty");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}
}
