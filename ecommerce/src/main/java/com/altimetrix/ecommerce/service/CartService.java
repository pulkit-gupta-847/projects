package com.altimetrix.ecommerce.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrix.ecommerce.repository.CartItemRepository;
import com.altimetrix.ecommerce.repository.CartRepository;
import com.altimetrix.ecommerce.repository.CustomerRepository;
import com.altimetrix.ecommerce.repository.ProductRepository;
import com.altimetrix.ecommerce.entity.Cart;
import com.altimetrix.ecommerce.entity.CartItem;
import com.altimetrix.ecommerce.entity.Product;
import com.altimetrix.ecommerce.entity.Customer;


@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	/* Empties the whole cart for a particular user */
	public String emptyCart(String emailId) {
		
		/*Fetching the cart from userDetails*/
		Customer customer=customerRepository.findById(emailId).orElseThrow(
				()-> new RuntimeException("Customer with email ID : "+emailId+" doesn't exist in DB"));
		
		Cart cart=customer.getCart();
		if(cart == null || cart.getCartItems().isEmpty()) {
			return "Cart is already empty!!";
		}
		
		/* Removes all the cart Items for a cart from db */
        List<CartItem> cartItems=cart.getCartItems();
        cartItemRepository.deleteAll(cartItems);
        cartItems.clear();
        cart.setCartItems(cartItems);
        
        //Setting Total price to 0
        cart.setTotalPrice(0);
        cartRepository.save(cart);
        
		return "All the items are removed from Cart for user : "+emailId+" !!";
	}
	
	/* Used to obtain the whole cart for a particular user */
	public List<CartItem> viewCart(String emailId) {
		
		/* Fetching the cart from userDetails */
		Customer customer=customerRepository.findById(emailId).orElseThrow(
				()-> new RuntimeException("Customer with email ID : "+emailId+" doesn't exist in DB"));
		
		Cart cart=customer.getCart();
		
		if(cart ==null) {
			throw new RuntimeException("Cart is Empty");
		}
		
        List<CartItem> cartItems=cart.getCartItems();
       
		return cartItems;
	}
	
}
