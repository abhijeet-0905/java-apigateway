package com.sbp.userservice.userservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException() {
		super("User not found!");
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
