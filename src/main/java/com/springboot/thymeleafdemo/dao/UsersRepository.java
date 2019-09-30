package com.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.thymeleafdemo.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	// that's it ... no need to write any code LOL!
	
	// add a method to sort by last name (www.luv2code.com/query-methids
	public List<Users> findAll();
	
	@Query("FROM Author WHERE firstName = ?1")
    public Users findByUsername(String username);
}
