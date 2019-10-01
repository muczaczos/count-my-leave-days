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
import com.springboot.thymeleafdemo.service.AuthoritiesService;

@Controller
@RequestMapping("/authorities")
public class AuthoritiesController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty String to
		// null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	public AuthoritiesController(AuthoritiesService authoritiesService) {
		this.authoritiesService = authoritiesService;
	}

	private AuthoritiesService authoritiesService;
	
	@GetMapping("/list")
	public String listAuthorities(Model theModel) {

		// get employees from db
		List<Authorities> theAuthorities = authoritiesService.findAll();

		// add to the spring model
		theModel.addAttribute("authorities", theAuthorities);

		return "authorities/list-authorities";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		Authorities theAuthorities = new Authorities();

		theModel.addAttribute("authorities", theAuthorities);

		return "authorities/add-authorities-form";

	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

		// get the employee form the service
		Authorities theAuthorities = authoritiesService.findById(theId);

		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("authorities", theAuthorities);

		// sendn over to our form
		return "authorities/authorities-form";
	}
	
	@PostMapping("/save")
	public String saveAuthorities(@ModelAttribute("authorities") Authorities theAuthorities) {

		// save the employee
		authoritiesService.save(theAuthorities);

		// use a redirect to prevent duplicate submissions
		return "redirect:/authorities/list";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("username") String username) {

		// delete the employee
		authoritiesService.deleteAuthorities(username);

		// use a redirect to /employees/list
		return "redirect:/authorities/list";

	}

}
