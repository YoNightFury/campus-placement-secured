package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Credential;
import com.app.pojos.Student;

public interface StudentRepository extends  JpaRepository<Student,Integer> {

	@Query("select s from Student s where s.credential=:cred")
//	@Query("select s from Student s join fetch s.photo where s.credential=:cred")
	 Optional<Student> findByCredential(@Param("cred") Credential cred);
}
