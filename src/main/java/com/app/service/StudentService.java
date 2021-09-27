package com.app.service;

import static com.app.CustomCommandLineRunner.PHOTO_PATH;
import static com.app.CustomCommandLineRunner.RESUME_PATH;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception.CourseNotFoundException;
import com.app.custom_exception.InvalidCompanyException;
import com.app.custom_exception.InvalidCredentialException;
import com.app.custom_exception.PhotoNotFoundException;
import com.app.custom_exception.ResumeNotFoundException;
import com.app.custom_exception.StudentNotFound;
import com.app.dao.AdminRepository;
import com.app.dao.CompanyRepository;
import com.app.dao.CourseRepository;
import com.app.dao.CredentialRepository;
import com.app.dao.StudentRepository;
import com.app.dto.DtoToInsertPlacementDetails;
import com.app.dto.QuestionDto;
import com.app.dto.SendPlacementDetailsDto;
import com.app.dto.StudentDto;
import com.app.dto.SuccessMessageDto;
import com.app.pojos.Company;
import com.app.pojos.Course;
import com.app.pojos.Credential;
import com.app.pojos.PlacementDetails;
import com.app.pojos.Project;
import com.app.pojos.Question;
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
	private CredentialRepository credRepo;

	@Autowired
	private CompanyRepository companyRepo;

	@Autowired
	private AdminRepository adminRepo;

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

	// validate Student credential
	@Override
	public Object validateLogin(Credential cred) {
		Credential credential = credRepo.findByUserNameAndPassword(cred.getUserName(), cred.getPassword())
				.orElseThrow(() -> new InvalidCredentialException("invalid credential!"));
		// required for later in controller
		cred.setRole(credential.getRole());
		Optional<?> user = null;
		// fetch the student Record using the credential
		if (credential.getRole().equals(com.app.pojos.Role.valueOf("STUDENT"))) {
			user = studentRepo.findByCredential(credential);
		} else {
			// the user is Admin
			user = adminRepo.findByCredential(credential);
		}

		return user.get();
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

	// get all project
	@Override
	public List<Project> getAllProject(int sid) {
		Student student = studentRepo.findById(sid).get();
		System.out.println(student.getDob());
		return student.getProjects();
	}

	// student resume download
	@Override
	public StudentResume downloadResume(int sid) {

		return studentRepo.findByIdWithResume(sid).orElseThrow(ResumeNotFoundException::getException).getResume();
	}

	// download the photo
	@Override
	public StudentPhoto downloadPhoto(int sid) {
		return studentRepo.findByIdWithPhoto(sid).orElseThrow(PhotoNotFoundException::getException).getPhoto();
	}

	// get all the placement details of a particular student
	@Override
	public List<SendPlacementDetailsDto> getAllPlacementDetails(int sid) {
		// find the student persistent pojo
		Student student = studentRepo.findById(sid).get();
		// find the list of his placement details
		List<PlacementDetails> placementDetails = student.getPlacementDetails();
		// create the list of SendPlacementDeailsDto
		List<SendPlacementDetailsDto> placementDetailsOfStudent = new ArrayList<SendPlacementDetailsDto>();
		placementDetails.forEach(e -> {
			// create and populate the plaementDetailsDto
			SendPlacementDetailsDto pd = new SendPlacementDetailsDto();
			pd.setIsSelected(e.getIsSelected().toString());
			pd.setRound(e.getRound().toString());
			pd.setCid(e.getCompany().getId().toString());
			pd.setCompanyName(e.getCompany().getName());

			// add that in the list of placement details
			placementDetailsOfStudent.add(pd);
		});
		// return that populated list
		return placementDetailsOfStudent;
	}

	// get all the question based on company
	@Override
	public List<Question> getAllQuestion(int cid) {
		return companyRepo.findById(cid).get().getQuestions();
	}

	// add the qustion to any company
	@Override
	public SuccessMessageDto addQuestion(QuestionDto questionDto) {
		System.out.println("company " + questionDto.getCompanyName() + " que" + questionDto.getQuestion());
		Company company = companyRepo.findByName(questionDto.getCompanyName().toUpperCase());
		System.out.println("company " + company);
		company.getQuestions().add(new Question(questionDto.getQuestion()));
		return new SuccessMessageDto("question iserted successfully");
	}

	// return all the list of student
	@Override
	public List<Student> findAllStudent() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
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
