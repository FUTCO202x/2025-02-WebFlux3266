package com.security.jwt.spring_security_jwt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.security.jwt.spring_security_jwt.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
	
}
