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

import com.altimetrix.ecommerce.service.CustomerService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.altimetrix.ecommerce.entity.Customer;


@RestController
@RequestMapping("/user")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
		Customer customerDetails=customerService.addCustomer(customer);
		return ResponseEntity.ok("Customer with details "+customerDetails+" is successfully added to the database");
	}
	
	@GetMapping("/get/{emailID}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable String id){
		Customer customerDetails=customerService.getCustomerById(id);
		return ResponseEntity.ok(customerDetails);
	}
	
	}

