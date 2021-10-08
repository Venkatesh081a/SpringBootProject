package com.learning.restaurantserarchservice.service;

import java.util.List;

import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.learning.restaurantserarchservice.dto.MenuItemRequestDto;
import com.learning.restaurantserarchservice.entity.MenuItem;
import com.learning.restaurantserarchservice.exception.service.RestaurantSearchServiceException;
import com.learning.restaurantserarchservice.exception.service.custom.ItemNotFoundException;

public interface MenuItemService {

	MenuItem saveMenuItem(MenuItemRequestDto menuItemRequestDto) throws RestaurantSearchServiceException;

	List<MenuItem> findByFoodMenuId(Long id);

	MenuItem findById(Long id) throws ItemNotFoundException;

	public MenuItemRequestDto updateItemQuantityById(Long menuItemId, int quantity);


}
