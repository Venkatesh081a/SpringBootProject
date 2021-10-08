package com.learning.ordermanagementservice.exception.service;

import com.learning.ordermanagementservice.exception.OrderManagementServiceAppException;

public class OrderManagementServiceException extends OrderManagementServiceAppException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderManagementServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderManagementServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public OrderManagementServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public OrderManagementServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public OrderManagementServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
