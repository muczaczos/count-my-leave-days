package com.springboot.thymeleafdemo.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.thymeleafdemo.entity.Authorities;


@Component
public interface AuthoritiesService {
	
	public List<Authorities> findAll();
	
	public Authorities findByUsername(String username);
	
	public void save(Authorities theAuthorities);
	
	public void deleteAuthorities(String username);

}
