package com.app.service;

import java.time.LocalDate;

import com.app.dto.SuccessMessageDto;
import com.app.pojos.Company;

public interface IAdminService {

	/**
	 * add a new Company
	 */
	SuccessMessageDto addCompany(Company company);
	
	/**
	 * update company upcoming date
	 */
	SuccessMessageDto updateCompany(LocalDate date, String companyName);
	
	
}
