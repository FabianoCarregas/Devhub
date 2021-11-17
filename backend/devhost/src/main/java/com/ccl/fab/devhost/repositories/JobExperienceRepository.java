package com.ccl.fab.devhost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccl.fab.devhost.entities.JobExperience;
import com.ccl.fab.devhost.entities.User;

@Repository
public interface JobExperienceRepository extends JpaRepository<JobExperience, Long> {

}
