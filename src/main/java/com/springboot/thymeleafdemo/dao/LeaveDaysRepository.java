package com.springboot.thymeleafdemo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.thymeleafdemo.entity.LeaveDays;

public interface LeaveDaysRepository extends JpaRepository<LeaveDays, Integer>  {
	
	// that's it ... no need to write any code LOL!
	
		// add a method to sort by last name (www.luv2code.com/query-methids
		public List<LeaveDays> findAll();
		
	    public List<LeaveDays> findAllById(int id);
}
