package com.learning.restaurantserarchservice.entity;

import javax.persistence.Entity;




import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Entity
@Table(name = "food_menu")
public class FoodMenu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long foodMenuId;
	private String openingTime;
	private String closingTime;
	
	@OneToOne
	@JoinColumn(name = "restaurant_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Restaurant restaurant;
	
	public FoodMenu(String openingTime, String closingTime, Restaurant restaurant) {
		super();
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.restaurant = restaurant;
	}

	public FoodMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getFoodMenuId() {
		return foodMenuId;
	}

	public void setFoodMenuId(Long foodMenuId) {
		this.foodMenuId = foodMenuId;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
