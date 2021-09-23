package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "questions")
public class Question extends BaseEntity {

	 // form 7 continution
	
	@Column(length = 300)
	private String question;

	// default const
	public Question() {
		System.out.println("Question.Question()");
	}

	

}
