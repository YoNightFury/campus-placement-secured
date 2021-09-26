package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.pojos.Student;
import com.app.service.IStudentService;

public class RegistrationController {

	@Autowired
	IStudentService studentService;
	
	
	 // url="http://localhost:8080/student/registration"
		// controller to register the student
	@PostMapping("/registration")
	public ResponseEntity<?> register(@RequestBody @Valid Student student) {	 
	   return ResponseEntity.status(HttpStatus.ACCEPTED).body( studentService.studentRegister(student));
	}
}
