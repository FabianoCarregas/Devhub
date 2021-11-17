package com.ccl.fab.devhost.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.ccl.fab.devhost.entities.Contact;
import com.ccl.fab.devhost.entities.JobExperience;
import com.ccl.fab.devhost.entities.Project;
import com.ccl.fab.devhost.entities.User;
import com.ccl.fab.devhost.enums.Profilee;
import com.ccl.fab.devhost.enums.WorkSituation;
import com.ccl.fab.devhost.repositories.ContactRepository;
import com.ccl.fab.devhost.repositories.JobExperienceRepository;
import com.ccl.fab.devhost.repositories.ProjectRepository;
import com.ccl.fab.devhost.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private JobExperienceRepository jobExperienceRepository;
	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BCryptPasswordEncoder bc;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User(null, "Maria", "Data Scientist", "Degree"," http://foto", WorkSituation.FREELANCER, bc.encode("123"));
		User user2 = new User(null, "João", "Cloud", "Degree", "http://foto2", WorkSituation.EMPLOYED, bc.encode("123"));
		User user3 = new User(null, "Pedro", "Design", "Post-Degree", "http://foto2", WorkSituation.UNEMPLOYED, bc.encode("123"));
		User user4 = new User(null, "Fabs", "Java Dev", "Degree"," http://foto6", WorkSituation.FREELANCER, bc.encode("123"));
		
		user4.addProfilee(Profilee.ADMIN);
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));
		
		Contact contact1= new Contact(null, "maria@Gmail.com", "11-99999999", "maria@github", "maria@linkedin",user1);
		Contact contact2= new Contact(null, "joao@Gmail.com", "11-88889999", "joao@github", "joao@linkedin", user2);	
		Contact contact3= new Contact(null, "pedro@Gmail.com", "11-77779999", "pedro@github", "pedro@linkedin", user3);
		Contact contact4= new Contact(null, "pedro2@Gmail.com", "11-77229999", null, null, user3);
		Contact contact5= new Contact(null, "fabs@Gmail.com", "11-74479999", "fabs@github", "fabs@linkedin", user4);
		
		user1.getContacts().add(contact1);
		user2.getContacts().add(contact2);
		user3.getContacts().addAll(Arrays.asList(contact3, contact4));
		user4.getContacts().add(contact5);

		contactRepository.saveAll(Arrays.asList(contact1, contact2, contact3, contact4, contact5));
	
		Project project1 = new Project(null, "Aplicativo de pressão de combustivel", "www.nasa.com", "github@nasa", user1);
		Project project2 = new Project(null, "Cadastro de Astronautas", "www.nasa2.com", "github@nasa2", user1);
		Project project3 = new Project(null, "Controle de carregamento da bateria", "www.projects.com", "github@project", user2);
		Project project4 = new Project(null, "Super mercado", "www.mercadinho.com", "github@sm", user2);
		Project project5 = new Project(null, "E-commerce", "www.eshop.com", "github@es", user3);
		Project project6 = new Project(null, "Dev-Host", "www.webpage.com", "github@fabs", user4);
		
		projectRepository.saveAll(Arrays.asList(project1, project2, project3, project4, project5, project6));
	
		JobExperience job1 = new JobExperience(null, "Nasa", "DevOps Jr.", 9, user1);
		JobExperience job2 = new JobExperience(null, "Space-X", "Software Engineer", 20, user1);
		JobExperience job3 = new JobExperience(null, "Tesla", "Software Engineer Sr.", 24, user2);
		JobExperience job4 = new JobExperience(null, "LandScape", "Digger", 26, user4);
		
		jobExperienceRepository.saveAll(Arrays.asList(job1, job2, job3, job4));
		
		user1.getJobs().add(job1);
		user1.getJobs().add(job2);
		user2.getJobs().add(job3);
		user4.getJobs().add(job4);
		
		user1.getProjects().add(project1);
		user1.getProjects().add(project2);
		user2.getProjects().add(project3);
		user2.getProjects().add(project4);
		user3.getProjects().add(project5);
		user4.getProjects().add(project6);
	}

}