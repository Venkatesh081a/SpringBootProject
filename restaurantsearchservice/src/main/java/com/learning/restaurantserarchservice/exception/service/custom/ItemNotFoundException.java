package com.learning.restaurantserarchservice.exception.service.custom;

import com.learning.restaurantserarchservice.exception.service.RestaurantSearchServiceException;

public class ItemNotFoundException extends RestaurantSearchServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ItemNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ItemNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ItemNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
