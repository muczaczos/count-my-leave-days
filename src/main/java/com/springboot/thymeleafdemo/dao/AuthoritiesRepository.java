package com.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.thymeleafdemo.entity.Authorities;

public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {

	// that's it ... no need to write any code LOL!
	
	// add a method to sort by last name (www.luv2code.com/query-methids
	public List<Authorities> findAll();
	
	@Modifying
	@Transactional
    @Query("delete from Authorities a where a.username = ?1")
    public void deleteAuthorities(String username);
	
	public Authorities findByUsername(String username);
}
