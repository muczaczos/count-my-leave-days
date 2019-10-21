package com.springboot.thymeleafdemo.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.entity.LeaveDays;
import com.springboot.thymeleafdemo.entity.Users;
import com.springboot.thymeleafdemo.service.EmployeeService;
import com.springboot.thymeleafdemo.service.LeaveDaysService;
import com.springboot.thymeleafdemo.service.UsersService;

@Controller
@RequestMapping("/")
public class StartController extends WebMvcConfigurerAdapter {

	private LeaveDaysService leaveDayService;
	private EmployeeService employeeService;
	private UsersService usersService;

	public StartController(LeaveDaysService theLeaveDayService, EmployeeService theEmployeeService, UsersService theUsersSerice) {
		leaveDayService = theLeaveDayService;
		employeeService = theEmployeeService;
		usersService = theUsersSerice;

	}
	
	@GetMapping("/")
	public String listLeaveDays(Model theModel) {

		String username;

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}

		try {
			// find the employee with username from actual session
			Employee theEmployee = employeeService.findByLogin(username);
			
			//Count days from day of employment
			Date dateOfemployment = theEmployee.getDateOfEmployment();
			Date today = new Date();
			
		    long days = today.getTime() - dateOfemployment.getTime();
		    
		    long daysInCompany = TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
			
			// find the users with username from actual session
			List<Users> theUsers = usersService.findByUsername(username);

			// find the leave days with id from theEmployee object
			List<LeaveDays> theLeaveDays = leaveDayService.findAllByEmployee(theEmployee);
			
			// Leave days counter
			int leaveDaysCounter = theEmployee.getYourLeaveDays();
			
			for(LeaveDays leavedays: theLeaveDays) {
				System.err.println(leavedays.toString() + "\n");
			}
			
			// add to the spring model
			theModel.addAttribute("daysincompany", daysInCompany);

			// add to the spring model
			theModel.addAttribute("leavedays", theLeaveDays);
			
			// add to the spring model
			theModel.addAttribute("employee", theEmployee);

			// add to the spring model
			theModel.addAttribute("users", theUsers);
			
			// add to the spring model
			theModel.addAttribute("leavedayscounter", leaveDaysCounter);
						
			System.err.println(username + " " + theEmployee.getId());
		} catch (NullPointerException e) {

		}
		return "start/index";
	}

}
