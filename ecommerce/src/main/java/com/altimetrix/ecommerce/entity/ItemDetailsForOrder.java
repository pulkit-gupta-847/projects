package com.altimetrix.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="item_details_for_order")
public class ItemDetailsForOrder  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="order_item_quantity")
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "orders_id")
	@JsonIgnore
	private Order orders;

	@Override
	public String toString() {
		return "CartItem [ quantity=" + quantity + ", product=" + product.getId() + "]";
	}
	
	public ItemDetailsForOrder(Long id, int quantity, Product product, Order orders) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.product = product;
		this.orders = orders;
	}

	public ItemDetailsForOrder() {}
	
	public Long getCartItemId() {
		return id;
	}

	public void setCartItemId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return orders;
	}

	public void setOrder(Order orders) {
		this.orders = orders;
	}
}
