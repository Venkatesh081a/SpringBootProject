package com.learning.ordermanagementservice.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.learning.ordermanagementservice.entity.OrderedItem;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {


	List<OrderedItem> findByOrderOrderId(Long orderId);

	void deleteByOrderedItemId(Long orderedItemId);

}
