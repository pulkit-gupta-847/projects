package com.altimetrix.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
import com.altimetrix.ecommerce.service.OrderService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.altimetrix.ecommerce.entity.Product;
import com.altimetrix.ecommerce.entity.CartItem;
import com.altimetrix.ecommerce.entity.Customer;


@RestController
@RequestMapping("/order")

public class OrdersController {
	
	
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping("/proceedToCheckout")
	public ResponseEntity<String> placeOrder(@RequestParam String emailId,@RequestParam String paymentType) throws IOException {
		orderService.placeOrder(emailId,paymentType);
		return ResponseEntity.ok("Order is successfully completed");
	}
}
