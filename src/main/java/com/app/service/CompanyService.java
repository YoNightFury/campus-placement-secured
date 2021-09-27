package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CompanyRepository;
import com.app.pojos.Company;

@Service
@Transactional
public class CompanyService implements ICompanyService {

	@Autowired
	CompanyRepository companyRepo;
	
	@Override
	public List<String> fetchAllCompanies() {
		return companyRepo.findAllName();
	}

}
