package com.app.custom_exception;

public class InvalidCredentialException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCredentialException(String message) {
		super(message);
	}

	

}
