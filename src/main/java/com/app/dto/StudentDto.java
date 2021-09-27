package com.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

	private Integer id;
	private String firstName;

	private String lastName;

	private LocalDate dob;

	private double mark10th;
	private LocalDate passingYear10th;

	private double mark12th;
	private LocalDate passingYear12th;

	private double markDiploma;
	private LocalDate passingYearDiploma;

	private double markGrad;
	private LocalDate passingYearGrad;

	private double markPostGrad;
	private LocalDate passingYearPostGrad;

	private double markCCEE;

	@Email(message = "Email must in email format")
	private String email;

	private long mobNo;
	private String address;
	private String gitLink;
	private String linkedIn;

}
