package com.app.dao;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.pojos.Admin;
import com.app.pojos.Credential;
import com.app.pojos.Role;

@SuppressWarnings("unused")
@SpringBootTest
public class AdminDaoTest {
	@Autowired
	private AdminRepository adminRepo;

	@Test
	public void insertAdmin() {
		List<Admin> admin=Arrays.asList(new Admin("ashish kumar yadav", "ashish.act.2021@gmail.com", 8181885221L),
				new Admin("rishav singh", "rishav89@gmail.com", 9089765370L),
				new Admin("abhilash maniyar", "abhi3212@gmail.com", 7897867895L),
				new Admin("nek ranjan", "nek695@gmail.com", 9807654379L),
				new Admin("chirag chaurasiya", "chirag657@gmail.com", 9084258649L));
		
		//adminRepo.saveAll(admin);
		
		
	}	
	@Test
	public void insertCredential() {
		Admin byId = adminRepo.findById(1).get();
		byId.getAdminName();
		byId.setCredential(new Credential("ashishact2021",new BCryptPasswordEncoder(10).encode( "ashish123@"),Role.ADMIN));
		adminRepo.save(byId);
		
		Admin byId2 = adminRepo.findById(2).get();
		byId2.getAdminName();
		byId2.setCredential(new Credential("rishavact2021",new BCryptPasswordEncoder(10).encode("rishav123@"),Role.ADMIN));
		adminRepo.save(byId2);
		
		Admin byId3 = adminRepo.findById(3).get();
		byId3.getAdminName();
		byId3.setCredential(new Credential("abhilashact2021",new BCryptPasswordEncoder(10).encode("abhilash123@"),Role.ADMIN));
		adminRepo.save(byId3);
		
		Admin byId4 = adminRepo.findById(4).get();
		byId4.getAdminName();
		byId4.setCredential(new Credential("nekRanjanact2021",new BCryptPasswordEncoder(10).encode("nekranjan123@"),Role.ADMIN));
		adminRepo.save(byId4);
		
		Admin byId5 = adminRepo.findById(5).get();
		byId5.getAdminName();
		byId5.setCredential(new Credential("chiragact2021",new BCryptPasswordEncoder(10).encode("chirag123@"),Role.ADMIN));
		adminRepo.save(byId5);
	}
}
