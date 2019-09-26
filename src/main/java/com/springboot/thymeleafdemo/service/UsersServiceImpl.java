package com.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.thymeleafdemo.dao.UsersRepository;
import com.springboot.thymeleafdemo.entity.Users;

@Service
public class UsersServiceImpl implements UsersService {
	
	private UsersRepository usersRepository;
	
	@Autowired
	public UsersServiceImpl(UsersRepository theUsersRepository) {
		usersRepository = theUsersRepository;
	}

	@Override
	public List<Users> findAll() {

		return usersRepository.findAll();
	}

	@Override
	public Users findById(int theId) {
		
		Optional<Users> result = usersRepository.findById(theId);
		
		Users theUsers = null;
		
		if(result.isPresent()) {
			theUsers = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find user id - " + theId);
		}
		
		return theUsers;	
	}

	@Override
	public void save(Users theUser) {
		usersRepository.save(theUser);
	}

	@Override
	public void deleteById(int theId) {
		usersRepository.deleteById(theId);
	}

}
