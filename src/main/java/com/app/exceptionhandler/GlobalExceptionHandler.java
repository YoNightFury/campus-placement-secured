package com.app.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
}
