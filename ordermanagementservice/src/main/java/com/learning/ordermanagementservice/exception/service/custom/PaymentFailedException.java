package com.learning.ordermanagementservice.exception.service.custom;

import com.learning.ordermanagementservice.exception.service.OrderManagementServiceException;

public class PaymentFailedException extends OrderManagementServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PaymentFailedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PaymentFailedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PaymentFailedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PaymentFailedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
