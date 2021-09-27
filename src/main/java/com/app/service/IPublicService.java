package com.app.service;

import java.util.List;

import com.app.dto.SendPlacementDetailsDto;
import com.app.pojos.Project;
import com.app.pojos.Student;
import com.app.pojos.StudentPhoto;
import com.app.pojos.StudentResume;

public interface IPublicService {

	List<Project> getAllProject(int sid);

	StudentResume downloadResume(int sid);

	StudentPhoto downloadPhoto(int sid);

	List<SendPlacementDetailsDto> getAllPlacementDetails(int sid);

	List<Student> findAllStudent();

}
