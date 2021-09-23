package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Batch;
import com.app.pojos.Course;
import com.app.pojos.CourseName;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	// findByLastnameAndFirstname
	// courseName,batch,year
	Optional<Course> findByCourseNameAndBatchAndYear(CourseName cname, Batch batch, int year);
}
