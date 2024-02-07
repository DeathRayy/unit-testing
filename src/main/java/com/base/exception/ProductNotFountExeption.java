package com.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ProductNotFountExeption extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ProductNotFountExeption(String msg) {
		super(msg);
	}

}
