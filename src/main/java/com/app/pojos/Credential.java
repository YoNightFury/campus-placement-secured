package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "login_credentials")
@Setter
@Getter
public class Credential extends BaseEntity {

	// form 1
	// applying validation
	@NotBlank(message = "UserName is mandatory")
	@Size(min = 8, max = 20, message = "UserName length must be between 8 and 20")
	@Column(name = "user_name", length = 20, unique = true)
	private String userName;

	@NotBlank(message = "Password is mandatory")
	@Size(min = 8, max = 20, message = "Password length must be between 8 and 20")
	@Pattern(regexp = "((?=.*\\d)(?=.*[A-Z])(?=.*[#@$*]).{5,20})", message = "Password must contain one special character and one capital letter  and one number")
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
