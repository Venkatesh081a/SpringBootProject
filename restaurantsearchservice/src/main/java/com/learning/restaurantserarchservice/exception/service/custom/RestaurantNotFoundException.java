package com.learning.restaurantserarchservice.exception.service.custom;

import com.learning.restaurantserarchservice.exception.service.RestaurantSearchServiceException;

public class RestaurantNotFoundException extends RestaurantSearchServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RestaurantNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestaurantNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RestaurantNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RestaurantNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RestaurantNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
