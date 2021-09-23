package com.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.JWT;
import com.app.pojos.Credential;
import com.app.pojos.Role;
import com.app.pojos.Student;
import com.app.security.utils.JwtUtils;
import com.app.service.IStudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IStudentService studentService;

	/**
	 * 
	 * @param year : path variable
	 * @param batch : path variable
	 * @param courseName : path variable
	 * @param student : un-marshaled student object posted from form
	 * @return : response entity of jwt token
	 * registers the student and with the specified course
	 * creates a jwt with isRegistrable claim and no username
	 */
	@PostMapping("/{year}/{batch}/{courseName}")
	public ResponseEntity<?> studentRegistration(@PathVariable int year, @PathVariable String batch,
			@PathVariable String courseName, @RequestBody Student student) {
		Student student2 = studentService.studentRegistration(year, batch, courseName, student);
		String jwt = jwtUtils.generateJwt(student2.getId(), null, Role.STUDENT);
		return new ResponseEntity<>(new JWT(jwt), HttpStatus.CREATED);

	}

	/**
	 * 
	 * @param credential : credential object of username and password
	 * @param req : http request for extracting jwt from header
	 * @return : response entity with jwt token
	 * the added student will have a registrable jwt
	 * we add credentials to this and create new jwt based on the username and id and claim as student
	 */
	@PostMapping("/credential")
	public ResponseEntity<?> setStudentCredential(@RequestBody Credential credential, HttpServletRequest req) {
		String jwt = jwtUtils.extractJwtFromRequest(req);
		int sid = jwtUtils.getUserIdFromJwt(jwt);
		studentService.studentCredential(sid, credential);
		jwt = jwtUtils.generateJwt(sid, credential.getUserName(), credential.getRole());
		return ResponseEntity.status(HttpStatus.CREATED).body(new JWT(jwt));
	}

}
