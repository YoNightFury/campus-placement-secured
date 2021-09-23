package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Credential;
import com.app.pojos.Student;
import com.app.security.utils.JwtUtils;
import com.app.service.IStudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/views")
public class PublicController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IStudentService studentService;


	

}
