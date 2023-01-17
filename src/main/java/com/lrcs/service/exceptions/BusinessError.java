package com.lrcs.service.exceptions;

public class BusinessError extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BusinessError(String msg) {
		super(msg);
	}
}
