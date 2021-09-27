package com.app.custom_exception;

public class StudentNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StudentNotFound() {
		super("Cannot Find the requested Student!!");
	}
	
}
