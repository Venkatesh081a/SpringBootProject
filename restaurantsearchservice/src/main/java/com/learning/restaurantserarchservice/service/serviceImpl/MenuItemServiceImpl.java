package com.learning.restaurantserarchservice.service.serviceImpl;

import java.util.List;






import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.restaurantserarchservice.dto.MenuItemRequestDto;
import com.learning.restaurantserarchservice.entity.FoodMenu;
import com.learning.restaurantserarchservice.entity.MenuItem;
import com.learning.restaurantserarchservice.exception.service.RestaurantSearchServiceException;
import com.learning.restaurantserarchservice.exception.service.custom.FoodMenuNotFoundException;
import com.learning.restaurantserarchservice.exception.service.custom.ItemNotFoundException;
import com.learning.restaurantserarchservice.repostiory.MenuItemRepository;

import com.learning.restaurantserarchservice.service.MenuItemService;
import com.learning.restaurantserarchservice.service.FoodMenuService;

@Service
public class MenuItemServiceImpl implements MenuItemService {

	@Autowired
	private FoodMenuService foodMenuService;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Override
	public MenuItem saveMenuItem(MenuItemRequestDto menuItemRequestDto) throws RestaurantSearchServiceException {
		Optional<FoodMenu> foodMenu = foodMenuService.getMenuById(menuItemRequestDto.getMenuItemId());
       	if(foodMenu.isPresent()) {
			
			MenuItem menuItem = new MenuItem(menuItemRequestDto.getName(), menuItemRequestDto.getDescription(),menuItemRequestDto.getQuantity(), menuItemRequestDto.getPrice(), foodMenu.get());
		    return menuItemRepository.save(menuItem);
	      }
       	else {
       		throw new FoodMenuNotFoundException("FoodMenu not Found");
       	}
	}

	@Override
	public List<MenuItem> findByFoodMenuId(Long id) {
		
		return menuItemRepository.findByFoodMenuFoodMenuId(id);
	}

	@Override
	public MenuItem findById(Long id) throws ItemNotFoundException {
		MenuItem menuItem = menuItemRepository.findByMenuItemId(id);
		if (menuItem == null) {
			throw new ItemNotFoundException("No Item found for specified inputs");
		}
		return menuItem;
	}

	@Override
	public MenuItemRequestDto updateItemQuantityById(Long menuItemId, int quantity) {
		MenuItemRequestDto menuItemDto = null;
		MenuItem menuItem= menuItemRepository.findByMenuItemId(menuItemId);
		if(menuItem != null) {
			menuItem.setQuantity(menuItem.getQuantity()+quantity);
			menuItem = menuItemRepository.save(menuItem);
			menuItemDto = new MenuItemRequestDto();
			menuItemDto.setMenuItemId(menuItemId);
			menuItemDto.setQuantity(quantity);
		}
		return menuItemDto;
	}

}
