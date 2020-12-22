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

import com.altimetrix.ecommerce.service.CartItemService;

import com.altimetrix.ecommerce.service.CustomerService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.altimetrix.ecommerce.entity.Product;
import com.altimetrix.ecommerce.entity.Customer;


@RestController
@RequestMapping("/cartItem")
public class CartItemController {
	
	@Autowired
	private CartItemService cartItemService;
	
	@PostMapping("/addToCart")
	public ResponseEntity<String> addProductToCart(@RequestParam Long productId,@RequestParam int quantity,@RequestParam String emailId) {
		Product productDetails=cartItemService.addProductToCart(productId,quantity,emailId);
		return ResponseEntity.ok("Product with details "+productDetails+" & quantity "+quantity+" "
				+ "is successfully added to the cart for email Id : "+ emailId);
	}
	
	@DeleteMapping("/removeFromCart")
	public ResponseEntity<String> removeProductFromCart(@RequestParam Long productId,@RequestParam String emailId) {
		Product productDetails=cartItemService.removeProductFromCart(productId,emailId);
		return ResponseEntity.ok("Product with details "+productDetails+" is "
				+ "successfully deleted from the cart for email Id : "+emailId);
	}
		
}

