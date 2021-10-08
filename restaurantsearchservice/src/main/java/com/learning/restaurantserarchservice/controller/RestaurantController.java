package com.learning.restaurantserarchservice.controller;




import org.springframework.beans.factory.annotation.Autowired;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.restaurantserarchservice.dto.FoodMenuResponseDto;
import com.learning.restaurantserarchservice.dto.RestaurantRequestDto;
import com.learning.restaurantserarchservice.dto.RestaurantResponseDto;
import com.learning.restaurantserarchservice.exception.RestaurantSearchServiceAppException;
import com.learning.restaurantserarchservice.service.FoodMenuService;
import com.learning.restaurantserarchservice.service.RestaurantService;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private FoodMenuService foodMenuService;
	
	@PostMapping("/restaurant/add")
	public ResponseEntity<String> addRestaurant(@RequestBody RestaurantRequestDto restaurantDto){
		
		restaurantService.saveRestaurant(restaurantDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Restaurant Details Added Successfully");
    }
	
	
	@GetMapping("/restaurant/name/{name}")
	public ResponseEntity<RestaurantResponseDto> getRestaurantByName(@PathVariable String name) throws RestaurantSearchServiceAppException{
		RestaurantResponseDto restaurantResponseDto = restaurantService.findByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(restaurantResponseDto);
	}
	

	@GetMapping("/restaurant/location/{location}")
	public ResponseEntity<RestaurantResponseDto> getRestaurantByLocation(@PathVariable String location) throws RestaurantSearchServiceAppException{
		RestaurantResponseDto restaurantResponseDto = restaurantService.findByLocation(location);
		return ResponseEntity.status(HttpStatus.OK).body(restaurantResponseDto);
		
	}

	
	@GetMapping("/restaurant/distance/{distance}")
	public ResponseEntity<RestaurantResponseDto> getRestaurantByDistance(@PathVariable double distance) throws RestaurantSearchServiceAppException{
		RestaurantResponseDto restaurantResponseDto = restaurantService.findByDistance(distance);
		return ResponseEntity.status(HttpStatus.OK).body(restaurantResponseDto);
		
	}
	
	
	@GetMapping("/restaurant/cuisine/{cuisine}")
	public ResponseEntity<RestaurantResponseDto> getRestaurantByCuisine(@PathVariable String cuisine) throws RestaurantSearchServiceAppException{
		RestaurantResponseDto restaurantResponseDto = restaurantService.findByCuisine(cuisine);
		return ResponseEntity.status(HttpStatus.OK).body(restaurantResponseDto);
	}
	
	
	@GetMapping("/restaurant/budget/{budget}")
	public ResponseEntity<RestaurantResponseDto> getRestaurantByBudget(@PathVariable int budget) throws RestaurantSearchServiceAppException{
		RestaurantResponseDto restaurantResponseDto = restaurantService.findByBudget(budget);
		return ResponseEntity.status(HttpStatus.OK).body(restaurantResponseDto);
	}
	
	@GetMapping("/restaurant/menuitems/{restaurantId}")
	public ResponseEntity<FoodMenuResponseDto> getMenuItemsByRestaurantId(@PathVariable Long restaurantId) throws RestaurantSearchServiceAppException{
		FoodMenuResponseDto foodMenu = restaurantService.findMenuItemByRestaurantId(restaurantId);
		return ResponseEntity.status(HttpStatus.OK).body(foodMenu);
	}
}
