package com.learning.restaurantserarchservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.learning.restaurantserarchservice.dto.MenuItemRequestDto;
import com.learning.restaurantserarchservice.entity.MenuItem;
import com.learning.restaurantserarchservice.exception.RestaurantSearchServiceAppException;
import com.learning.restaurantserarchservice.service.MenuItemService;



@Controller
public class MenuItemController {
	
	@Autowired
	private MenuItemService menuItemService;

	
	@PostMapping("/restaurant/menu/add")
	public ResponseEntity<String> addItemsToRestaurantMenu(@RequestBody MenuItemRequestDto menuItemRequestDto) throws RestaurantSearchServiceAppException{
		
		menuItemService.saveMenuItem(menuItemRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body("Menu Item Added Successfully");	
	}
	
	
	@GetMapping("/restaurant/item/id/{id}")
	public ResponseEntity<MenuItem> getItemById( @PathVariable Long id) throws RestaurantSearchServiceAppException{
		MenuItem item = menuItemService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(item);	
    
}
	
	@PostMapping("/restaurant/update/MenuItems")
	public ResponseEntity<?> updateItemQuantity(@RequestBody MenuItemRequestDto menuItem){
		return new ResponseEntity<MenuItemRequestDto>(menuItemService.updateItemQuantityById(menuItem.getMenuItemId(), menuItem.getQuantity()), HttpStatus.OK);
	}
}
