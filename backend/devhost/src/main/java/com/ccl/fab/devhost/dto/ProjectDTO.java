package com.ccl.fab.devhost.dto;

import java.io.Serializable;

import com.ccl.fab.devhost.entities.Project;

public class ProjectDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String objective;
	private String link;
	private String github;
	
	public ProjectDTO() {
	
	}
	public ProjectDTO(Project obj) {
		id = obj.getId();
		objective = obj.getObjective();
		link = obj.getObjective();
		github = obj.getGithub();
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getObjective() {
		return objective;
	}
	
	public void setObjective(String objective) {
		this.objective = objective;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getGithub() {
		return github;
	}
	
	public void setGithub(String github) {
		this.github = github;
	}
	
}
