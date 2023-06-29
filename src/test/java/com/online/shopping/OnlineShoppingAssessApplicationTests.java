package com.online.shopping;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.online.shopping.model.Category;
import com.online.shopping.repository.CategoryRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class OnlineShoppingAssessApplicationTests {

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	@Order(1)
	public void testCreateCategory() {
		Category category = new Category();
		category.setName("bread");
		categoryRepository.save(category);
		assertNotNull(categoryRepository.findById(98).get());
	}

	@Test
	@Order(2)
	public void testReadAllCategories() {
		List<Category> list = categoryRepository.findAll();
		assertThat(list.size()).isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void testSingleCategory() {
		Category category = categoryRepository.findById(1).get();
		assertEquals("Cakes", category.getName());
	}

	@Test
	@Order(4)
	public void testUpdateCategory() {
		Category category = categoryRepository.findById(1).get();
		category.setName("Cake");
		categoryRepository.save(category);
		assertNotEquals("Cakes", categoryRepository.findById(1).get().getName());
	}

	@Test
	@Order(5)
	public void testDeleteCategory() {
		categoryRepository.deleteById(98);
		assertThat(categoryRepository.existsById(98)).isFalse();
	}

}
