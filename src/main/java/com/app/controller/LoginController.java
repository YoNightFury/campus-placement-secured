package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LoginDto;
import com.app.pojos.BaseEntity;
import com.app.pojos.Credential;
import com.app.security.utils.JwtUtils;
import com.app.service.IValidationService;

@RestController
public class LoginController {

	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private IValidationService validateService;

	// for user login authentication
	@PostMapping("/login")
	public ResponseEntity<?> validateLogin(@RequestBody Credential credential) {
		Object user = validateService.validateLogin(credential);
//			System.out.println("name "+credential.getUserName()+ " pass "+credential.getPassword()+" role"+credential.getRole());
		String jwt = jwtUtils.generateJwt(((BaseEntity) user).getId(), credential.getUserName(), credential.getRole());
		LoginDto login = new LoginDto();
		login.setJwt(jwt);
		login.setUser(user);
		return ResponseEntity.status(HttpStatus.ACCEPTED) // valid cred
				.body(login);
	}

}
