package com.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.thymeleafdemo.dao.LeaveDaysRepository;
import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.entity.LeaveDays;

@Service
public class LeaveDaysServiceImpl implements LeaveDaysService {

	private LeaveDaysRepository leaveDaysRepository;

	@Autowired
	public LeaveDaysServiceImpl(LeaveDaysRepository theLeaveDayseRepository) {
		leaveDaysRepository = theLeaveDayseRepository;
	}

	@Override
	public List<LeaveDays> findAll() {
		
		return leaveDaysRepository.findAll();
	}

	@Override
	public LeaveDays findById(int theId) {
		
		Optional<LeaveDays> result = leaveDaysRepository.findById(theId);
		
		LeaveDays theLeaveDays = null;
		
		if(result.isPresent()) {
			theLeaveDays = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theLeaveDays;
	}

	@Override
	public void save(LeaveDays theLeaveDays) {
		
		leaveDaysRepository.save(theLeaveDays);

	}

	@Override
	public void deleteById(int theId) {
		
		leaveDaysRepository.deleteById(theId);

	}

	@Override
	public List<LeaveDays> findAllByEmployee(Employee employee) {
		
		return leaveDaysRepository.findAllByEmployee(employee);
	}

	@Override
	public void deleteLeaveDayByEmployee(Employee employee) {

		leaveDaysRepository.deleteLeaveDayByEmployee(employee);
		
	}


}
