package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	Company findByName(String companyName);

	@Query("select c.name from Company c")
	List<String> findAllName();
}
