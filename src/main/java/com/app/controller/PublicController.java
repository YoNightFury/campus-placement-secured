package com.app.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Project;
import com.app.pojos.Question;
import com.app.pojos.Student;
import com.app.pojos.StudentResume;
import com.app.service.ICompanyService;
import com.app.service.IPublicService;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private IStudentService studentService;

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IPublicService publicService;

	// find all the student based on the year , batch , course
	// create a student dto to send only requred field
	// url="http://localhost:8080/public/get/allStudent"
	@GetMapping("/get/allStudent")
	public List<Student> findAllStudent() {
		return publicService.findAllStudent();
	}

	// implemented
	@GetMapping("/profile/{id}")
	public ResponseEntity<?> showProfile(@PathVariable int id) {
		return ResponseEntity.ok(studentService.getStudentUsingId(id));

	}

	// find all the project of any particular student
	// url="http://localhost:8080/public/fetch/project/{sid}"
	@GetMapping("/fetch/project/{sid}")
	public List<Project> getAllProject(@PathVariable int sid) {
		return publicService.getAllProject(sid);
	}

	// download resume from database
	// url="http://localhost:8080/public/download/resume/{sid}"
	// download resume from database
	@GetMapping("/download/resume/{sid}")
	public ResponseEntity<?> downloadResume(@PathVariable int sid) throws IOException {
		StudentResume downloadResume = publicService.downloadResume(sid);
		System.out.println(downloadResume.getResumeName());
		System.out.println("in side download Response controller");
		String encodedString = Base64.getEncoder().encodeToString(downloadResume.getResumeContent());
		byte[] resume = Base64.getDecoder().decode(encodedString);
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_PDF).body(resume);

	}

	/**
	 * 
	 * @param
	 * @return List of all companies
	 */

	@GetMapping("/fetch/companies")
	public ResponseEntity<?> fetchAllCompanies() {
		return ResponseEntity.ok(companyService.fetchAllCompanies());
	}

	// find all the placement details of any particular student
	// use dto object to store placementDetails and comany name as one object to
	// send the client
	// url="http://localhost:8080/public/fetch/placementdetails/{sid}"
	@GetMapping("/fetch/placementdetails/{sid}")
	List<?> getAllPlacementDetails(@PathVariable int sid) {
		return publicService.getAllPlacementDetails(sid);
	}

	// download the photo of the a particular student
	@GetMapping("/download/photo/{sid}")
	public ResponseEntity<?> downloadPhoto(@PathVariable int sid) {
		return ResponseEntity.ok(publicService.downloadPhoto(sid));
	}

	// find all the question of a particual company
	// url="http://localhost:8080/public/getall/question/{cid}"
	@GetMapping("/getall/question/{cid}")
	public List<Question> getAllQuestion(@PathVariable int cid) {
		return companyService.getAllQuestion(cid);
	}

}
