package com.learning.ordermanagementservice.entity;


import java.util.List;




import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="orders")
public class Order {
	
	@Id
	private Long orderId;
	private Long customerId;
	private Long restaurantId;
	private String status;
	private double totalPrice;
	
	@OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("orderId")
	private List<OrderedItem> items;
	
	@OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
	@JsonIgnoreProperties("order_id")
	private Payment payment;
	
	
	public List<OrderedItem> getItems() {
		return items;
	}
	public void setItems(List<OrderedItem> items) {
		this.items = items;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
	
	public Order(Long orderId, Long customerId, Long restaurantId, String status, double totalPrice) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.status = status;
		this.totalPrice = totalPrice;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(Long customerId, Long restaurantId, String status) {
		super();
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.status = status;
	}
	
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
}
