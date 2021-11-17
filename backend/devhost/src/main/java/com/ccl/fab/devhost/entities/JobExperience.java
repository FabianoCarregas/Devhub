package com.ccl.fab.devhost.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_job")
public class JobExperience implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String company;
	private String position;
	private Integer ServiceTimeMonths;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
		
	public JobExperience() {
	
	}

	public JobExperience(Long id, String company, String position, Integer serviceTimeMonths, User user) {
		super();
		this.id = id;
		this.company = company;
		this.position = position;
		this.ServiceTimeMonths = serviceTimeMonths;
		this.user = user;
		
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobExperience other = (JobExperience) obj;
		return Objects.equals(id, other.id);
	}

}
