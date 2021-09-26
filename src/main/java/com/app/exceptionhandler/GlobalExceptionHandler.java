package com.app.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.custom_exception.CourseNotFoundException;
import com.app.dto.ErrorResponse;


@ControllerAdvice //mandatory : to tell SC following class contains centralized exc handler method/s
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<?> handleCourseNotFoundException(CourseNotFoundException e)
	{
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage(),LocalDateTime.now()) );
	
	}
	
	
	// exception handler for all the exception occured in the project 
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> catchAllException(RuntimeException e){
		e.printStackTrace();
		ErrorResponse errorResponse = new ErrorResponse();
	    errorResponse.setMessage("Unable to Full full the request ! SORRY TRY AGAIN AFTER SOME TIME") ;
	    errorResponse.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	
	// exception handler for all the exception occured in  the pojo validtion
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("in handle invalid meth args ");
	//	System.out.println(ex.getBindingResult().getFieldErrors());
	
	  StringBuilder sb = new StringBuilder();
	  ex.getBindingResult().getFieldErrors().forEach(e ->
	  sb.append(e.getDefaultMessage()+"  "));
	  
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(sb.toString(),LocalDateTime.now()));
	}
}
