package com.learning.ordermanagementservice.service.serviceImpl;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.ordermanagementservice.entity.OrderedItem;
import com.learning.ordermanagementservice.repository.OrderedItemRepository;
import com.learning.ordermanagementservice.service.OrderedItemService;

@Service
public class OrderedItemServiceImpl implements OrderedItemService {
	
	@Autowired
	private OrderedItemRepository orderedItemRepository;

	public OrderedItem saveOrderedItem(OrderedItem orderedItem) {
		return orderedItemRepository.save(orderedItem);
	}
	
	@Override
	public List<OrderedItem> findByOrderId(Long orderId) {
		return orderedItemRepository.findByOrderOrderId(orderId);
	}

	@Override
	public OrderedItem saveItem(OrderedItem orderedItem) {
		return orderedItemRepository.save(orderedItem);
	}

	@Override
	public void deleteItemsById(Long itemId) {
	 orderedItemRepository.deleteByOrderedItemId(itemId);
	}
}
