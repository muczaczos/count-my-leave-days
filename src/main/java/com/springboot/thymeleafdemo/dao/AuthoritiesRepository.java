package com.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.thymeleafdemo.entity.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

	// that's it ... no need to write any code LOL!
	
	// add a method to sort by last name (www.luv2code.com/query-methids
	public List<Authorities> findAll();
}
