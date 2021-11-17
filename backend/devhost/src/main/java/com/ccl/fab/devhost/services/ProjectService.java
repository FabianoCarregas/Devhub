package com.ccl.fab.devhost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ccl.fab.devhost.dto.ProjectDTO;
import com.ccl.fab.devhost.entities.Project;
import com.ccl.fab.devhost.exceptions.ObjectNotFoundException;
import com.ccl.fab.devhost.repositories.ProjectRepository;
import com.ccl.fab.devhost.services.exceptions.DataIntegrityException;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	public List<Project> findAll() {
		return projectRepository.findAll();
	}	
	
	public Project findById(Long id){
		Optional<Project> obj = projectRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Project not found! Id: " + id + ", Tipo: " + Project.class.getName()));
	}
	
	public Project insert(Project obj) {
		obj.setId(null);
		return projectRepository.save(obj);
	}
	
	public Project update(Project obj) {
		Project newObj = findById(obj.getId());
		updateData(newObj, obj);
		return projectRepository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			projectRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("the option Delete is not allowed");
		}
		
	}

	public Project fromDTO(ProjectDTO objDto) {
		return new Project(objDto.getId(), objDto.getObjective(), objDto.getLink(), objDto.getGithub(), null);	}
	
	private void updateData(Project newObj, Project obj) {
		newObj.setObjective(obj.getObjective());
		newObj.setLink(obj.getLink());
		newObj.setGithub(obj.getGithub());
	}	
	
}
	