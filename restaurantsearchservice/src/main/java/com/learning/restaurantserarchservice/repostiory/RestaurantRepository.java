package com.learning.restaurantserarchservice.repostiory;

import java.util.List;





import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.restaurantserarchservice.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	List<Restaurant> findByName(String name);

	List<Restaurant> findByLocation(String location);

	List<Restaurant> findByDistance(double distance);

	List<Restaurant> findByCuisine(String cuisine);

	List<Restaurant> findByBudget(int budget);

}
