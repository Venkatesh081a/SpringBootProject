package com.learning.ordermanagementservice.service;

import java.util.List;


import com.learning.ordermanagementservice.entity.OrderedItem;

public interface OrderedItemService {

	List<OrderedItem> findByOrderId(Long orderId);

	OrderedItem saveItem(OrderedItem orderedItem);

	void deleteItemsById(Long itemId);

}
