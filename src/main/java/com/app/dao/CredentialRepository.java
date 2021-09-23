package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer>{
	// fetching the credential id using userName and Password
    Optional<Credential>  findByUserNameAndPassword(String userName,String password);

	Optional<Credential> findByUserName(String username);
}
