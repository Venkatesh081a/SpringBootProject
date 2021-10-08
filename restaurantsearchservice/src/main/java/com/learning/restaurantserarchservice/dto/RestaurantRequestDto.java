package com.learning.restaurantserarchservice.dto;




public class RestaurantRequestDto {

    
	private String name;
	private String location;
	private double distance;
	private String cuisine;
	private int budget;
	private String openingTime;
	private String closingTime;
	
	public RestaurantRequestDto(String name, String location, double distance, String cuisine, int budget,
			String openingTime, String closingTime) {
		super();
		this.name = name;
		this.location = location;
		this.distance = distance;
		this.cuisine = cuisine;
		this.budget = budget;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}

	
	public RestaurantRequestDto(String name, String location, double distance, String cuisine, int budget) {
		super();
		this.name = name;
		this.location = location;
		this.distance = distance;
		this.cuisine = cuisine;
		this.budget = budget;
	}



	public RestaurantRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	public int getBudget() {
		return budget;
	}
	public void setBudget(int budget) {
		this.budget = budget;
	}
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
}
