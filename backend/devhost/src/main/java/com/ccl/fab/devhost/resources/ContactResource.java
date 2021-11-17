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

import com.ccl.fab.devhost.dto.ContactDTO;
import com.ccl.fab.devhost.entities.Contact;
import com.ccl.fab.devhost.services.ContactService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/contacts")
public class ContactResource {
	
	@Autowired
	private ContactService contactService;
	
	@ApiOperation("Get all Contacts")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ContactDTO>> findAll() {
		List<Contact> list= contactService.findAll();
		List<ContactDTO> listDto= list.stream().map(obj -> new ContactDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);	 
	}
	
	@ApiOperation("Get Contact by Id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Contact> findById(@PathVariable Long id){
		Contact contact= contactService.findById(id);
		return ResponseEntity.ok().body(contact);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Create new Contact")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ContactDTO objDto) {
		Contact obj = contactService.fromDTO(objDto);
		obj = contactService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Edit Contact by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ContactDTO objDto, @PathVariable Long id) {
		Contact obj = contactService.fromDTO(objDto);
		obj = contactService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@ApiOperation("Delete Contact by Id")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		contactService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
