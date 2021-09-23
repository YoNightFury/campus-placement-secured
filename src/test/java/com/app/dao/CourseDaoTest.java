package com.app.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.pojos.Batch;
import com.app.pojos.Course;
import com.app.pojos.CourseName;

@SuppressWarnings("unused")
@SpringBootTest
public class CourseDaoTest {
	
	@Autowired
	private CourseRepository courseRepo;
	
	@Test
	public void insertCourse() {
//		List<Course> course=Arrays.asList(new Course(DAC,JAN, 2022),new Course(DBDA,JAN,2022),new Course(DESD,JAN,2022),
//				new Course(DIOT,JAN,2022),new Course(DASSD,JAN,2022),new Course(DAI,JAN,2022),new Course(DGI,JAN,2022),
//				new Course(DITISS,JAN,2022)
//				);
		
		List <Course> course = new ArrayList<>();
		for(Batch b : Batch.values()) {
			for(CourseName cn : CourseName.values()) {
				for(int year=2016,i=0;i<4;i++,year++)
					course.add(new Course(cn,b,year));
			}
		}
		
		
//		List<Course> list = courseRepo.saveAll(course);
//		assertEquals(list.size(),course.size());
		
	}
}
