package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Student;
import com.app.service.IStudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {

	@Autowired
	IStudentService studentService;

	// controller to register the student
	@PostMapping("/registration")
	public ResponseEntity<?> register(@RequestBody @Valid Student student) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(studentService.studentRegister(student));
	}
}
