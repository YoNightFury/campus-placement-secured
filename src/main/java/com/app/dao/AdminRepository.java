package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Admin;
import com.app.pojos.Credential;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
     
  Optional<Admin> findByCredential(Credential cred);
}
