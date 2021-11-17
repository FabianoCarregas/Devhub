package com.ccl.fab.devhost.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String password;
	
	public CredentialsDTO() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
