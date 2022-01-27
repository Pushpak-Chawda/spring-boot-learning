package com.pc.springboot.exception;

import java.util.Date;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails cse=new CustomErrorDetails("Method Arg Exception", new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<>(cse,HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		
		CustomErrorDetails cse=new CustomErrorDetails("HTTP Method Not  Supported", new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<>(cse,HttpStatus.METHOD_NOT_ALLOWED);
		
	}
	
	@ExceptionHandler(UserNameNotFoundException.class)
	public final  ResponseEntity<Object> handleUserNameNotFoundException(
			UserNameNotFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		
		CustomErrorDetails cse=new CustomErrorDetails("User Not Exist", new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<>(cse,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final  ResponseEntity<Object> handleConstraintViolationException(
			ConstraintViolationException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		
		
		CustomErrorDetails cse=new CustomErrorDetails("User Not Exist", new Date(), ex.getLocalizedMessage());
		
		return new ResponseEntity<>(cse,HttpStatus.BAD_REQUEST);
		
	}
	
	
	
}
