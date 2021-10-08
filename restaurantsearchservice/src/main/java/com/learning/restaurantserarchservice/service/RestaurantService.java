package com.learning.restaurantserarchservice.service;

import com.learning.restaurantserarchservice.dto.FoodMenuResponseDto;

import com.learning.restaurantserarchservice.dto.RestaurantRequestDto;

import com.learning.restaurantserarchservice.dto.RestaurantResponseDto;
import com.learning.restaurantserarchservice.entity.Restaurant;
import com.learning.restaurantserarchservice.exception.service.RestaurantSearchServiceException;

public interface RestaurantService {

	Restaurant saveRestaurant(RestaurantRequestDto restaurantDto);

	RestaurantResponseDto findByName(String name) throws RestaurantSearchServiceException;

	RestaurantResponseDto findByLocation(String location) throws RestaurantSearchServiceException;

	RestaurantResponseDto findByDistance(double distance) throws RestaurantSearchServiceException;

	RestaurantResponseDto findByCuisine(String cuisine) throws RestaurantSearchServiceException;

	RestaurantResponseDto findByBudget(int budget) throws RestaurantSearchServiceException;

	FoodMenuResponseDto findMenuItemByRestaurantId(Long restaurantId) throws RestaurantSearchServiceException;

}
