package com.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.thymeleafdemo.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	// that's it ... no need to write any code LOL!
	
	// add a method to find all(www.luv2code.com/query-methids
	public List<Users> findAll();
	
	// add a method to sort by username (www.luv2code.com/query-methids
    public List<Users> findByUsername(String username);
    
    /* Modifying query if you needed
    @Modifying
    @Query("update Users u set u.password = ?1, u.enabled = ?2 where u.username = ?3")
    public int updateUsers(String password, int enabled, String username);
    */
    
}
