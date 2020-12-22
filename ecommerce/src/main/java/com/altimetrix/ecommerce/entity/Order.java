package com.altimetrix.ecommerce.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class Order{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id")
	private Long id;

	@Column(name="status")
	private String status;
	
	@Column(name="payment_method")
	private String paymentMethod;
	
	@Column(name="advance_payment")
	private boolean advancePayment;

	@Column(name="expected_date")
	private LocalDate expectedDeliveryDate;
	
	@Column(name="actual_date")
	private LocalDate actualDeliveryDate;

	@Column(name="total_price")
	private double totalPrice;

	@OneToMany(mappedBy = "orders")
	private List<ItemDetailsForOrder> items;
	
	@OneToOne
	@JoinColumn(name="email_id")
	@JsonIgnore
	private Customer customer;

	public Order() {}
	

	@Override
	public String toString() {
		return "Order [id=" + id + ", status=" + status + ", paymentMethod=" + paymentMethod + ", advancePayment="
				+ advancePayment + ", expectedDeliveryDate=" + expectedDeliveryDate + ", totalPrice=" + totalPrice
				+ ", items=" + items + "]";
	}

	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public boolean isAdvancePayment() {
		return advancePayment;
	}

	public void setAdvancePayment(boolean advancePayment) {
		this.advancePayment = advancePayment;
	}

	public LocalDate getExpectedDeliveryDate() {
		return expectedDeliveryDate;
	}

	public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
		this.expectedDeliveryDate = expectedDeliveryDate;
	}

	public LocalDate getActualDeliveryDate() {
		return actualDeliveryDate;
	}

	public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}

	public List<ItemDetailsForOrder> getCartItems() {
		return items;
	}

	public void setCartItems(List<ItemDetailsForOrder> items) {
		this.items = items;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}