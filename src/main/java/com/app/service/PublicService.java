package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.PhotoNotFoundException;
import com.app.custom_exception.ResumeNotFoundException;
import com.app.dao.StudentRepository;
import com.app.dto.SendPlacementDetailsDto;
import com.app.pojos.PlacementDetails;
import com.app.pojos.Project;
import com.app.pojos.Student;
import com.app.pojos.StudentPhoto;
import com.app.pojos.StudentResume;

@Service
@Transactional
public class PublicService implements IPublicService {

	@Autowired
	private StudentRepository studentRepo;

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

	// return all the list of student
	@Override
	public List<Student> findAllStudent() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
	}

}
