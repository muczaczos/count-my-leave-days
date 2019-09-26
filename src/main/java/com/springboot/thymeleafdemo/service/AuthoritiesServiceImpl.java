package com.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.thymeleafdemo.dao.AuthoritiesRepository;
import com.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.springboot.thymeleafdemo.entity.Authorities;
import com.springboot.thymeleafdemo.entity.Employee;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService {
	
	private AuthoritiesRepository authoritiesRepository;

	@Autowired
	public AuthoritiesServiceImpl(AuthoritiesRepository theAuthoritiesRepository) {
		authoritiesRepository = theAuthoritiesRepository;
	}
	
	@Override
	public List<Authorities> findAll() {

		return authoritiesRepository.findAll();
	}

	@Override
	public Authorities findById(int theId) {
		
		Optional<Authorities> result = authoritiesRepository.findById(theId);
		
		Authorities theAuthorities = null;
		
		if(result.isPresent()) {
			theAuthorities = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find Authorities id - " + theId);
		}
		
		return theAuthorities;
	}

	@Override
	public void save(Authorities theAuthorities) {
		
		authoritiesRepository.save(theAuthorities);
	}

	@Override
	public void deleteById(int theId) {
		authoritiesRepository.deleteById(theId);

	}

}
