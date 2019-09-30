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

import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.entity.Users;
import com.springboot.thymeleafdemo.service.UsersService;

@Controller
@RequestMapping("/users")
public class UsersController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty String to
		// null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	private UsersService usersService;

	public UsersController(UsersService theUsersService) {
		usersService = theUsersService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listUsers(Model theModel) {

		// get employees from db
		List<Users> theUsers = usersService.findAll();

		// add to the spring model
		theModel.addAttribute("users", theUsers);

		return "users/list-users";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Users theUsers = new Users();

		theModel.addAttribute("users", theUsers);

		return "users/user-form";

	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("username") String username, Model theModel) {

		// get the employee form the service
		List<Users> theUsers = usersService.findByUsername(username);
		Users theUsers2 = new Users();
		for(Users users: theUsers) {
			theUsers2 = users;
		}

		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("users", theUsers2);

		// sendn over to our form
		return "users/user-form";
	}

	@PostMapping("/save")
	public String saveUsers(@ModelAttribute("users") Users theUsers) {

		// save the employee
		usersService.save(theUsers);

		// use a redirect to prevent duplicate submissions
		return "redirect:/users/list";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("usersId") int theId) {

		// delete the employee
		usersService.deleteById(theId);

		// use a redirect to /employees/list
		return "redirect:/users/list";

	}

}
