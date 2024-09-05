package com.sbp.hotelservice.hotelservice.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException() {
		super("Hotel not found!");
	}
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}

}
