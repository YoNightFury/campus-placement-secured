package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class StudentPhoto extends BaseEntity {

	@Lob
	private byte[] photo;

	// default const
	public StudentPhoto() {
		System.out.println("StudentResume.StudentResume()");
	}

	// one param cont
	public StudentPhoto(byte[] photo) {
		super();
		this.photo = photo;
	}

}