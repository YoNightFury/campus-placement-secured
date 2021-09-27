package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.InvalidCompanyException;
import com.app.dao.CompanyRepository;
import com.app.dto.SuccessMessageDto;
import com.app.pojos.Company;

@Service
@Transactional
public class AdminService implements IAdminService {

	@Autowired
	CompanyRepository companyRepo;
	
	@Override
	public SuccessMessageDto addCompany(Company company) {
		company.setName(company.getName().toUpperCase());
		try {
		companyRepo.save(company);
		}catch (Exception e) {
			throw new InvalidCompanyException("Company already exists!!");
		}
		
		return new SuccessMessageDto("Added Company Successfully");
	}

}
