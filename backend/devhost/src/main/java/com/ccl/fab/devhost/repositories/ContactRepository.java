package com.ccl.fab.devhost.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccl.fab.devhost.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
