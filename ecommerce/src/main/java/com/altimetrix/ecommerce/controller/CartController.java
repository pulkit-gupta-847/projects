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

import com.altimetrix.ecommerce.service.CartService;
import com.altimetrix.ecommerce.service.CustomerService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.altimetrix.ecommerce.entity.Product;
import com.altimetrix.ecommerce.entity.CartItem;
import com.altimetrix.ecommerce.entity.Customer;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	/* In actual scenario firstly user logins to the application then a valid JWT Token is issued to him/her
	 *  and in order to access all the APIs in Cart & CartItemController user needs to send the same authentication
	 *  token (JWT)needs to send till its expiry period.A stateless authentication will be used
	 */
	@GetMapping("/viewCart")
	public ResponseEntity<List<CartItem>> viewCart(@RequestParam String emailId) {
		List<CartItem> cartItems=cartService.viewCart(emailId);
		return ResponseEntity.ok(cartItems);
	}
	
	@DeleteMapping("/emptyCart")
	public ResponseEntity<String> emptyCart(@RequestParam String emailId) {
		String message=cartService.emptyCart(emailId);
		return ResponseEntity.ok(message);
	}
}

