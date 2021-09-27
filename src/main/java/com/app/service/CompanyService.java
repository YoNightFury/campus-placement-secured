package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.InvalidCompanyException;
import com.app.dao.CompanyRepository;
import com.app.dto.QuestionDto;
import com.app.dto.SuccessMessageDto;
import com.app.pojos.Company;
import com.app.pojos.Question;

@Service
@Transactional
public class CompanyService implements ICompanyService {

	@Autowired
	CompanyRepository companyRepo;
	
	@Override
	public List<String> fetchAllCompanies() {
		return companyRepo.findAllName();
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
			if(company==null)
				throw new InvalidCompanyException("Invalid Company Name, Company Not Found!!");
			System.out.println("company " + company);
			company.getQuestions().add(new Question(questionDto.getQuestion()));
			return new SuccessMessageDto("question inserted successfully");
		}
}
