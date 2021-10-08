package com.learning.ordermanagementservice.service;

import java.util.Optional;


import org.springframework.cloud.context.config.annotation.RefreshScope;

import com.learning.ordermanagementservice.dto.OrderRequestDto;
import com.learning.ordermanagementservice.entity.Order;
import com.learning.ordermanagementservice.entity.OrderedItem;
import com.learning.ordermanagementservice.exception.service.OrderManagementServiceException;


public interface OrderService {

	Order placeOrder(OrderRequestDto orderRequestDto) throws OrderManagementServiceException;

	boolean cancelOrder(Long orderId) throws OrderManagementServiceException;

	Optional<Order> getOrderById(Long orderId) throws OrderManagementServiceException;

	double getOrderAmountByOrderId(Long orderId);

	Order updateOrder(OrderedItem item, Long orderId, String updateType);
}
