package com.learning.ordermanagementservice.service.serviceImpl;

import java.util.ArrayList;





import java.util.List;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.learning.ordermanagementservice.dto.MenuItemDto;
import com.learning.ordermanagementservice.dto.OrderRequestDto;
import com.learning.ordermanagementservice.dto.OrderUpdateRequestDto;
import com.learning.ordermanagementservice.dto.OrderUpdateResponseDto;
import com.learning.ordermanagementservice.dto.OrderedItemsDto;
import com.learning.ordermanagementservice.dto.TransactionRequest;
import com.learning.ordermanagementservice.entity.Order;
import com.learning.ordermanagementservice.entity.OrderedItem;
import com.learning.ordermanagementservice.entity.Payment;
import com.learning.ordermanagementservice.exception.service.OrderManagementServiceException;
import com.learning.ordermanagementservice.exception.service.custom.InsufficientQuantityException;
import com.learning.ordermanagementservice.exception.service.custom.OrderNotFoundException;
import com.learning.ordermanagementservice.exception.service.custom.PaymentFailedException;
import com.learning.ordermanagementservice.repository.OrderRepository;
import com.learning.ordermanagementservice.repository.PaymentRepository;
import com.learning.ordermanagementservice.service.OrderService;
import com.learning.ordermanagementservice.service.OrderedItemService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderedItemService orderedItemService;
	
	@Autowired
	RestOperations restTemplate;
	
	private static final String ENDPOINT_URL = "http://RESTAURANT-SEARCH-SERVICE/restaurant/item/id/";
	private static final String INCREASE_QUANTITY = "ADD";
	private static final String DECREASE_QUANTITY = "REMOVE";

	@Override
	public Order placeOrder(OrderRequestDto orderRequestDto) throws OrderManagementServiceException {
		double totalPrice = 0;
		Order order = new Order(orderRequestDto.getOrderId(),orderRequestDto.getCustomerId(), orderRequestDto.getRestaurantId(), "Created",totalPrice);
		Order savedOrder = orderRepository.save(order);
		List<OrderedItemsDto>  itemsOrdered = orderRequestDto.getItems();
		for(OrderedItemsDto itemsDto : itemsOrdered) {

			MenuItemDto menuItem = restTemplate.getForObject(ENDPOINT_URL+itemsDto.getItemId(), MenuItemDto.class);
			
			totalPrice = totalPrice + (menuItem.getPrice()*itemsDto.getQuantity());
			
			if( itemsDto.getQuantity()<=0) {
				orderRepository.delete(order);
				throw new InsufficientQuantityException("Quantity of the item cannot be Zero");
			}
			
			OrderedItem orderedItem = new OrderedItem(menuItem.getName(), itemsDto.getQuantity(), menuItem.getPrice(), menuItem.getMenuItemId(), savedOrder);
		    orderedItemService.saveItem(orderedItem);
	}
		order.setTotalPrice(totalPrice);
		orderRepository.save(order);
		Payment payment = orderRequestDto.getPayment();
		payment.setOrderId(orderRequestDto.getOrderId());
		payment.setAmount(totalPrice);
		payment.setTransactionId(UUID.randomUUID().toString());
        if(paymentProcessing().equalsIgnoreCase("Success"))
        {
		payment.setPaymentStatus(paymentProcessing());
		payment.setOrder(order);
		payment.setCustomerId(orderRequestDto.getCustomerId());
		paymentRepository.save(payment);
        }
        else {
        	throw new PaymentFailedException("Payment Failed , Something wrong in Payment ApI");
        }
		return savedOrder;
	}
	

	@Override
	public boolean cancelOrder(Long orderId) throws OrderManagementServiceException {
		Optional<Order> order = orderRepository.findByOrderId(orderId);
		if(order.isPresent()) {
			if(order.get().getStatus().equalsIgnoreCase("Cancelled")) {
				throw new OrderNotFoundException("This Order is already Cancelled");
			}
			order.get().setStatus("Cancelled");
			orderRepository.save(order.get());
			return true;
		}
		else {
			throw new OrderNotFoundException("No records available for the specified id");
		}
	}

	@Override
	public Order updateOrder(OrderedItem item, Long orderId, String updateType) {
		Order order = orderRepository.findByOrderId(orderId).orElse(null);
		boolean updateStatus = false;
		List<OrderedItem> itemsToUpdate = new ArrayList<OrderedItem>();
		try {
			List<OrderedItem> items = order.getItems();
			updateItemQuantity(items, item, updateType);
			if(updateType.equalsIgnoreCase(INCREASE_QUANTITY)) {
				item.setQuantity(item.getQuantity() * -1);
				itemsToUpdate.add(item);
				updateStatus = updateItemQtyInDb(itemsToUpdate, updateType);
			}
			else {
				itemsToUpdate.add(item);
				updateStatus = updateItemQtyInDb(itemsToUpdate, updateType);
			}
			if(updateStatus) {
				updatePaymentDetailsAndTotalAmount(item, order, updateType);
				order = orderRepository.save(order);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return order;
	}

	@Override
	public Optional<Order> getOrderById(Long orderId) throws OrderManagementServiceException {
		Optional<Order> order =  orderRepository.findByOrderId(orderId);
		if(order.isPresent()) {
			return order;
		}
		 throw new OrderNotFoundException("No records available for the specified id");
	}

	
	public String paymentProcessing() {
		return new Random().nextBoolean()?"Success":"Failure";
	}
	
	@Override
	public double getOrderAmountByOrderId(Long orderId) {
		Optional<Order> order = orderRepository.findByOrderId(orderId);
		if(order.isPresent()) {
			List<OrderedItem> itemsOrdered = orderedItemService.findByOrderId(orderId);
			double totalPrice = 0;
			for(OrderedItem items : itemsOrdered) {
				totalPrice = totalPrice + (items.getPrice()*items.getQuantity());
			}
			return totalPrice;
		}
		else {
			return 0;
		}
		
	}


	private void updateItemQuantity(List<OrderedItem> items, OrderedItem item, String updateType) {
		for (OrderedItem itemInDb : items) {
			if (itemInDb.getItemId() == item.getItemId()
					&& updateType.equalsIgnoreCase(INCREASE_QUANTITY)) {
				itemInDb.setQuantity(item.getQuantity() + itemInDb.getQuantity());
			} else if (itemInDb.getItemId() == item.getItemId()
					&& updateType.equalsIgnoreCase(DECREASE_QUANTITY)) {
				itemInDb.setQuantity(itemInDb.getQuantity()- item.getQuantity());
			}
		}
	}
	
	private void updatePaymentDetailsAndTotalAmount(OrderedItem orderedItem, Order order, String updateType) {
		double updatedTotalPrice;
		if(updateType.equalsIgnoreCase(DECREASE_QUANTITY)) {
			updatedTotalPrice=order.getTotalPrice()-(orderedItem.getQuantity()*orderedItem.getPrice());
		}
		else {
			updatedTotalPrice=order.getTotalPrice()+(orderedItem.getQuantity()*orderedItem.getPrice());
		}
		order.getPayment().setAmount(updatedTotalPrice);
		order.setTotalPrice(updatedTotalPrice);
	}

	private boolean updateItemQtyInDb(List<OrderedItem> itemsOrdered, String requestStatus)
			throws OrderManagementServiceException{
		MenuItemDto menuItem = null;
		boolean status = true;
		for (OrderedItem food : itemsOrdered) {
			 menuItem = restTemplate.getForObject(ENDPOINT_URL+food.getItemId(), MenuItemDto.class);
			if (menuItem != null && requestStatus.equalsIgnoreCase(INCREASE_QUANTITY)) {
				if ((food.getQuantity()) > menuItem.getQuantity()) {
					throw new InsufficientQuantityException("Insufficient quantity");
				}
			}
			updateItemQtyByItemId(food);
			if (menuItem == null) {
				status = false;
			}
		}
		return status;
	}
	

	public OrderedItem updateItemQtyByItemId(OrderedItem food) {
		HttpEntity<OrderedItem> request = new HttpEntity<>(food);
		ResponseEntity<OrderedItem> response = restTemplate
		  .exchange(ENDPOINT_URL, HttpMethod.POST, request, OrderedItem.class);
		return response.getBody();
	}
}
