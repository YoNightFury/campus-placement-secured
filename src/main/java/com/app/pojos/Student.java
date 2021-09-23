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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Student extends BaseEntity {

	// basic detials

	@Column(name = "first_name", length = 50)
	private String firstName;

	@Column(name = "last_name", length = 50)
	private String lastName;

	@Column(length = 20,unique=true)
	private Long prn;

	@Column(name = "dob")
	private LocalDate dob;

	// academic details

	@Column(name = "marks_10th", length = 10)
	private double mark10th;
	@Column(name = "passing_year_10th")
	private LocalDate passingYear10th;

	@Column(name = "marks_12th", length = 10)
	private double mark12th;
	@Column(name = "passing_year_12th")
	private LocalDate passingYear12th;

	@Column(name = "marks_diploma", length = 10)
	private double markDiploma;
	@Column(name = "passing_year_diploma")
	private LocalDate passingYearDiploma;

	@Column(name = "marks_grad", length = 10)
	private double markGrad;
	@Column(name = "passing_year_grad")
	private LocalDate passingYearGrad;

	@Column(name = "marks_post_grad", length = 10)
	private double markPostGrad;
	@Column(name = "passing_year_post_grad")
	private LocalDate passingYearPostGrad;

	@Column(name ="marks_ccee", length = 10 )
	private double markCCEE;
	// contact details

	@Column(length = 50,unique=true)
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
	@ManyToOne()
	@JoinColumn(name = "course_id")
	@JsonIgnoreProperties("students")
	private Course course;

	// student and credential
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "credential_id")
	@JsonIgnore
	private Credential credential;

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
	private StudentPhoto photo;

}
