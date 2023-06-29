package com.online.shopping.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.shopping.model.Product;
import com.online.shopping.service.CategoryService;
import com.online.shopping.service.ProductService;


@RestController
public class AdminProductRestController {

	private static final Logger logger = LoggerFactory.getLogger(AdminProductRestController.class);

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	// add product in to database
	@RequestMapping(method = RequestMethod.POST, value = "/admin/addProduct")
	public ResponseEntity<Product> addProduct(@Valid @RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("category") int category, @RequestParam("price") int price,@RequestParam("quantity") int quantity,
			@RequestParam("weight") double weight, @RequestParam("description") String description,
			 @RequestParam("productImage") MultipartFile file,
			@RequestParam("imgName") String imgName) {
		try {
			Product product = new Product();
			product.setId(id);
			product.setName(name);
			product.setCategory(categoryService.getCategoryById(category));
			product.setPrice(price);
			product.setWeight(weight);
			product.setDescription(description);
			product.setQuantity(quantity);
			String imageUUID;

			if (!file.isEmpty()) {
				imageUUID = file.getOriginalFilename();
				Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
				Files.write(fileNameAndPath, file.getBytes());
			} else {
				imageUUID = imgName;
			}
			product.setImageName(imageUUID);
			productService.addProduct(product);
			logger.info("product added successfully");
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			logger.warn("please fill all the fields properly");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	// delete product from database
	@RequestMapping(method = RequestMethod.DELETE, value = "/admin/deleteProduct")
	public ResponseEntity<Void> deleteProduct(@RequestParam("id") int id) {
		try {
			productService.removeProductById(id);
			logger.info("product deleted successfully");
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		} catch (Exception e) {
			logger.error("Data Integrity Violation Exception");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

	}

	// get products from database
	@RequestMapping(method = RequestMethod.GET, value = "/admin/getProducts")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam String keyword) {
		if(keyword == null) {			
			List<Product> list = productService.getAllProduct();
			if (list.size() <= 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.of(Optional.of(list));
		}else {
			List<Product> list = productService.getProductByKeyword(keyword);
			if (list.size() <= 0) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			return ResponseEntity.of(Optional.of(list));
		}
	}
}
