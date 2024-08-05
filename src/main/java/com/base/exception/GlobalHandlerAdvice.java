package com.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@Controller not mandatory to write
@ControllerAdvice
public class GlobalHandlerAdvice {

	@ExceptionHandler(value = FailedToSaveProduct.class)
	public ResponseEntity<String> filedToStoreProduct(){
		return new ResponseEntity<>("Failed to store product(s)", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
