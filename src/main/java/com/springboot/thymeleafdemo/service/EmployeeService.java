package com.springboot.thymeleafdemo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.thymeleafdemo.entity.Employee;

@Component
public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
}
