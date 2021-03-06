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
	public Authorities findByUsername(String username) {
		
		return authoritiesRepository.findByUsername(username);
	}

	@Override
	public void save(Authorities theAuthorities) {
		
		authoritiesRepository.save(theAuthorities);
	}

	@Override
	public void deleteAuthorities(String username) {
		authoritiesRepository.deleteAuthorities(username);

	}

}
