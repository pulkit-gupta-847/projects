package com.altimetrix.ecommerce.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="customer")

public class Customer {
	
	@NotNull(message="User Name can't be null")
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;
		
	@Column(name="password")
	@JsonIgnore
	private String password;
	
	@Id
	@NotNull(message="Email ID can't be null")
	@Column(name="email_id")
	private String id;
	
	@Column(name="age")
	private int age;
	
	@Column(name="deliveryAddress")
	private String deliveryAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cart_id")
	@JsonIgnore
	private Cart cart;
	
	@OneToOne(mappedBy = "customer")
	private Order order;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Customer(@NotNull(message = "User Name can't be null") String name, String gender, String password,
			@NotNull(message = "Email ID can't be null") String id, int age, String deliveryAddress, Cart cart) {
		super();
		this.name = name;
		this.gender = gender;
		this.password = password;
		this.id = id;
		this.age = age;
		this.deliveryAddress = deliveryAddress;
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", gender=" + gender + ", emailID=" + id
				+ ", age=" + age + ", deliveryAddress=" + deliveryAddress +" ]";
	}
	
	public Customer() {}
	
	
}
