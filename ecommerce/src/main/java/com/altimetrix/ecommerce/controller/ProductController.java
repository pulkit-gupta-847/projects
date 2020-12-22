package com.altimetrix.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrix.ecommerce.service.ProductService;
import com.altimetrix.ecommerce.service.CustomerService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.websocket.server.PathParam;

import com.altimetrix.ecommerce.entity.Product;
import com.altimetrix.ecommerce.entity.Customer;


@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/*
	 * In actual scenario only users with the specified role can access the createProduct API
	 */
	@PostMapping("/register")
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		Product productDetails=productService.addProduct(product);
		return ResponseEntity.ok("Product with details "+productDetails+" is successfully added to the database");
	}
	
	/*
	 * Accessible to all users
	 */
	@GetMapping("/listAll")
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> productDetails=productService.getAllProducts();
		return ResponseEntity.ok(productDetails);
	}
	
	//list all the products of a particular type and sorted in ascending order of their price
	@GetMapping("/listAllByPrice/{productType}")
	public ResponseEntity<List<Product>> getAllProductsByTypeSortedByPrice(@PathVariable String productType){
		List<Product> productDetails=productService.getAllProductsByTypeSortedByPrice(productType);
		return ResponseEntity.ok(productDetails);
	}
	
	//list all the products of a particular type and sorted in ascending order of their name
	@GetMapping("/listAllByName/{productType}")
	public ResponseEntity<List<Product>> getAllProductsByTypeSortedByName(@PathVariable String productType){
		List<Product> productDetails=productService.getAllProductsByTypeSortedByName(productType);
		return ResponseEntity.ok(productDetails);
	}
		
	//search all the products of a particular type & name 
	@GetMapping("/searchProduct/{productName}")
	public ResponseEntity<List<Product>> searchProducts(@PathVariable String productName,@RequestParam String productType){
					List<Product> productDetails=productService.searchProductsByTypeAndName(productType,productName);
					return ResponseEntity.ok(productDetails);
	}
		
}

