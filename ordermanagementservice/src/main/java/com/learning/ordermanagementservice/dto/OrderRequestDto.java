package com.learning.ordermanagementservice.dto;

import java.util.List;


import com.learning.ordermanagementservice.entity.Payment;

public class OrderRequestDto {

	private Long customerId;
	private Long restaurantId;
	private Long orderId;
	private List<OrderedItemsDto> items;
	private Payment payment;
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	

	public OrderRequestDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<OrderedItemsDto> getItems() {
		return items;
	}

	public void setItems(List<OrderedItemsDto> items) {
		this.items = items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	

}
