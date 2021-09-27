package com.app.controller;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exception.InvalidDataException;
import com.app.dto.DtoToInsertPlacementDetails;
import com.app.dto.QuestionDto;
import com.app.dto.StudentDto;
import com.app.pojos.Project;
import com.app.security.utils.JwtUtils;
import com.app.service.IStudentService;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")

public class StudentController {

	@Autowired
	private IStudentService studentService;

	@Autowired
	private JwtUtils jwtUtils;

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
	@PostMapping("/add/project")
	public ResponseEntity<?> studentProject(@RequestBody Project project, HttpServletRequest req) {
		String jwt = jwtUtils.extractJwtFromRequest(req);

		return ResponseEntity.ok(studentService.addStudentProject(jwtUtils.getUserIdFromJwt(jwt), project));
	}

	// store student resume into the data base
	// url="http://localhost:8080/student/add/resume/{sid}"
	@PostMapping("/add/resume")
	public ResponseEntity<?> studentResume(@RequestParam MultipartFile studentResume, HttpServletRequest req)
			throws IOException {
		// throw error if the file is not a pdf
		String fileName = studentResume.getOriginalFilename();
		if (!fileName.endsWith(".pdf"))
			throw new InvalidDataException("File is Not a pdf Try Again");

		String jwt = jwtUtils.extractJwtFromRequest(req);
		// create resume class instance and set the property by fetching multipart file
		// and then store the resume instance
		// in the database
		return ResponseEntity.ok(studentService.addStudentResume(jwtUtils.getUserIdFromJwt(jwt), studentResume));
	}

	// store student photo
	// url="http://localhost:8080/student/add/photo/{sid}"
	@PostMapping("/add/photo/")
	public ResponseEntity<?> studentPhoto(@RequestParam MultipartFile studentPhoto, HttpServletRequest req)
			throws IOException {
		// throw error if the file is not a photo 
		String fileName = studentPhoto.getOriginalFilename();
		 Pattern pattern = Pattern.compile(".+\\.(jpe?g)$");
		if (!pattern.matcher(fileName).matches())
			throw new InvalidDataException("File is Not a valid image Try Again with jpg or jpeg");
		// create Photo class instance and set the property by fetching multipart file
		// and then store the Photo instance
		// in the database
		String jwt = jwtUtils.extractJwtFromRequest(req);

		return ResponseEntity.ok(studentService.addStudentPhoto(jwtUtils.getUserIdFromJwt(jwt), studentPhoto));
	}

	// store placement details
	// url="http://localhost:8080/student/add/placement/{sid}"
	@PostMapping("/add/placement")
	public ResponseEntity<?> studentPlacement(@RequestBody DtoToInsertPlacementDetails placementDto,
			HttpServletRequest req) {
		String jwt = jwtUtils.extractJwtFromRequest(req);
		return ResponseEntity.ok(studentService.addStudentPlacement(jwtUtils.getUserIdFromJwt(jwt), placementDto));
	}

	// add question with the to a particualr company
	// url="http://localhost:8080/student/add/question/{sid}"
	@PostMapping("/add/question/") // cid =company id
	public ResponseEntity<?> insertQuestion(@RequestBody QuestionDto question) {

		return ResponseEntity.ok(studentService.addQuestion(question));
	}

	/**
	 * update operatoin on the data
	 * base--------------------------------------------------------------------------------------------------
	 */

	@PutMapping("/update/details")
	public ResponseEntity<?> updateStudetntDetails(@RequestBody StudentDto student, HttpServletRequest req) {
		String jwt = jwtUtils.extractJwtFromRequest(req);
		int id = jwtUtils.getUserIdFromJwt(jwt);
		student.setId(id);
		return ResponseEntity.ok(studentService.updateStudentDetails(student));
	}

	/*-----------------------------------------------------------------------------------------------------------------------------------
	                                                                     *  student data fetching part* */
	/**
	 * shifted to public controller
	 */

}
