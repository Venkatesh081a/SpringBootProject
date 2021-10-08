package com.learning.ordermanagementservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MenuItemDto {

	private Long menuItemId;
	private String name;
	private String description;
	private int quantity;
	private int price;
	@JsonProperty("menu")
	private FoodMenuDto menu;
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
	public FoodMenuDto getMenu() {
		return menu;
	}
	public void setMenu(FoodMenuDto menu) {
		this.menu = menu;
	}
	public MenuItemDto(Long menuItemId, String name, String description, int quantity, int price, FoodMenuDto menu) {
		super();
		this.menuItemId = menuItemId;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.menu = menu;
	}
	public MenuItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
