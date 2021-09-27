package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class StudentResume extends BaseEntity {

	@Column(name = "resume_name", length = 50)
	private String resumeName;
	@Lob
	private byte[] resumeContent;

	// default const
	public StudentResume() {
		System.out.println("StudentResume.StudentResume()");
	}

	public StudentResume(String resumeName, byte[] resumeContent) {
		super();
		this.resumeName = resumeName;
		this.resumeContent = resumeContent;
	}

}
