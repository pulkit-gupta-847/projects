package com.altimetrix.ecommerce.service;

import java.util.List;

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
public class CartItemService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	
	/*This method either adds a new product to the cart for a specific client with a specified number
	 * or increase / decrease the quantity of an already existing product in the cart 
	*/
	public Product addProductToCart(Long productId,int newQuantity,String emailID) {
		
		/*Checking if product with given product ID exists in DB or not */
		Product newProduct=productRepository.findById(productId).orElseThrow(
				()-> new RuntimeException("Product with product ID : "+productId+" doesn't exist in DB"));
		
		/*Fetching the cart from userDetails*/
		Customer customer=customerRepository.findById(emailID).orElseThrow(
				()-> new RuntimeException("Customer with email ID : "+emailID+" doesn't exist in DB"));
		Cart cart=customer.getCart();
		
		/*Fetching cart Items from the cart */
		List<CartItem> cartItems=cart.getCartItems();
		
		/* Updating the quantity of existing product in the cart for a user */
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			if (newProduct.getId()==cartItem.getProduct().getId()) {
				cartItem.setQuantity(newQuantity);
				cartItemRepository.save(cartItem);
				return newProduct;
			}
		}
		
		/* Adding new item with its specified quantity to the cart */
		CartItem cartItem = new CartItem();
		
		cartItem.setQuantity(newQuantity);
		cartItem.setProduct(newProduct);
		cartItem.setPrice(newProduct.getProductPrice());
		cartItem.setCart(cart);
		cartItems.add(cartItem);
		
		/* Saving the new Cart Item */
		cartItemRepository.save(cartItem);
		cart.setCartItems(cartItems);
		double totalPriceOfItemsInCart=cart.getCartItems().stream().mapToDouble(item -> (item.getPrice()*item.getQuantity())).sum();
		cart.setTotalPrice(totalPriceOfItemsInCart);
		cartRepository.save(cart);
		return newProduct;
	}
	
	/*This method removes complete quantity of a product(specified by its product ID) from the cart */
	public Product removeProductFromCart(Long productId,String emailId) {
		
		
		Product productToBeRemoved=productRepository.findById(productId).orElseThrow(
				()-> new RuntimeException("Product with product ID : "+productId+" doesn't exist in DB"));
		
		/*Fetching the cart from userDetails*/
		Customer customer=customerRepository.findById(emailId).orElseThrow(
				()-> new RuntimeException("Customer with email ID : "+emailId+" doesn't exist in DB"));
		
		Cart cart=customer.getCart();
		
		if(cart ==null || cart.getCartItems().isEmpty()) {
			throw new RuntimeException("Cart is empty!!");
		}
		
        List<CartItem> cartItems=cart.getCartItems();
        CartItem cartItemToBeRemoved=null;
        
        /* Removing existing product in the cart for a user */
		for (int i = 0; i < cartItems.size(); i++) {
			
			CartItem cartItem = cartItems.get(i);
			
			if (productToBeRemoved.getId() == cartItem.getProduct().getId()) {	
				cartItemToBeRemoved=cartItem;
				cartItemRepository.delete(cartItem);
			}
		}
		if(cartItemToBeRemoved != null) {
			cartItems.remove(cartItemToBeRemoved);
			cart.setCartItems(cartItems);
			
			//Update the total Price of Cart
			double totalPriceOfItemsInCart=cart.getCartItems().stream().mapToDouble(item -> (item.getPrice()*item.getQuantity())).sum();
			cart.setTotalPrice(totalPriceOfItemsInCart);
			cartRepository.save(cart);
			
			
			return productToBeRemoved;
		}
		else {
			throw new RuntimeException("Given Product ID: "+productId+" is not present in customer cart");
		}
	}
	
	
}
