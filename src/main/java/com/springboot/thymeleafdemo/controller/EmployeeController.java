package com.springboot.thymeleafdemo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.thymeleafdemo.entity.Authorities;
import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.entity.Users;
import com.springboot.thymeleafdemo.service.AuthoritiesService;
import com.springboot.thymeleafdemo.service.EmployeeService;
import com.springboot.thymeleafdemo.service.UsersService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty String to
		// null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	private EmployeeService employeeService;
	private UsersService usersService;
	private AuthoritiesService authoritiesService;

	public EmployeeController(EmployeeService theEmployeeService, UsersService theUsersService,
			AuthoritiesService theAuthoritiesService) {
		employeeService = theEmployeeService;
		usersService = theUsersService;
		authoritiesService = theAuthoritiesService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// get employees from db
		List<Employee> theEmployees = employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

		// get the employee form the service
		Employee theEmployee = employeeService.findById(theId);

		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);

		// sendn over to our form
		return "employees/employee-form";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

		// save the employee
		employeeService.save(theEmployee);

		// set account enabled
		byte enabled = 1;

		// create new users object
		//Users theUser = new Users(theEmployee.getLogin(), "{noop}" + theEmployee.getPassword(), enabled);
		Users theUser = new Users(theEmployee.getLogin(), "{noop}" + theEmployee.getPassword(), enabled);

		// save user in db
		usersService.save(theUser);

		// create authorities object
		Authorities theAuthorities = new Authorities(theEmployee.getLogin(), theEmployee.getRole());

		// save authorities in db
		authoritiesService.save(theAuthorities);

		System.err.println(theEmployee.getLogin());

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {

		// delete the employee
		employeeService.deleteById(theId);

		// use a redirect to /employees/list
		return "redirect:/employees/list";

	}

}
