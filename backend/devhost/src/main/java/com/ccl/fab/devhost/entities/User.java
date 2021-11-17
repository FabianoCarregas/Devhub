package com.ccl.fab.devhost.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ccl.fab.devhost.enums.Profilee;
import com.ccl.fab.devhost.enums.WorkSituation;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String area;
	private String graduation;
	private String imageUri;
	private Integer workSituation;
	
	@JsonIgnore
	private String password;
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<JobExperience> jobs= new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Project> projects= new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
	private List<Contact> contacts= new ArrayList<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "Profiles")
	private Set<Integer> profiles = new HashSet<>();
	
	public User() {
		addProfilee(Profilee.CLIENT);
	}

	public User(Long id, String name, String area, String graduation, String photoUrl, WorkSituation workSituation, String password) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.graduation = graduation;
		this.imageUri = photoUrl;
		this.workSituation = (workSituation == null) ? null : workSituation.getCode();
		this.password = password;
		addProfilee(Profilee.CLIENT);
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
	
	public WorkSituation getWorkSituation() {
		return WorkSituation.valueOf(workSituation);
	}
	
	public void setWorkSituation(WorkSituation workSituation) {
		if (workSituation != null) {
		this.workSituation = workSituation.getCode();
	}	
}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public List<JobExperience> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobExperience> jobs) {
		this.jobs = jobs;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Profilee> getProfiles() {
		return profiles.stream().map(x -> Profilee.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfilee(Profilee profilee) {
		profiles.add(profilee.getCode());
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
