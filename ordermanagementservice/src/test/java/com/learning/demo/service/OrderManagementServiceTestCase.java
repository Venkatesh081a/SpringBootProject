package com.learning.demo.service;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestOperations;

import com.learning.ordermanagementservice.dto.FoodMenuDto;
import com.learning.ordermanagementservice.dto.MenuItemDto;
import com.learning.ordermanagementservice.dto.OrderRequestDto;
import com.learning.ordermanagementservice.dto.OrderedItemsDto;
import com.learning.ordermanagementservice.dto.RestaurantDto;
import com.learning.ordermanagementservice.entity.Order;
import com.learning.ordermanagementservice.entity.OrderedItem;
import com.learning.ordermanagementservice.entity.Payment;
import com.learning.ordermanagementservice.exception.service.OrderManagementServiceException;
import com.learning.ordermanagementservice.repository.OrderRepository;
import com.learning.ordermanagementservice.repository.PaymentRepository;
import com.learning.ordermanagementservice.service.serviceImpl.OrderServiceImpl;
import com.learning.ordermanagementservice.service.serviceImpl.OrderedItemServiceImpl;

@RunWith(SpringRunner.class)
public class OrderManagementServiceTestCase {
	
	@InjectMocks
	private OrderServiceImpl orderServiceImpl;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private PaymentRepository paymentRepository;
	
	@Mock
	private OrderedItemServiceImpl ItemServiceImpl;
	
	@Mock
	private RestOperations restTemplate;
	
	@BeforeEach
	void setUp() throws Exception {
	}
	
	
	@Test
	void testPlaceOrder() throws Exception {
		Payment payment = new Payment();
		payment.setAmount(20.0);
		payment.setOrderId(1L);
		payment.setPaymentId(123L);
		payment.setPaymentMethod("Phonepe");
		payment.setPaymentStatus("Success");
		payment.setTransactionId("23A456L789S");
		OrderRequestDto orderRequestDto = new OrderRequestDto();
		OrderedItemsDto orderedItemsDto = new OrderedItemsDto();
		orderedItemsDto.setItemId(123L);
		orderedItemsDto.setQuantity(10);
		orderRequestDto.setCustomerId(1L);
		orderRequestDto.setItems(Arrays.asList(orderedItemsDto));
		orderRequestDto.setOrderId(1L);
		orderRequestDto.setRestaurantId(1L);
		orderRequestDto.setPayment(payment);
		
		RestaurantDto restaurant = new RestaurantDto();
		restaurant.setId(1L);
		restaurant.setName("Three Chef");
		restaurant.setLocation("BBSR");
		restaurant.setDistance(10.0);
		restaurant.setCuisine("NorthIndian Foods");
		restaurant.setBudget(200);
		
		FoodMenuDto menu = new FoodMenuDto();
		menu.setId(1L);
		menu.setActiveFrom("10:00 AM");
		menu.setActiveTill("10:00 PM");
		menu.setRestaurant(restaurant);
		
		MenuItemDto menuItem = new MenuItemDto();
		menuItem.setMenuItemId(1L);
		menuItem.setName("Idly");
		menuItem.setDescription("MenuItem for ThreeChef");
		menuItem.setQuantity(1);
		menuItem.setPrice(10);
		menuItem.setMenu(menu);
		
		when(orderRepository.save(any(Order.class))).thenReturn(
				new Order(orderRequestDto.getOrderId(), orderRequestDto.getCustomerId(), orderRequestDto.getRestaurantId(), "ORDER_PLACED", orderRequestDto.getPayment().getAmount())
         );
		
		when(restTemplate.getForObject(any(String.class),any(Class.class))).thenReturn(menuItem);
		when(ItemServiceImpl.saveItem(any(OrderedItem.class))).thenReturn(new OrderedItem());
		
		when(paymentRepository.save(any(Payment.class))).thenReturn(
				new Payment(payment.getAmount(), payment.getTransactionId(), payment.getOrderId(), payment.getPaymentStatus(), payment.getPaymentMethod(), payment.getOrder())
				);
		
		Order order = orderServiceImpl.placeOrder(orderRequestDto);
		assertNotNull(order);
	}
	
	
	@Test
	void testCancelOrder() throws Exception {
		Optional<Order> order = Optional.of((Order) new Order(1L, 2L, "CREATED")); 
		Order order1 = new Order();
		order1.setCustomerId(1L);
		order1.setOrderId(1L);
		order1.setStatus("CREATED");
		Optional<Order> order2 = Optional.of(order1);
		when(orderRepository.findByOrderId(any(Long.class))).thenReturn(order2);
		when(orderRepository.save(any(Order.class))).thenReturn(order1);
		Boolean cancelledStatus = orderServiceImpl.cancelOrder(1L);
		assertTrue(cancelledStatus);
		assertEquals("Cancelled", order1.getStatus());
	}
	
	@Test
	void testCancelOrderAlreadyCacelled() throws Exception {
		Optional<Order> order = Optional.empty();
		when(orderRepository.findByOrderId(any(Long.class))).thenReturn(order);
		Boolean cancelledStatus = orderServiceImpl.cancelOrder(1L);
		assertFalse(cancelledStatus);
	}

	
	@Test
	void testGetOrderAmountByOrderId() {
		Optional<Order> order = Optional.of(new Order(1L, 1L, "created"));
		when(orderRepository.findByOrderId(any(Long.class))).thenReturn(order);
		List<OrderedItem> items = Arrays.asList(
		new OrderedItem("Idly", 1, 20, 1L, order.get()),
				new OrderedItem("Puri", 2, 40, 2L, order.get())
				);
		when(ItemServiceImpl.findByOrderId(any(Long.class))).thenReturn(items);
		double totalPrice = orderServiceImpl.getOrderAmountByOrderId(1L);
		assertEquals(100, totalPrice);
		
	}
	
	@Test
	void testGetOrderAmountByOrderIdWithEmptyAmount() {
		Optional<Order> order = Optional.empty();
		when(orderRepository.findByOrderId(any(Long.class))).thenReturn(order);
		List<OrderedItem> items = new ArrayList<OrderedItem>();
		items.add(new OrderedItem());
		double totalPrice = orderServiceImpl.getOrderAmountByOrderId(1L);
		assertEquals(0, totalPrice);
	}
	
	
	private Payment getOnePaymentDetail( double amount, String paymentStatusPaid) {
		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setPaymentId(1L);
		payment.setPaymentMethod("PhonePe");
		payment.setPaymentStatus(paymentStatusPaid);
		return payment;
	}

}
