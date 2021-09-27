package com.app.custom_exception;

public class StudentNotFound extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static StudentNotFound e;

	private StudentNotFound(String mseg) {
		super(mseg);
	}
	
	public static StudentNotFound getException() {
		if(e==null)
			e= new StudentNotFound(
					"Cannot find the Student!!");
		return e;
	}
}
