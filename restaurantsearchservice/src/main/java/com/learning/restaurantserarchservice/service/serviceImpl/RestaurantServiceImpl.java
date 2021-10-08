package com.learning.restaurantserarchservice.service.serviceImpl;

import java.util.List;


import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.learning.restaurantserarchservice.dto.FoodMenuResponseDto;
import com.learning.restaurantserarchservice.dto.MenuItemRequestDto;
import com.learning.restaurantserarchservice.dto.RestaurantRequestDto;
import com.learning.restaurantserarchservice.dto.RestaurantResponseDto;
import com.learning.restaurantserarchservice.entity.FoodMenu;
import com.learning.restaurantserarchservice.entity.MenuItem;
import com.learning.restaurantserarchservice.entity.Restaurant;
import com.learning.restaurantserarchservice.exception.service.RestaurantSearchServiceException;
import com.learning.restaurantserarchservice.repostiory.RestaurantRepository;
import com.learning.restaurantserarchservice.service.FoodMenuService;
import com.learning.restaurantserarchservice.service.MenuItemService;
import com.learning.restaurantserarchservice.service.RestaurantService;



@Service
public class RestaurantServiceImpl implements RestaurantService{
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private FoodMenuService foodMenuService;
	
	@Autowired
	private MenuItemService menuItemService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Restaurant saveRestaurant(RestaurantRequestDto restaurantDto) {
		Restaurant restaurant = new Restaurant(restaurantDto.getName(), restaurantDto.getLocation(), restaurantDto.getDistance(), restaurantDto.getCuisine(), restaurantDto.getBudget());
		Restaurant savedRestaurant = restaurantRepository.save(restaurant);
		FoodMenu foodMenu = new FoodMenu(restaurantDto.getOpeningTime(), restaurantDto.getClosingTime(), savedRestaurant);
		foodMenuService.saveMenu(foodMenu);
		return savedRestaurant;
	}

	@Override
	public RestaurantResponseDto findByName(String name) throws RestaurantSearchServiceException {
		List<Restaurant> restaurants = restaurantRepository.findByName(name);
		if(restaurants.size() == 0) {
			throw new RestaurantSearchServiceException("No Restaurants available for the specified restaurant name " + " ," +name);
		}
		return new RestaurantResponseDto(restaurants);
	}

	@Override
	public RestaurantResponseDto findByLocation(String location) throws RestaurantSearchServiceException {
		List<Restaurant> restaurants = restaurantRepository.findByLocation(location);
		if(restaurants.size() == 0) {
			throw new RestaurantSearchServiceException("No Restaurants available for the specified location " + " ," +location);
		}
		return new RestaurantResponseDto(restaurants);
	}

	@Override
	public RestaurantResponseDto findByDistance(double distance) throws RestaurantSearchServiceException{
		List<Restaurant> restaurants = restaurantRepository.findByDistance(distance);
		if(restaurants.size() == 0) {
			throw new RestaurantSearchServiceException("No Restaurants available for the specified distance " + " ," + distance);
		}
		return new RestaurantResponseDto(restaurants);
	}

	@Override
	public RestaurantResponseDto findByCuisine(String cuisine) throws RestaurantSearchServiceException {
		List<Restaurant> restaurants = restaurantRepository.findByCuisine(cuisine);
		if(restaurants.size() == 0) {
			throw new RestaurantSearchServiceException("No Restaurants available for the specified cuisine " + " ," + cuisine);
		}
		return new RestaurantResponseDto(restaurants);
	}

	@Override
	public RestaurantResponseDto findByBudget(int budget) throws RestaurantSearchServiceException {
		List<Restaurant> restaurants = restaurantRepository.findByBudget(budget);
		if(restaurants.size() == 0) {
			throw new RestaurantSearchServiceException("No Restaurants available for the specified budget " + " ," + budget);
		}
		return new RestaurantResponseDto(restaurants);
	}

	@Override
	public FoodMenuResponseDto findMenuItemByRestaurantId(Long restaurantId) throws RestaurantSearchServiceException {
		FoodMenu foodMenu = foodMenuService.getMenuByRestaurantId(restaurantId);
		List<MenuItem> menuItems = menuItemService.findByFoodMenuId(foodMenu.getFoodMenuId());
		if(menuItems.size() == 0) {
			throw new RestaurantSearchServiceException("No Menu Items found for given restaurant ");
		}
		List<MenuItemRequestDto> menuItemsDto = menuItems
				                                .stream()
				                                .map(MenuItem->modelMapper.map(MenuItem, MenuItemRequestDto.class))
				                                .collect(Collectors.toList());
		
		return new FoodMenuResponseDto(foodMenu.getOpeningTime(), foodMenu.getClosingTime(), menuItemsDto);
	}

}
