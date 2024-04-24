package com.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//@Controller not mandetory to write
@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(value = FailedToSaveProduct.class)
	public ResponseEntity<String> filedToStoreProduct(){
		return new ResponseEntity<String>("Failed to store product(s)",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
