package com.ccl.fab.devhost.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//user
	@NotNull(message = "Name cannot be null")
	@Size(min = 8, max = 120, message = "The size most be between 8 and 90 characters")
	private String name;
	private String area;
	private String graduation;
	private String imageUri;
	private Integer workSituation;
	
	@NotNull(message = "cannot be empty")
	private String password;
	
	//contact
	@NotNull(message="Please fill out the e-mail field")
	@Email(message = "invalid e-mail")
	private String email;
	@NotNull(message="Please fill out the phone field ")
	@Size(min = 8, max= 16, message="The size most be between 8 and 16 characters")
	private String phone;
	private String githubProfile;
	@NotNull(message = "Name cannot be null")
	private String linkedin;
	
	//projec
	private String objective;
	private String link;
	private String github;
	
	//job
	private String company;
	private String position;
	private Integer ServiceTimeMonths;
	
	public UserNewDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGraduation() {
		return graduation;
	}

	public void setGraduation(String graduation) {
		this.graduation = graduation;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public Integer getWorkSituation() {
		return workSituation;
	}

	public void setWorkSituation(Integer workSituation) {
		this.workSituation = workSituation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGithubProfile() {
		return githubProfile;
	}

	public void setGithubProfile(String githubProfile) {
		this.githubProfile = githubProfile;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
