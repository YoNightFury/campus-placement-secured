package com.app.pojos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Admin extends BaseEntity {

	@Column(name = "admin_name", length = 50)
	private String adminName;
	@Column(name = "email", length = 50)
	private String email;
	@Column(name = "mob_no", length = 15)
	private long mobNo;

	public Admin(String adminName, String email, long mobNo) {
		super();
		this.adminName = adminName;
		this.email = email;
		this.mobNo = mobNo;
	}

	// association
	// credential and admin
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "credential_id")
	private Credential credential;

	// default constructor
	public Admin() {
		System.out.println("Admin.Admin()");
	}



}
