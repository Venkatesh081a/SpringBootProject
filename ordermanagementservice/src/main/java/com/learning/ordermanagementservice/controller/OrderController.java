package com.learning.ordermanagementservice.controller;

import java.util.Optional;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learning.ordermanagementservice.dto.OrderRequestDto;
import com.learning.ordermanagementservice.entity.Order;
import com.learning.ordermanagementservice.entity.OrderedItem;
import com.learning.ordermanagementservice.exception.OrderManagementServiceAppException;
import com.learning.ordermanagementservice.service.OrderService;
import com.learning.ordermanagementservice.service.PaymentService;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/order/placeOrder")
	public ResponseEntity<Order> placeOrder(@RequestBody OrderRequestDto order) throws OrderManagementServiceAppException, JsonProcessingException {
		Order order1 = orderService.placeOrder(order);
		return ResponseEntity.status(HttpStatus.OK).body(order1);
	}
	
	@PutMapping("/order/cancelOrder/{orderId}")
	public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) throws OrderManagementServiceAppException{
		boolean status = orderService.cancelOrder(orderId);
		return ResponseEntity.status(HttpStatus.OK).body("Order Cancelled Successfully");
	}
	
	
	public ResponseEntity<Order> updateOrder(@RequestBody OrderedItem Item, @PathVariable Long orderId, @PathVariable String updateType){
		Order order = orderService.updateOrder(Item , orderId, updateType);
		return ResponseEntity.status(HttpStatus.OK).body(order);
	}
	

	
	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> viewOrder(@PathVariable Long orderId) throws OrderManagementServiceAppException{
		Optional<Order> order = orderService.getOrderById(orderId);
		if(order.isPresent()) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(order.get());
		}
		return null;
	}
	
	@GetMapping("/order/orderAmount/{orderId}")
	public ResponseEntity<Double> getOrderAmountByOrderId(@PathVariable Long orderId){
		double price = orderService.getOrderAmountByOrderId(orderId);
		return ResponseEntity.status(HttpStatus.OK).body(price);
	}

}
