package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.DtoToInsertPlacementDetails;
import com.app.dto.StudentDto;
import com.app.dto.SuccessMessageDto;
import com.app.pojos.Project;
import com.app.pojos.Student;

public interface IStudentService {

	// Student studentRegistration(int year, String batch, String courseName,
	// Student student);

	int addStudentResume(int sid, MultipartFile studentResume) throws IOException;

	int addStudentPhoto(int sid, MultipartFile studentPhoto) throws IOException;

	int addStudentProject(int sid, Project project);
	// -------------------------------------------------------------

	public SuccessMessageDto addStudentPlacement(int sid, DtoToInsertPlacementDetails placementDto);

	// ------------------------------------------------------------

	public SuccessMessageDto studentRegister(Student student);

	// update student detail
	SuccessMessageDto updateStudentDetails(StudentDto std);

	Student getStudentUsingId(int id);

}
