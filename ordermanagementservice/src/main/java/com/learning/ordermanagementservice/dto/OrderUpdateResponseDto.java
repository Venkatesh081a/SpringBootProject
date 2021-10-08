package com.learning.ordermanagementservice.dto;

import java.util.List;


import com.learning.ordermanagementservice.entity.OrderedItem;

public class OrderUpdateResponseDto {

	private Long orderId;
	private Long customerId;
	private Long restaurantId;
	private String status;
	
	private List<OrderedItem> orderedItems;
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
	public List<OrderedItem> getOrderedItems() {
		return orderedItems;
	}
	public void setOrderedItems(List<OrderedItem> orderedItems) {
		this.orderedItems = orderedItems;
	}
	public OrderUpdateResponseDto(Long orderId, Long customerId, Long restaurantId, String status,
			List<OrderedItem> orderedItems) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.restaurantId = restaurantId;
		this.status = status;
		this.orderedItems = orderedItems;
	}
	public OrderUpdateResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
