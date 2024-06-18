package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.custom_exception.InvalidCredentialException;
import com.app.dao.AdminRepository;
import com.app.dao.CredentialRepository;
import com.app.dao.StudentRepository;
import com.app.pojos.Credential;

@Service
@Transactional
public class ValidationService implements IValidationService {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private AdminRepository adminRepo;

	@Autowired
	private CredentialRepository credRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	// validate Student credential
	@Override
	public Object validateLogin(Credential cred) {
		Credential credential = credRepo.findByUserName(cred.getUserName())
				.orElseThrow(() -> new InvalidCredentialException("invalid username!"));
		// required for later in controller
		if(!passwordEncoder.matches(cred.getPassword(), credential.getPassword()))
			throw new InvalidCredentialException("Invalid password!!");
		cred.setRole(credential.getRole());
		Optional<?> user = null;
		// fetch the student Record using the credential
		if (credential.getRole().equals(com.app.pojos.Role.valueOf("STUDENT"))) {
			user = studentRepo.findByCredential(credential.getUserName());
		} else {
			// the user is Admin
			user = adminRepo.findByCredentialUserName(credential.getUserName());
		}

		return user.get();
	}
}
