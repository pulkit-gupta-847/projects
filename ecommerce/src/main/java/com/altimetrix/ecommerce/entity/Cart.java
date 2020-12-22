package com.altimetrix.ecommerce.entity;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.List;
import java.util.Map;
import javax.persistence.JoinColumn;

@Entity
@Table(name="cart")
public class Cart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_id")
	private long id;

	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItems;
	
	@OneToOne(mappedBy = "cart")
	private Customer customer;

	private double totalPrice;
	
	public Cart() {}

	public Cart(long id, List<CartItem> cartItems, Customer customer, double totalPrice) {
		super();
		this.id = id;
		this.cartItems = cartItems;
		this.customer = customer;
		this.totalPrice = totalPrice;
	}


	@Override
	public String toString() {
		return "Cart [id=" + id + ", cartItems=" + cartItems + ", totalPrice=" + totalPrice + "]";
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
}
