package com.learning.demo.controller;
import static org.mockito.ArgumentMatchers.any;



import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.restaurantserarchservice.controller.RestaurantController;
import com.learning.restaurantserarchservice.dto.FoodMenuResponseDto;
import com.learning.restaurantserarchservice.dto.MenuItemRequestDto;
import com.learning.restaurantserarchservice.dto.RestaurantRequestDto;
import com.learning.restaurantserarchservice.dto.RestaurantResponseDto;
import com.learning.restaurantserarchservice.entity.Restaurant;
import com.learning.restaurantserarchservice.service.FoodMenuService;
import com.learning.restaurantserarchservice.service.RestaurantService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RestaurantController.class)
public class RestaurantControllerTestCase {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private RestaurantService restaurantService;
	
	@MockBean
	private FoodMenuService foodMenuService;
	
	@MockBean
	private FoodMenuResponseDto foodMenuResponseDto;
	
	@MockBean
	private MenuItemRequestDto menuItemRequestDto;
	
	Restaurant restaurant;
	
	String Response = "Restaurant Details Added Successfully";
	
	@BeforeEach
	public void setUp() throws Exception {
		
	}

	@Test
	public void testAddRestaurant() throws Exception {
		RestaurantRequestDto restaurantRequest = new RestaurantRequestDto();
		restaurantRequest.setName("Fast Eats");
		restaurantRequest.setLocation("Chennai");
		restaurantRequest.setDistance(10.0);
		restaurantRequest.setCuisine("Homely Foods");
		restaurantRequest.setBudget(1000);
		restaurantRequest.setOpeningTime("10:00 AM");
		restaurantRequest.setClosingTime("10:00 PM");
		String jsonRequest = objectMapper.writeValueAsString(restaurantRequest);
		when(restaurantService.saveRestaurant(any(RestaurantRequestDto.class))).thenReturn(new Restaurant());
		RequestBuilder buildRequest = MockMvcRequestBuilders.post(
				"/restaurant/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest);
		mockMvc.perform(buildRequest).andExpect(status().isCreated()).andExpect(content().string(Response)).andReturn();
	}

	
	@Test
	public void testGetRestaurantByName() throws Exception {
		List<Restaurant> resturantsList = Arrays.asList(
				new Restaurant("South Restaurant", "Chennai", 10, "South India Foods", 100),
				new Restaurant("North Restaurant", "Nellore", 10, "North India Foods", 100)
				);
		RestaurantResponseDto restaurantResponse = new RestaurantResponseDto(resturantsList);
		when(restaurantService.findByName(any(String.class))).thenReturn(restaurantResponse);

		RequestBuilder buildRequest = MockMvcRequestBuilders
				.get("/restaurant/name/ChennaiRestaurant")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void testGetRestaurantByLocation() throws Exception {
		List<Restaurant> resturantsList = Arrays.asList(
				new Restaurant("South Restaurant", "Chennai", 10, "South India Foods", 100),
				new Restaurant("North Restaurant", "Nellore", 10, "North India Foods", 100)
				);
		RestaurantResponseDto restaurantResponse = new RestaurantResponseDto(resturantsList);
		when(restaurantService.findByLocation(any(String.class))).thenReturn(restaurantResponse);
		RequestBuilder buildRequest = MockMvcRequestBuilders
				.get("/restaurant/location/Nellore")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
	}
	
	@Test
	public void testGetRestaurantByDistance() throws Exception {
		List<Restaurant> resturantsList = Arrays.asList(
				new Restaurant("South Restaurant", "Chennai", 10, "South India Foods", 100),
				new Restaurant("North Restaurant", "Nellore", 10, "North India Foods", 100)
				);
		RestaurantResponseDto restaurantResponse = new RestaurantResponseDto(resturantsList);
		when(restaurantService.findByDistance(any(Double.class))).thenReturn(restaurantResponse);
		RequestBuilder buildRequest = MockMvcRequestBuilders
				.get("/restaurant/distance/2.0")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testGetRestaurantByCuisine() throws Exception {
		List<Restaurant> resturantsList = Arrays.asList(
				new Restaurant("South Restaurant", "Chennai", 10, "South India Foods", 100),
				new Restaurant("North Restaurant", "Nellore", 10, "North India Foods", 100)
				);
		RestaurantResponseDto restaurantResponse = new RestaurantResponseDto(resturantsList);
		when(restaurantService.findByCuisine(any(String.class))).thenReturn(restaurantResponse);
		RequestBuilder buildRequest = MockMvcRequestBuilders
				.get("/restaurant/cuisine/HomelyFoods")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
	}

	
	@Test
	public void testGetRestaurantByBudget() throws Exception {
		List<Restaurant> resturantsList = Arrays.asList(
				new Restaurant("South Restaurant", "Chennai", 10, "South India Foods", 100),
				new Restaurant("North Restaurant", "Nellore", 10, "North India Foods", 100)
				);
		RestaurantResponseDto restaurantResponse = new RestaurantResponseDto(resturantsList);
		when(restaurantService.findByBudget(any(Integer.class))).thenReturn(restaurantResponse);
		RequestBuilder buildRequest = MockMvcRequestBuilders
				.get("/restaurant/cuisine/HomelyFoods")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
	}
	
	
	@Test
	public void testGetMenuItemsByRestaurantId() throws Exception {
		List<MenuItemRequestDto> menuItemsList = Arrays.asList(
				new MenuItemRequestDto(12345L, "Dosa", "Menuitem for Chennai Restaurant", 1, 20),
				new MenuItemRequestDto(6789L, "Idly", "Menuitem for Chennai Restaurant", 1, 15)
				);
		FoodMenuResponseDto foodMenu = new FoodMenuResponseDto("10:00 AM", "10:00 PM", menuItemsList);
		when(restaurantService.findMenuItemByRestaurantId(any(Long.class))).thenReturn(foodMenu);
		RequestBuilder buildRequest = MockMvcRequestBuilders
				.get("/restaurant/menuitems/1")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(buildRequest).andExpect(status().isOk()).andReturn();
	}
}
