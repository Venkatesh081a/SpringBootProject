package com.learning.ordermanagementservice.dto;

public class RestaurantDto {

	private Long id;
	private String name;
	private String location;
	private double distance;
	private String cuisine;
	private int budget;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public RestaurantDto(Long id, String name, String location, double distance, String cuisine, int budget) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.distance = distance;
		this.cuisine = cuisine;
		this.budget = budget;
	}
	public RestaurantDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
