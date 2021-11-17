package com.ccl.fab.devhost.dto;

import java.io.Serializable;

import com.ccl.fab.devhost.entities.JobExperience;

public class JobExperienceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String company;
	private String position;
	private Integer ServiceTimeMonths;
	
	public JobExperienceDTO() {
	}
	
	public JobExperienceDTO(JobExperience obj) {
		id = obj.getId();
		company = obj.getCompany();
		position = obj.getPosition();
		ServiceTimeMonths = obj.getServiceTimeMonths();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getServiceTimeMonths() {
		return ServiceTimeMonths;
	}

	public void setServiceTimeMonths(Integer serviceTimeMonths) {
		ServiceTimeMonths = serviceTimeMonths;
	}
	
}
