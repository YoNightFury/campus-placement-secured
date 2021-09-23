package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "login_credentials")
@Setter
@Getter
public class Credential extends BaseEntity {

	// form 1
	
	@Column(name = "user_name", length = 20, unique = true)
	private String userName;

	@Column(name = "password", length = 20)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(length = 15)
	private Role role = Role.STUDENT;

	// 0 param constructor
	public Credential() {
		System.out.println("Credential.Credential()");
	}

	// for unit testing purpose
	public Credential(String userName, String password, Role role) {
		super();
		System.out.println("Credential.Credential()");
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

}
