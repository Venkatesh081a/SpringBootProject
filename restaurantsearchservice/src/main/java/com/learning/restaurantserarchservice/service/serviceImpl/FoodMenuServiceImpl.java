package com.learning.restaurantserarchservice.service.serviceImpl;

import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.restaurantserarchservice.entity.FoodMenu;
import com.learning.restaurantserarchservice.repostiory.FoodMenuRepository;
import com.learning.restaurantserarchservice.service.FoodMenuService;

@Service
public class FoodMenuServiceImpl implements FoodMenuService{
	
	@Autowired
	private FoodMenuRepository foodMenuRepository;

	@Override
	public FoodMenu saveMenu(FoodMenu menu) {
		return foodMenuRepository.save(menu);
	}

	@Override
	public Optional<FoodMenu> getMenuById(Long menuId) {
		return foodMenuRepository.findByFoodMenuId(menuId);
	}

	@Override
	public FoodMenu getMenuByRestaurantId(Long restaurantId) {
		
		return foodMenuRepository.findByRestaurantRestaurantId(restaurantId);
	}

}
