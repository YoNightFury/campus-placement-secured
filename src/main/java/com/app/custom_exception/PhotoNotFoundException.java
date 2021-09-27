package com.app.custom_exception;

public class PhotoNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static PhotoNotFoundException e;

	private PhotoNotFoundException(String mseg) {
		super(mseg);
	}
	
	public static PhotoNotFoundException getException() {
		if(e==null)
			e= new PhotoNotFoundException(
					"Photo has not been uploaded by the user yet!!");
		return e;
	}

}
