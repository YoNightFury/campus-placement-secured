package com.app.service;

import java.io.File;
import java.io.IOException;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception.CourseNotFoundException;
import com.app.custom_exception.InvalidCredentialException;
import com.app.dao.CourseRepository;
import com.app.dao.CredentialRepository;
import com.app.dao.StudentRepository;
import com.app.pojos.Batch;
import com.app.pojos.Course;
import com.app.pojos.CourseName;
import com.app.pojos.Credential;
import com.app.pojos.PlacementDetails;
import com.app.pojos.Project;
import com.app.pojos.Student;
import com.app.pojos.StudentPhoto;
import com.app.pojos.StudentResume;

import static com.app.CustomCommandLineRunner.*;

@Service
@Transactional
public class StudentService implements IStudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private CredentialRepository credRepo;

	// student Registraton
	@Override
	public Student studentRegistration(int year, String batch, String courseName, Student student) {
		Batch batch1 = Batch.valueOf(batch.toUpperCase());
		CourseName courseName1 = CourseName.valueOf(courseName.toUpperCase());
		Optional<Course> courseOptional = courseRepo.findByCourseNameAndBatchAndYear(courseName1, batch1, year);
		Course course = courseOptional.orElseThrow(() -> new CourseNotFoundException("can not the find the course !!"));
		course.getStudents().add(student);
		student.setCourse(course);
		return student;
	}

	// store Credential
	@Override
	public int studentCredential(int sid, Credential credential) {
		System.out.println("in service" + sid + credential.getPassword() + credential.getUserName());
		Student student = studentRepo.findById(sid).get();
		student.setCredential(credential);
		studentRepo.save(student);
		return sid;
	}

	// store project details
	@Override
	public int studentProject(int sid, Project project) {
		Student student = studentRepo.findById(sid).get();
		student.getProjects().add(project);
		studentRepo.save(student);
		return sid;
	}

	// store placement details
	@Override
	public int studentPlacement(int sid, PlacementDetails placementDetails) {
		return 0;
	}

	// store student resume
	@Override
	public int studentResume(int sid, MultipartFile studentResume) throws IOException {
		// create resume class instance and set the property by fetching multipart file
		// and then store the resume instance
		// in the database
		StudentResume stdResume = new StudentResume();
		stdResume.setResumeName(studentResume.getOriginalFilename());
		stdResume.setResumeContent(studentResume.getBytes());

		Student student = studentRepo.findById(sid).get();
		student.setResume(stdResume);
		studentRepo.save(student);
		studentResume.transferTo(new File(RESUME_PATH + "\\" + studentResume.getOriginalFilename()));
		return sid;
	}

	// store student photo
	@Override
	public int studentPhoto(int sid, MultipartFile studentPhoto) throws IOException {
		// create Photo class instance and set the property by fetching multipart file
		// and then store the Photo instance
		// in the database
		StudentPhoto stdPhoto = new StudentPhoto();
		stdPhoto.setPhoto(studentPhoto.getBytes());

		Student student = studentRepo.findById(sid).get();
		student.setPhoto(stdPhoto);
		studentRepo.save(student);
		studentPhoto.transferTo(new File(PHOTO_PATH + "\\" + studentPhoto.getOriginalFilename()));
		return sid;
	}

	// validate Student credential
	@Override
	public Student validateLogin(Credential cred) {
		Credential credential = credRepo.findByUserNameAndPassword(cred.getUserName(), cred.getPassword())
				.orElseThrow(() -> new InvalidCredentialException("invalid credential!"));

		// fetch the student Record using the credential
		Optional<Student> student = studentRepo.findByCredential(credential);
//		student.or

		return student.get();
	}
}
