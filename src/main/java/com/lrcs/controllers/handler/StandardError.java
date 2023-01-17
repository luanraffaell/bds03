package com.lrcs.controllers.handler;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Instant timeStamp;
	private Integer Status;
	private String error;
	private String message;
	private String path;
	
	public StandardError() {
	}
	

	public StandardError(Integer status, String error, String message, String path) {
		Status = status;
		this.error = error;
		this.message = message;
		this.path = path;
		timeStamp = Instant.now();
	}


	public Instant getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Instant timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return Status;
	}

	public void setStatus(Integer status) {
		Status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}
