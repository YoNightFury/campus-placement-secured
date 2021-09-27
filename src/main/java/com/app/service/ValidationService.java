package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.custom_exception.InvalidCredentialException;
import com.app.dao.AdminRepository;
import com.app.dao.CredentialRepository;
import com.app.dao.StudentRepository;
import com.app.pojos.Credential;

public class ValidationService implements IValidationService {

	@Autowired
	StudentRepository studentRepo;

	@Autowired
	AdminRepository adminRepo;

	@Autowired
	CredentialRepository credRepo;

	// validate Student credential
	@Override
	public Object validateLogin(Credential cred) {
		Credential credential = credRepo.findByUserNameAndPassword(cred.getUserName(), cred.getPassword())
				.orElseThrow(() -> new InvalidCredentialException("invalid credential!"));
		// required for later in controller
		cred.setRole(credential.getRole());
		Optional<?> user = null;
		// fetch the student Record using the credential
		if (credential.getRole().equals(com.app.pojos.Role.valueOf("STUDENT"))) {
			user = studentRepo.findByCredential(credential);
		} else {
			// the user is Admin
			user = adminRepo.findByCredential(credential);
		}

		return user.get();
	}
}
