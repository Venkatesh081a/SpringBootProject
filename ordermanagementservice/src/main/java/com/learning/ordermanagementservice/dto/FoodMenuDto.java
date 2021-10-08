package com.learning.ordermanagementservice.dto;

import java.util.List;



import com.fasterxml.jackson.annotation.JsonProperty;


public class FoodMenuDto {
	
	private Long id;
	private String activeFrom;
	private String activeTill;
	@JsonProperty("restaurant")
	private RestaurantDto restaurant;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public RestaurantDto getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantDto restaurant) {
		this.restaurant = restaurant;
	}
	public FoodMenuDto(Long id, String activeFrom, String activeTill, RestaurantDto restaurant) {
		super();
		this.id = id;
		this.activeFrom = activeFrom;
		this.activeTill = activeTill;
		this.restaurant = restaurant;
	}
	public FoodMenuDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
