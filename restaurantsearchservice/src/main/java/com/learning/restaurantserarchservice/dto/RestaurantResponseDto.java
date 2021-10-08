package com.learning.restaurantserarchservice.dto;

import java.util.List;


import com.learning.restaurantserarchservice.entity.Restaurant;

public class RestaurantResponseDto {

	private List<Restaurant> restaurants;

	public RestaurantResponseDto(List<Restaurant> restaurants) {
		super();
		this.restaurants = restaurants;
	}

	public RestaurantResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
}
