package com.learning.restaurantserarchservice.dto;

import java.util.List;




import com.learning.restaurantserarchservice.entity.MenuItem;

public class FoodMenuResponseDto {

	private String openingTime;
	private String closingTime;
	private List<MenuItemRequestDto> menuItems;
	
	public String getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}
	public String getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}
	
	
	public List<MenuItemRequestDto> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(List<MenuItemRequestDto> menuItems) {
		this.menuItems = menuItems;
	}
	
	
	public FoodMenuResponseDto(String openingTime, String closingTime,
			List<MenuItemRequestDto> menuItems) {
		super();
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.menuItems = menuItems;
	}
	
	public FoodMenuResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
