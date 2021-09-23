package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Credential;
import com.app.pojos.PlacementDetails;
import com.app.pojos.Project;
import com.app.pojos.Student;

public interface IStudentService {

	Student studentRegistration(int year, String batch, String courseName, Student student);

	int studentCredential(int sid,Credential credential);

	int studentPlacement(int sid,PlacementDetails placementDetails);

	int studentResume(int sid,MultipartFile studentResume)throws IOException;

	int studentPhoto(int sid,MultipartFile studentPhoto)throws IOException;

	int studentProject(int sid,Project project);

	
	// all fetching method;
	Student validateLogin(Credential cred);
}
