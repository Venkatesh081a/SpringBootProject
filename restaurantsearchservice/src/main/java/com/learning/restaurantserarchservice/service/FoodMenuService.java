package com.learning.restaurantserarchservice.service;

import java.util.Optional;



import com.learning.restaurantserarchservice.entity.FoodMenu;

public interface FoodMenuService {

	FoodMenu saveMenu(FoodMenu menu);

	Optional<FoodMenu> getMenuById(Long menuId);

	FoodMenu getMenuByRestaurantId(Long restaurantId);

	

}
