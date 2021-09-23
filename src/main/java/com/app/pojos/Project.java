package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Project extends BaseEntity {
	
	// form 6

	@Column(name = "project_name", length = 100)
	private String projectName;
	@Column(name = "project_description", length = 500)
	private String projectDescription;

	@Column(name = "project_git_link", length = 100)
	private String projectGitLink;

	public Project(String projectName, String projectDescription, String projectGitLink) {
		super();
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.projectGitLink = projectGitLink;
	}

	// defalut const
	public Project() {
		System.out.println("Project.Project()");
	}

	
}
