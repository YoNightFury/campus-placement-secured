package com.app.service;

import java.util.List;

import com.app.dto.QuestionDto;
import com.app.dto.SuccessMessageDto;
import com.app.pojos.Question;

public interface ICompanyService {

	// fetch all companies
	List<String> fetchAllCompanies();

	List<Question> getAllQuestion(int cid);

	SuccessMessageDto addQuestion(QuestionDto questionDto);

}
