package com.online.shopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.online.shopping.model.Product;
import com.online.shopping.service.CategoryService;
import com.online.shopping.service.ProductService;

@Controller
public class ShopController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);

	// home page
	@GetMapping({ "/", "/home" })
	public String home(Model model) {
		return "index";
	}
	
	// shop home page with filter 
	@GetMapping("/shop")
	public String shop(Model model, @Param("keyword") String keyword) {
		if (keyword == null) {
			model.addAttribute("categories", categoryService.getAllCategory());
			model.addAttribute("products", productService.getAllProduct());
			return "shop";
		} else {
			List<Product> listProducts = productService.listAll(keyword);
			model.addAttribute("keyword", keyword);
			model.addAttribute("categories", categoryService.getAllCategory());
			model.addAttribute("listProducts", listProducts);
			return "shop";
		}
	}
	
	// shop home page category according to id, price and filter
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id, @Param("keyword") String keyword,
			@Param("min") String min, @Param("max") String max) {

		try {
			if (keyword == null) {
				if ((min == "" && max == "") || (min == null && max == null)) {
					model.addAttribute("categories", categoryService.getAllCategory());
					model.addAttribute("name", categoryService.getCategoryById(id).getName());
					model.addAttribute("products", productService.getAllProductsByCategoryId(id));
					return "shop";
				}else{
					List<Product> listProducts = productService.findByCategory_IdAndPriceBetween(id,
							Integer.parseInt(min), Integer.parseInt(max));
					model.addAttribute("categories", categoryService.getAllCategory());
					model.addAttribute("name", categoryService.getCategoryById(id).getName());
					model.addAttribute("listProducts", listProducts);
					return "shop";
				}
			} else {
				List<Product> listProducts = productService.findProductByCategory_IdAndKeyword(id,keyword);
				model.addAttribute("keyword", keyword);
				model.addAttribute("categories", categoryService.getAllCategory());
				model.addAttribute("name", categoryService.getCategoryById(id).getName());
				model.addAttribute("listProducts", listProducts);
				return "shop";
			}

		} catch (Exception e) {
			logger.info("content not found");
			return "shop";
		}
	}

	// view a proper product
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable int id) {
		try {
			int quantity  = productService.getProductById(id).get().getQuantity();
			if(quantity < 5) {
				model.addAttribute("msg","only "+quantity+" quantity left");
			}
			model.addAttribute("product", productService.getProductById(id).get());
			return "viewProduct";
		} catch (Exception e) {
			logger.info("content not found");
			return "shop";
		}
	}

}
