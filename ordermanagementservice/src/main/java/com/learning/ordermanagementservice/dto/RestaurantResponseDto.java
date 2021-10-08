package com.learning.ordermanagementservice.dto;

import java.util.List;

public class RestaurantResponseDto {

	RestaurantDto restaurant;
	private String activeFrom;
	private String activeTill;
	List<MenuItemDto> menuItem;
	
	public RestaurantResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestaurantResponseDto(RestaurantDto restaurant, String activeFrom, String activeTill,
			List<MenuItemDto> menuItem) {
		super();
		this.restaurant = restaurant;
		this.activeFrom = activeFrom;
		this.activeTill = activeTill;
		this.menuItem = menuItem;
	}

	public RestaurantDto getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantDto restaurant) {
		this.restaurant = restaurant;
	}

	public String getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(String activeFrom) {
		this.activeFrom = activeFrom;
	}

	public String getActiveTill() {
		return activeTill;
	}

	public void setActiveTill(String activeTill) {
		this.activeTill = activeTill;
	}

	public List<MenuItemDto> getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(List<MenuItemDto> menuItem) {
		this.menuItem = menuItem;
	}

}
