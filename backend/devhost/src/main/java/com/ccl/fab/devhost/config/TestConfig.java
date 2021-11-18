package com.ccl.fab.devhost.config;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
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
import com.ccl.fab.devhost.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();
		return true;
	}
	

}