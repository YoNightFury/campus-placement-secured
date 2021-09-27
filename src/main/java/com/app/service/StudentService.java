package com.app.service;

import static com.app.CustomCommandLineRunner.PHOTO_PATH;
import static com.app.CustomCommandLineRunner.RESUME_PATH;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception.CourseNotFoundException;
import com.app.custom_exception.InvalidCompanyException;
import com.app.custom_exception.StudentNotFound;
import com.app.dao.CompanyRepository;
import com.app.dao.CourseRepository;
import com.app.dao.StudentRepository;
import com.app.dto.DtoToInsertPlacementDetails;
import com.app.dto.StudentDto;
import com.app.dto.SuccessMessageDto;
import com.app.pojos.Company;
import com.app.pojos.Course;
import com.app.pojos.PlacementDetails;
import com.app.pojos.Project;
import com.app.pojos.Round;
import com.app.pojos.SelectionStatus;
import com.app.pojos.Student;
import com.app.pojos.StudentPhoto;
import com.app.pojos.StudentResume;

@Service
@Transactional
public class StudentService implements IStudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private CourseRepository courseRepo;

	@Autowired
	private CompanyRepository companyRepo;

	@Override
	public SuccessMessageDto studentRegister(Student student) {
		Optional<Course> courseOptional = courseRepo.findByCourseNameAndBatchAndYear(
				student.getCourse().getCourseName(), student.getCourse().getBatch(), student.getCourse().getYear());
		Course course = courseOptional.orElseThrow(() -> new CourseNotFoundException("can not the find the course !!"));
		course.getStudents().add(student);
		student.setCourse(course);
		return new SuccessMessageDto("User Registered successfully");
	}

	// store project details
	@Override
	public int addStudentProject(int sid, Project project) {
		Student student = studentRepo.findById(sid).get();
		student.getProjects().add(project);
		studentRepo.save(student);
		return sid;
	}

	// store student resume
	@Override
	public int addStudentResume(int sid, MultipartFile studentResume) throws IOException {
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
	public int addStudentPhoto(int sid, MultipartFile studentPhoto) throws IOException {
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

	@Override
	public SuccessMessageDto addStudentPlacement(int sid, DtoToInsertPlacementDetails placementDetails) {
		System.out.println(placementDetails.getCompanyName());
		// create the placement details pojo
		PlacementDetails transientPlacementDetais = new PlacementDetails();
		transientPlacementDetais.setIsSelected(SelectionStatus.valueOf(placementDetails.getIsSelected().toUpperCase()));
		transientPlacementDetais.setRound(Round.valueOf(placementDetails.getRound().toUpperCase()));
		// if the company is null
		Company company = companyRepo.findByName(placementDetails.getCompanyName().toUpperCase());
		if (company == null)
			throw new InvalidCompanyException("Invalid Company Name, Company Not Found!!");
		transientPlacementDetais.setCompany(company);

		// insert the RECORD
		Student student = studentRepo.findById(sid).get();
		student.getPlacementDetails().add(transientPlacementDetais);
		studentRepo.save(student);

		return new SuccessMessageDto("student placement details is added successfully");
	}

	@Override
	public SuccessMessageDto updateStudentDetails(StudentDto std) {
		Student student = studentRepo.findById(std.getId()).get();
		// use bean utils to set the properties

		BeanUtils.copyProperties(std, student);
		studentRepo.save(student);
		// merge used to merge details or else save updates completely
//		manager.merge(student);
		return new SuccessMessageDto("Student details updated successfully");
	}

	@Override
	public Student getStudentUsingId(int id) {

		return studentRepo.findById(id).orElseThrow(StudentNotFound::getException);
	}

}
