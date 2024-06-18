package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.Credential;
import com.app.pojos.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query("select s from Student s  where s.credential.userName=:userName")
	Optional<Student> findByCredential(@Param("userName") String  userName);

	@Query("select s from Student s join fetch s.resume where s.id=:id")
	Optional<Student> findByIdWithResume(@Param("id") int id);

	@Query("select s from Student s join fetch s.photo where s.id=:id")
	Optional<Student> findByIdWithPhoto(@Param("id") int id);
}
