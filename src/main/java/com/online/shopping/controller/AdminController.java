package com.online.shopping.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.online.shopping.dto.ProductDTO;
import com.online.shopping.model.Product;
import com.online.shopping.service.CategoryService;
import com.online.shopping.service.ProductService;

@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	// admin home page
	@RequestMapping(path = "/admin")
	public String adminHome() {
		return "admin/adminHome";
	}

	
	// categories view page
	@RequestMapping("/admin/categories/add")
	public String getCategoryAdd() {
		return "admin/categoriesAdd";
	}


	
	// products view page
	@GetMapping("/admin/products")
	public String products() {
		return "admin/products";
	}

	// add product page model
	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories",categoryService.getAllCategory());
		return "admin/productsAdd";
	}

	// update product page model
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable int id,Model model) {
		try {
			Product product = productService.getProductById(id).get();
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setCategoryId(product.getCategory().getId());
			productDTO.setPrice(product.getPrice());
			productDTO.setQuantity(product.getQuantity());
			productDTO.setWeight(product.getWeight());
			productDTO.setDescription(product.getDescription());
			productDTO.setImageName(product.getImageName());

			model.addAttribute("categories",categoryService.getAllCategory());
			model.addAttribute("productDTO",productDTO);
			return "admin/productsAdd";
		}catch (Exception e) {
			logger.info("content not found");
			return "admin/productsAdd";
		}
	}
}
