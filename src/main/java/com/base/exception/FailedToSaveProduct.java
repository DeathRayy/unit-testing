package com.base.exception;

import java.io.Serial;

public class FailedToSaveProduct extends RuntimeException{
	@Serial
	private static final long serialVersionUID = 1L;

	public FailedToSaveProduct(String msg) {
		super();
	}
}
