package com.app.exceptionhandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// send the localised message with unauthorised message
//		System.out.print("asdasd");
//		response.sendError(401, authException.getLocalizedMessage());
		// set the content type
		// instead set the status 
		// write the response
		response.setContentType("application/json");
		response.setStatus(401);
		response.getOutputStream().println(authException.getLocalizedMessage());
		
	}

}
