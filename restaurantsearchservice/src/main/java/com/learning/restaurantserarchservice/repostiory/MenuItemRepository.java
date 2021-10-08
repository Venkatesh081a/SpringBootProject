package com.learning.restaurantserarchservice.repostiory;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.restaurantserarchservice.entity.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

	List<MenuItem> findByFoodMenuFoodMenuId(Long id);

	MenuItem findByMenuItemId(Long id);
}
