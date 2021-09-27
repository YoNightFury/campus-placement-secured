package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.pojos.Company;
import com.app.service.IAdminService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	
	/**
	 * add new company details
	 */
	@Autowired
	IAdminService adminService;
	
	@PostMapping("/admin/add/company")
	public ResponseEntity<?> addCompany(@RequestBody Company company){
		return ResponseEntity.ok(adminService.addCompany(company));
	}
	

}
