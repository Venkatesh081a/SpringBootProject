package com.learning.restaurantserarchservice.exceptionHandler;

import javax.servlet.http.HttpServletRequest;


import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.restaurantserarchservice.exception.RestaurantSearchServiceAppException;
import com.learning.restaurantserarchservice.utility.ExceptionHandling;

@RestControllerAdvice(annotations = RestController.class)
public class ExceptionHandler {
	
	@org.springframework.web.bind.annotation.ExceptionHandler(RestaurantSearchServiceAppException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ExceptionHandling exceptionGetter(final RestaurantSearchServiceAppException exception, final HttpServletRequest request) {
		ExceptionHandling exceptionResult = new ExceptionHandling();
		exceptionResult.setMessage(exception.getMessage());
		exceptionResult.setUrl(request.getRequestURI());
		return exceptionResult;
	}
}
