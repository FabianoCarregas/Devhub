package com.ccl.fab.devhost.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ccl.fab.devhost.entities.Contact;

public class ContactDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotNull(message="Please fill out the e-mail field")
	@Email(message = "invalid e-mail")
	private String email;
	@NotNull(message="Please fill out the phone field ")
	@Size(min = 8, max= 16, message="The size most be between 8 and 16 characters")
	private String phone;
	private String githubProfile;
	private String linkedin;

	public ContactDTO() {
		
	}
	
	public ContactDTO(Contact obj) {
		id = obj.getId();
		email = obj.getEmail();
		phone = obj.getPhone();
		githubProfile = obj.getGithubProfile();
		linkedin = obj.getLinkedin();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setGithubProfile(String github) {
		this.githubProfile = github;
	}

	public String getLinkedin() {
		return linkedin;
	}

	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	
}
