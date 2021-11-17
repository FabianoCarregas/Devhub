package com.ccl.fab.devhost.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ccl.fab.devhost.dto.ContactDTO;
import com.ccl.fab.devhost.entities.Contact;
import com.ccl.fab.devhost.exceptions.ObjectNotFoundException;
import com.ccl.fab.devhost.repositories.ContactRepository;
import com.ccl.fab.devhost.services.exceptions.DataIntegrityException;

@Service
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	public List<Contact> findAll() {
		return contactRepository.findAll();
	}	
	
	public Contact findById(Long id){
		Optional<Contact> obj = contactRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Contact not found! Id: " + id + ", Tipo: " + Contact.class.getName()));
	}
	
	public Contact insert(Contact obj) {
		obj.setId(null);
		return contactRepository.save(obj);
	}
	
	public Contact update(Contact obj) {
		Contact newObj = findById(obj.getId());
		updateData(newObj, obj);
		return contactRepository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			contactRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("the option Delete is not allowed");
		}
		
	}

	public Contact fromDTO(ContactDTO objDto) {
		return new Contact(objDto.getId(), objDto.getEmail(), objDto.getPhone(), objDto.getGithubProfile(), objDto.getLinkedin(), null);
	}
	
	private void updateData(Contact newObj, Contact obj) {
		newObj.setEmail(obj.getEmail());
		newObj.setPhone(obj.getPhone());
		newObj.setGithubProfile(obj.getGithubProfile());
		newObj.setLinkedin(obj.getLinkedin());
	}	
	
}
