package com.altimetrix.ecommerce.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrix.ecommerce.repository.CustomerRepository;
import com.altimetrix.ecommerce.entity.Cart;
import com.altimetrix.ecommerce.entity.Customer;


@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer addCustomer(Customer customer) {
		Cart cart=customer.getCart();
		if(cart == null) {
			cart=new Cart();
			cart.setCartItems(new ArrayList<>());
			customer.setCart(cart);
		}
		return customerRepository.save(customer);
	}
	
	public Customer getCustomerById(String emailId) {
		return customerRepository.findById(emailId).orElseThrow(
				()->new RuntimeException("Customer doesn't exist"));
	}
	
	

}
