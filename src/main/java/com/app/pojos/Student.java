package com.app.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Student extends BaseEntity {

	// basic detials
     @NotBlank(message = "First name is mandatory")
	@Column(name = "first_name", length = 50)
	private String firstName;

     @NotBlank(message = "Last name is mandatory")
	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(length = 20,unique=true)
	 @NotNull(message = "Prn is mandatory")
	private Long prn;

	@Column(name = "dob")
	@NotNull(message = "Date of birth is mandatory")
	@Past(message ="Date of Birth must be from past")
	private LocalDate dob;

	// academic details
	
	@Column(name = "marks_10th", length = 10)
	private double mark10th;
	@Past(message = "10th completeing Date must be from past")
	@Column(name = "passing_year_10th")
	private LocalDate passingYear10th;

	@Column(name = "marks_12th", length = 10)
	private double mark12th;
	@Past(message = "12th completing Date must be from past")
	@Column(name = "passing_year_12th")
	private LocalDate passingYear12th;

	@Column(name = "marks_diploma", length = 10)
	private double markDiploma;
	@Past(message = " Diploma completing Date must be from past")
	@Column(name = "passing_year_diploma")
	private LocalDate passingYearDiploma;

	@Column(name = "marks_grad", length = 10)
	private double markGrad;
	@Past(message = "Graduation completing Date must be from past")
	@Column(name = "passing_year_grad")
	private LocalDate passingYearGrad;

	@Column(name = "marks_post_grad", length = 10)
	private double markPostGrad;
	@Past(message = "Post GraduationDate must be from past")
	@Column(name = "passing_year_post_grad")
	private LocalDate passingYearPostGrad;

	@Column(name ="marks_ccee", length = 10 )
	@NotNull(message = "CCEE mark is completly")
	@Positive(message = "CCEE mark positive number is allowed")
	private double markCCEE;
	// contact details

	@Column(length = 50,unique=true)
	@Email(message = "Email must in email format")
	private String email;
	@Column(name = "mob_no", length = 13,unique=true)
	private long mobNo;
	@Column(name = "address", length = 200)
	private String address;
	@Column(name = "git_link", length = 100,unique=true)
	private String gitLink;
	@Column(name = "linkedin_link", length = 100,unique=true)
	private String linkedIn;

	// default const
	public Student() {
		System.out.println("Student.Student()");
	}

	// association

	// student and course and table
	@ManyToOne
	@JoinColumn(name = "course_id")
	@JsonIgnoreProperties("students")
	private Course course;

	// student and credential
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "credential_id")
	//@JsonIgnore
	private Credential credential;
       
	@JsonIgnore //ignore this property during serilization
	public Credential getCredential() {
		return credential;
	}
	
	@JsonProperty //DO NOT ignore this property during de-ser
	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	// student and project
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "student_id")
	@JsonIgnore
	private List<Project> projects = new ArrayList<>();
	
	//student and placement
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "student_id")
	@JsonIgnore
	private List<PlacementDetails> placementDetails = new ArrayList<>();


	// student and resume
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "resume_id")
	@JsonIgnore
	private StudentResume resume;

	// student and photo
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "photo_id")
	@JsonIgnore
	private StudentPhoto photo;

}
