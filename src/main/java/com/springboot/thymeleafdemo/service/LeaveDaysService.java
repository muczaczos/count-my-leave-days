package com.springboot.thymeleafdemo.service;

import java.util.List;

import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.entity.LeaveDays;

public interface LeaveDaysService {

	
	public List<LeaveDays> findAll();
	
	public LeaveDays findById(int theId);
	
	public void save(LeaveDays theLeaveDays);
	
	public void deleteById(int theId);
	
    public List<LeaveDays> findAllByEmployee(Employee employee);

	
}
