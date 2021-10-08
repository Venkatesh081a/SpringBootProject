package com.learning.restaurantserarchservice.repostiory;




import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.restaurantserarchservice.entity.FoodMenu;

@Repository
public interface FoodMenuRepository extends JpaRepository<FoodMenu, Integer>{

	Optional<FoodMenu> findByFoodMenuId(Long foodMenuId);
	

	FoodMenu findByRestaurantRestaurantId(Long restaurantId);

	
}
