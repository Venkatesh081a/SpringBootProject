package com.learning.ordermanagementservice.dto;

public class OrderedItemsDto {

	private long itemId;
	private int quantity;
	
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderedItemsDto(long itemId, int quantity) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
	}
	public OrderedItemsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
