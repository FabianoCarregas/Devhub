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

import com.ccl.fab.devhost.dto.JobExperienceDTO;
import com.ccl.fab.devhost.entities.JobExperience;
import com.ccl.fab.devhost.services.JobExperienceService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/jobexperiences")
public class JobExperienceResource {
	
	@Autowired
	private JobExperienceService jobExperienceService;
	
	@ApiOperation("Get all JobExperiences")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<JobExperience>> findAll() {
		List<JobExperience> list= jobExperienceService.findAll();
		return ResponseEntity.ok().body(list); 
	}
	
	@ApiOperation("Get JobExperience by Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<JobExperience> findById(@PathVariable Long id){
		JobExperience jobs= jobExperienceService.findById(id);
		return ResponseEntity.ok().body(jobs);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Create new JobExperience")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody JobExperienceDTO objDto) {
		JobExperience obj = jobExperienceService.fromDTO(objDto);
		obj = jobExperienceService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Edit JobExperience by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody JobExperienceDTO objDto, @PathVariable Long id) {
		JobExperience obj = jobExperienceService.fromDTO(objDto);
		obj = jobExperienceService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Delete JobExperience by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		jobExperienceService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
