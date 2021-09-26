package com.app.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Project;
import com.app.pojos.Question;
import com.app.pojos.Student;
import com.app.pojos.StudentResume;
import com.app.security.utils.JwtUtils;
import com.app.service.IStudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/public")
public class PublicController {

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	IStudentService studentService;

	// find all the student based on the year , batch , course
	// create a student dto to send only requred field
	// url="http://localhost:8080/public/get/allStudent"
	@GetMapping("/get/allStudent")
	public List<Student> findAllStudent() {
		return studentService.findAllStudent();
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
		return studentService.getAllProject(sid);
	}

	// download resume from database
	// url="http://localhost:8080/public/download/resume/{sid}"
	// download resume from database
	@GetMapping("/download/resume/{sid}")
	public ResponseEntity<?> downloadResume(@PathVariable int sid) throws IOException {
		StudentResume downloadResume = studentService.downloadResume(sid);
		System.out.println(downloadResume.getResumeName());
		System.out.println("in side download Response controller");
		String encodedString = Base64.getEncoder().encodeToString(downloadResume.getResumeContent());
		byte[] resume = Base64.getDecoder().decode(encodedString);
		return ResponseEntity.status(HttpStatus.FOUND).contentType(MediaType.APPLICATION_PDF).body(resume);

	}

	// find all the placement details of any particular student
	// use dto object to store placementDetails and comany name as one object to
	// send the client
	// url="http://localhost:8080/public/fetch/placementdetails/{sid}"
	@GetMapping("/fetch/placementdetails/{sid}")
	List<?> getAllPlacementDetails(@PathVariable int sid) {
		return studentService.getAllPlacementDetails(sid);
	}

	// download the photo of the a particular student
	@GetMapping("/download/photo/{sid}")
	public ResponseEntity<?> downloadPhoto(@PathVariable int sid) {
		return ResponseEntity.ok(studentService.downloadPhoto(sid));
	}

	// find all the question of a particual company
	// url="http://localhost:8080/public/getall/question/{cid}"
	@GetMapping("/getall/question/{cid}")
	public List<Question> getAllQuestion(@PathVariable int cid) {
		return studentService.getAllQuestion(cid);
	}

}
