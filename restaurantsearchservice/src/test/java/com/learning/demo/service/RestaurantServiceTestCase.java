package com.learning.demo.service;
import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.learning.restaurantserarchservice.dto.RestaurantRequestDto;
import com.learning.restaurantserarchservice.dto.RestaurantResponseDto;
import com.learning.restaurantserarchservice.entity.FoodMenu;
import com.learning.restaurantserarchservice.entity.Restaurant;
import com.learning.restaurantserarchservice.exception.service.RestaurantSearchServiceException;
import com.learning.restaurantserarchservice.repostiory.RestaurantRepository;
import com.learning.restaurantserarchservice.service.FoodMenuService;
import com.learning.restaurantserarchservice.service.MenuItemService;
import com.learning.restaurantserarchservice.service.serviceImpl.RestaurantServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestaurantServiceImpl.class)
public class RestaurantServiceTestCase {
	
	@InjectMocks
	private RestaurantServiceImpl restaurantServiceImpl;
	
	@Mock
	private RestaurantRepository restaurantRepository;
	
	@Mock
	private FoodMenuService foodMenuService;
	
	@Mock
	private MenuItemService menuItemService;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSaveRestaurant() {
		RestaurantRequestDto restaurantRequest = new RestaurantRequestDto();
		restaurantRequest.setName("Fast Eats");
		restaurantRequest.setLocation("Chennai");
		restaurantRequest.setDistance(10.0);
		restaurantRequest.setCuisine("Homely Foods");
		restaurantRequest.setBudget(1000);
		restaurantRequest.setOpeningTime("10:00 AM");
		restaurantRequest.setClosingTime("10:00 PM");
		Restaurant restaurant = new Restaurant(restaurantRequest.getName(), restaurantRequest.getLocation(), restaurantRequest.getDistance(), restaurantRequest.getCuisine(), restaurantRequest.getBudget());
		when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
		Restaurant savedRestaurant = restaurantServiceImpl.saveRestaurant(restaurantRequest);
		FoodMenu foodMenu = new FoodMenu(restaurantRequest.getOpeningTime(), restaurantRequest.getClosingTime(), savedRestaurant);
		when(foodMenuService.saveMenu(any(FoodMenu.class))).thenReturn(foodMenu);
		assertNotNull(savedRestaurant);
		assertEquals("Fast Eats", savedRestaurant.getName());
		assertEquals("Chennai", savedRestaurant.getLocation());
		assertEquals("10.0", savedRestaurant.getDistance());
		assertEquals("Homely Foods", savedRestaurant.getCuisine());
		assertEquals(1000, savedRestaurant.getBudget());
	}
	
	
	@Test
	void testFindByName() throws Exception {
		List<Restaurant> restaurantsList = Arrays.asList(
				new Restaurant("South Restaurant", "Chennai", 10, "South India Foods", 100),
				new Restaurant("North Restaurant", "Nellore", 10, "North India Foods", 100)
				);
		when(restaurantRepository.findByName(any(String.class))).thenReturn(restaurantsList);
		RestaurantResponseDto restaurantDto = restaurantServiceImpl.findByName("South Restaurant");
		assertEquals("South Restaurant", restaurantDto.getRestaurants().get(0).getName());
	}
	

	@Test
	void testFindByLocation() throws Exception {
		List<Restaurant> restaurantsList = Arrays.asList(
				new Restaurant("South Restaurant", "Chennai", 10, "South India Foods", 100),
				new Restaurant("North Restaurant", "Nellore", 10, "North India Foods", 100)
				);
		when(restaurantRepository.findByLocation(any(String.class))).thenReturn(restaurantsList);
		RestaurantResponseDto restaurantDto = restaurantServiceImpl.findByLocation("Chennai");
		assertEquals("South Restaurant", restaurantDto.getRestaurants().get(0).getName());
	}
	
	@Test
	void testFindByDistance() throws Exception {
		List<Restaurant> restaurantsList = Arrays.asList(
				new Restaurant("South Restaurant", "Chennai", 10, "South India Foods", 100),
				new Restaurant("North Restaurant", "Nellore", 10, "North India Foods", 100)
				);
		when(restaurantRepository.findByDistance(any(Double.class))).thenReturn(restaurantsList);
		RestaurantResponseDto restaurantDto = restaurantServiceImpl.findByDistance(10.0);
		assertEquals("South Restaurant", restaurantDto.getRestaurants().get(0).getName());
	}

	@Test
	void testFindByCuisine() throws Exception {
		List<Restaurant> restaurantsList = Arrays.asList(
				new Restaurant("South Restaurant", "Chennai", 10, "South India Foods", 100),
				new Restaurant("North Restaurant", "Nellore", 10, "North India Foods", 100)
				);
		when(restaurantRepository.findByCuisine(any(String.class))).thenReturn(restaurantsList);
		RestaurantResponseDto restaurantDto = restaurantServiceImpl.findByCuisine("South India Foods");
		assertEquals("South Restaurant", restaurantDto.getRestaurants().get(0).getName());
	}

	@Test
	void testFindByBudget() throws Exception {
		List<Restaurant> restaurantsList = Arrays.asList(
				new Restaurant("South Restaurant", "Chennai", 10, "South India Foods", 100),
				new Restaurant("North Restaurant", "Nellore", 10, "North India Foods", 100)
				);
		when(restaurantRepository.findByBudget(any(Integer.class))).thenReturn(restaurantsList);
		RestaurantResponseDto restaurantDto = restaurantServiceImpl.findByBudget(1000);
		assertEquals("South Restaurant", restaurantDto.getRestaurants().get(0).getName());
	}

}
