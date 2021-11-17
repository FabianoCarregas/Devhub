package com.ccl.fab.devhost.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ccl.fab.devhost.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Transactional(readOnly = true)
	User findByName(String name);

}
