package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.BaseEntity;
import com.app.pojos.Credential;
import com.app.security.utils.JwtUtils;
import com.app.service.IStudentService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

	
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	IStudentService studentService;
	
	
	// for user login authentication
		@PostMapping("/login")
		public ResponseEntity<?> validateLogin(@RequestBody Credential credential) {
			Object user = studentService.validateLogin(credential);
			String jwt = jwtUtils.generateJwt(((BaseEntity)user).getId(), credential.getUserName(), credential.getRole());
			return ResponseEntity
					.status(HttpStatus.ACCEPTED) // valid cred
					.header("Authorization", jwt) // pass the jwt
					.body(user); // response object student or admin
		}
	
}
