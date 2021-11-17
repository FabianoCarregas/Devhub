package com.ccl.fab.devhost.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ccl.fab.devhost.entities.User;

public class UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotNull(message = "This content can not be null")
	@Size(min = 8, max = 120, message = "The size most be between 8 and 90 characters")
	private String name;
	private String area;
	private String graduation;
	private String imageUri;
	
	public UserDTO() {
	}

	public UserDTO(User obj) {
		id = obj.getId();
		name = obj.getName();
		area = obj.getArea();
		graduation = obj.getGraduation();
		imageUri = obj.getImageUri();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
