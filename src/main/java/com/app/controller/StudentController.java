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

import com.app.pojos.PlacementDetails;
import com.app.pojos.Project;
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

	// store project details
	@PostMapping("/project")
	public ResponseEntity<?> studentProject(@PathVariable int sid,@RequestBody Project project) {
		return ResponseEntity.ok(studentService.studentProject(sid, project));
	}

	// store placement details
	@PostMapping("/placement")
	public ResponseEntity<?> studentPlacement(@RequestBody PlacementDetails placementDetails) {
		return null;
	}

	// store student resume
	@PostMapping("/resume")
	public ResponseEntity<?> studentResume(@PathVariable int sid,@RequestParam MultipartFile studentResume) throws IOException {
		// create resume class instance and set the property by fetching multipart file
		// and then store the resume instance
		// in the database
		return ResponseEntity.ok(studentService.studentResume(sid, studentResume));
	}

	// store student photo
	@PostMapping("/photo")
	public ResponseEntity<?> studentPhoto(@PathVariable int sid,@RequestParam MultipartFile studentPhoto) throws IOException {
		// create Photo class instance and set the property by fetching multipart file
		// and then store the Photo instance
		// in the database
		return ResponseEntity.ok(studentService.studentPhoto(sid, studentPhoto));
	}

	

	
	/*-----------------------------------------------------------------------------------------------------------------------------------
	 *  student data fetching part
	 * 
	 * */

	
	 
}
