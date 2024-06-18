package com.app;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.dao.CourseRepository;
import com.app.pojos.Batch;
import com.app.pojos.Course;
import com.app.pojos.CourseName;

@Component
public class CustomCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseRepository courseRepo;

	public static final String STORAGE_PATH = Paths.get(System.getProperty("user.home"), ".temp_project").toString();
	public static final String RESUME_PATH = Paths.get(STORAGE_PATH, "resume").toString();
	public static final String PHOTO_PATH = Paths.get(STORAGE_PATH, "photo").toString();

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Creating folder...");
		System.out.println(STORAGE_PATH);
		// create the folder if does not exists
		File resume_folder = new File(RESUME_PATH);
		File photo_folder = new File(PHOTO_PATH);
		resume_folder.mkdirs();
		photo_folder.mkdirs();

		List<Course> course = new ArrayList<>();
		for (Batch b : Batch.values()) {
			for (CourseName cn : CourseName.values()) {
				for (int year = 2016, i = 0; i < 8; i++, year++) {
					var crs1 = new Course(cn, b, year);
					var crs = courseRepo.findByCourseNameAndBatchAndYear(cn, b, year);
					course.add(crs.orElseGet(() -> courseRepo.save(crs1)));
				}
			}
		}

//		if (!Files.notExists(Paths.get(STORAGE_PATH)))
//			resume_folder.mkdir();
//		

	}

}
