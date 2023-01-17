package com.lrcs.controllers.handler;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public void addErrors(String fieldName,String message) {
		errors.add(new FieldMessage(fieldName, message));
	}
	public List<FieldMessage> getErrors() {
		return errors;
	}

}
