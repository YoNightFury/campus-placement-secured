package com.app.dao;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.pojos.Company;

@SpringBootTest
public class CompanyDaoTest {

	@Autowired
	private CompanyRepository companyRepo;
	
	@Test
	public void insertCompany() {
		List<Company>  company=Arrays.asList(new Company("TCS","https://www.tcs.com"),new Company("WIPRO", "https://www.wipro.com"),new Company("INFYOSIS","https://www.infosys.com/"));
		companyRepo.saveAll(company);
	}
}
