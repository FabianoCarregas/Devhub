package com.ccl.fab.devhost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ccl.fab.devhost.dto.JobExperienceDTO;
import com.ccl.fab.devhost.entities.JobExperience;
import com.ccl.fab.devhost.exceptions.ObjectNotFoundException;
import com.ccl.fab.devhost.repositories.JobExperienceRepository;
import com.ccl.fab.devhost.services.exceptions.DataIntegrityException;

@Service
public class JobExperienceService {
	
	@Autowired
	private JobExperienceRepository jobExperienceRepository ;
	
	public List<JobExperience> findAll() {
		return jobExperienceRepository.findAll();
	}	
	
	public JobExperience findById(Long id){
		Optional<JobExperience> obj = jobExperienceRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"JobXP not found! Id: " + id + ", Tipo: " + JobExperience.class.getName()));
	}
	
	public JobExperience insert(JobExperience obj) {
		obj.setId(null);
		return jobExperienceRepository.save(obj);
	}
	
	public JobExperience update(JobExperience obj) {
		JobExperience newObj = findById(obj.getId());
		updateData(newObj, obj);
		return jobExperienceRepository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			jobExperienceRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("the option Delete is not allowed");
		}
		
	}

	public JobExperience fromDTO(JobExperienceDTO objDto) {
		return new JobExperience(objDto.getId(), objDto.getCompany(), objDto.getPosition(), objDto.getServiceTimeMonths(), null);
	}
	
	private void updateData(JobExperience newObj, JobExperience obj) {
		newObj.setCompany(obj.getCompany());
		newObj.setPosition(obj.getPosition());
		newObj.setServiceTimeMonths(obj.getServiceTimeMonths());
	}	
	
}
	
	