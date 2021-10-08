package com.learning.restaurantserarchservice.dto;

import java.util.List;





import com.learning.restaurantserarchservice.entity.MenuItem;

public class MenuItemRequestDto {
	
	private Long menuItemId;
	private String name;
	private String description;
	private int quantity;
	private int price;
	
	public MenuItemRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MenuItemRequestDto(Long menuItemId, String name, String description, int quantity, int price) {
		super();
		this.menuItemId = menuItemId;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
	public Long getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(Long menuItemId) {
		this.menuItemId = menuItemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

}
