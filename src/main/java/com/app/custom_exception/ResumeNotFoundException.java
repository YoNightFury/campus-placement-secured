package com.app.custom_exception;

public class ResumeNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ResumeNotFoundException e;

	private ResumeNotFoundException(String mseg) {
		super(mseg);
	}
	
	public static ResumeNotFoundException getException() {
		if(e==null)
			e= new ResumeNotFoundException(
					"Resume has not been uploaded by the user yet!!");
		return e;
	}

}
