package com.learning.ordermanagementservice.dto;

import com.learning.ordermanagementservice.entity.Order;

import com.learning.ordermanagementservice.entity.Payment;

public class TransactionRequest {
	
	private Order order;
	
	private Payment payment;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public TransactionRequest(Order order, Payment payment) {
		super();
		this.order = order;
		this.payment = payment;
	}

	public TransactionRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
