package com.ccl.fab.devhost.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ccl.fab.devhost.dto.UserDTO;
import com.ccl.fab.devhost.dto.UserNewDTO;
import com.ccl.fab.devhost.entities.Contact;
import com.ccl.fab.devhost.entities.JobExperience;
import com.ccl.fab.devhost.entities.Project;
import com.ccl.fab.devhost.entities.User;
import com.ccl.fab.devhost.enums.Profilee;
import com.ccl.fab.devhost.enums.WorkSituation;
import com.ccl.fab.devhost.exceptions.ObjectNotFoundException;
import com.ccl.fab.devhost.repositories.ContactRepository;
import com.ccl.fab.devhost.repositories.JobExperienceRepository;
import com.ccl.fab.devhost.repositories.ProjectRepository;
import com.ccl.fab.devhost.repositories.UserRepository;
import com.ccl.fab.devhost.security.UserSS;
import com.ccl.fab.devhost.services.exceptions.AuthorizationException;
import com.ccl.fab.devhost.services.exceptions.DataIntegrityException;

import ch.qos.logback.core.net.server.Client;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private JobExperienceRepository jobRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private S3Service s3Service;
	
	@Autowired
	private BCryptPasswordEncoder bc;
	
	@Autowired
	private ImageService imageService;
	
	@Value("${img.prefix.client.profile}")
	private String prefix;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}	
	
	public User findById(Long id){		
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"User not found! Id: " + id + ", Tipo: " + User.class.getName()));
	}
	@Transactional
	public User insert(User obj) {
		obj.setId(null);
		obj = userRepository.save(obj);
		contactRepository.saveAll(obj.getContacts());
		jobRepository.saveAll(obj.getJobs());
		projectRepository.saveAll(obj.getProjects());
		return obj;
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return userRepository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			userRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Not allowed to delete, ");
		}
		
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getArea(), objDto.getGraduation(), objDto.getImageUri(), null, null);
	}
	
	public User fromDTO(UserNewDTO objDto) {
		User user = new User(null, objDto.getName(), objDto.getArea(), objDto.getGraduation(), objDto.getImageUri(), WorkSituation.valueOf(objDto.getWorkSituation()), bc.encode(objDto.getPassword()));
		Contact contact = new Contact(null, objDto.getEmail(), objDto.getPhone(), objDto.getGithubProfile(), objDto.getLinkedin(), user);
		Project project = new Project(null, objDto.getObjective(), objDto.getLink(), objDto.getGithub(), user);
		JobExperience job = new JobExperience(null, objDto.getCompany(), objDto.getPosition(), objDto.getServiceTimeMonths(), user);
		user.getContacts().add(contact);
		user.getProjects().add(project);
		user.getJobs().add(job);
		return user;
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setGraduation(obj.getGraduation());
		newObj.setWorkSituation(obj.getWorkSituation());
		}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UsersService.autheticated();
		if (user == null) {
			throw new AuthorizationException("Access denied");
			
		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		String fileName = prefix + user.getId() + ".jpg";
		
		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
		
	}
	
}	
	
	