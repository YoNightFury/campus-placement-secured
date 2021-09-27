package com.app.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Company;
import com.app.service.IAdminService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
	
	/**
	 * add new company details
	 */
	@Autowired
	IAdminService adminService;
	
	@PostMapping("/add/company")
	public ResponseEntity<?> addCompany(@RequestBody Company company){
		return ResponseEntity.ok(adminService.addCompany(company));
	}
	
	@PutMapping("/update/company/date/{companyName}")
	public ResponseEntity<?> updateUpcomingDateOfCompany(@RequestBody LocalDate date, @PathVariable String companyName){
		return ResponseEntity.ok(adminService.updateCompany(date, companyName));
		
	}

}
