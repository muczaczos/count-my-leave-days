package com.springboot.thymeleafdemo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.thymeleafdemo.entity.Users;

@Component
public interface UsersService {
	
	public List<Users> findAll();
	
	public Users findById(int theId);
	
	public void save(Users theUser);
	
	public void deleteById(int theId);

}
