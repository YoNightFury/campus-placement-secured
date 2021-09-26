package com.app.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.DtoToInsertPlacementDetails;
import com.app.pojos.Project;
import com.app.pojos.Question;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")

public class StudentController {

	@Autowired
	private IStudentService studentService;

	// default const
	public StudentController() {
		System.out.println("StudentController.StudentController()");
	}

	/*-----------------------------------------------------------------------------------------------------------------------------------
	 *  student data entry part
	 * 
	 * */

	// store project details
//url="http://localhost:8080/student/add/project/{sid}"
	@PostMapping("/add/project/{sid}")
	public ResponseEntity<?> studentProject(@PathVariable int sid, @RequestBody Project project) {
		return ResponseEntity.ok(studentService.addStudentProject(sid, project));
	}

	// store student resume into the data base
	// url="http://localhost:8080/student/add/resume/{sid}"
	@PostMapping("/add/resume/{sid}")
	public ResponseEntity<?> studentResume(@PathVariable int sid, @RequestParam MultipartFile studentResume)
			throws IOException {
		// create resume class instance and set the property by fetching multipart file
		// and then store the resume instance
		// in the database
		return ResponseEntity.ok(studentService.addStudentResume(sid, studentResume));
	}

	// store student photo
	// url="http://localhost:8080/student/add/photo/{sid}"
	@PostMapping("/add/photo/{sid}")
	public ResponseEntity<?> studentPhoto(@PathVariable int sid, @RequestParam MultipartFile studentPhoto)
			throws IOException {
		// create Photo class instance and set the property by fetching multipart file
		// and then store the Photo instance
		// in the database
		return ResponseEntity.ok(studentService.addStudentPhoto(sid, studentPhoto));
	}

	// store placement details
	// url="http://localhost:8080/student/add/placement/{sid}"
	@PostMapping("/add/placement/{sid}")
	public ResponseEntity<?> studentPlacement(@PathVariable int sid,
			@RequestBody DtoToInsertPlacementDetails placementDto) {
		return ResponseEntity.ok(studentService.addStudentPlacement(sid, placementDto));
	}

	// add question with the to a particualr company
	// url="http://localhost:8080/student/add/question/{sid}"
	@PostMapping("/add/question/{cid}") // cid =company id
	public ResponseEntity<?> insertQuestion(@PathVariable int cid, @RequestBody Question quetion) {
		return ResponseEntity.ok(studentService.addQuestion(cid, quetion));
	}
	
	/*-----------------------------------------------------------------------------------------------------------------------------------
	                                                                     *  student data fetching part* */
	/**
	 * shifted to public controller
	 */

}
