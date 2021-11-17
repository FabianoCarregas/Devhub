package com.ccl.fab.devhost.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ccl.fab.devhost.dto.ProjectDTO;
import com.ccl.fab.devhost.entities.Project;
import com.ccl.fab.devhost.services.ProjectService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/projects")
public class ProjectResource {
	
	@Autowired
	private ProjectService projectService;
	
	@ApiOperation("Get all Projects")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProjectDTO>> findAll() {
		List<Project> list= projectService.findAll();
		List<ProjectDTO> listDto= list.stream().map(obj -> new ProjectDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);	 
	}
	
	@ApiOperation("Get Project by Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Project> findById(@PathVariable Long id){
		Project projects= projectService.findById(id);
		return ResponseEntity.ok().body(projects);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Create new Project")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProjectDTO objDto) {
		Project obj = projectService.fromDTO(objDto);
		obj = projectService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Edit Project by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ProjectDTO objDto, @PathVariable Long id) {
		Project obj = projectService.fromDTO(objDto);
		obj = projectService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Delete Project by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		projectService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
