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
import com.springboot.thymeleafdemo.entity.LeaveDays;
import com.springboot.thymeleafdemo.service.EmployeeService;
import com.springboot.thymeleafdemo.service.LeaveDaysService;

@Controller
@RequestMapping("/leavedays")
public class LeaveDaysController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);

		// true passed to CustomDateEditor constructor means convert empty String to
		// null
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	private LeaveDaysService leaveDaysService;
	private EmployeeService employeeService;

	public LeaveDaysController(LeaveDaysService leaveDaysService, EmployeeService employeeService) {
		this.leaveDaysService = leaveDaysService;
		this.employeeService = employeeService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String lisLeaveDays(Model theModel) {

		// get leave days from db
		List<LeaveDays> theLeaveDays = leaveDaysService.findAll();

		// add to the spring model
		theModel.addAttribute("leavedays", theLeaveDays);

		return "leavedays/list-leavedays";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create model attribute to bind form data
		LeaveDays theLeaveDays = new LeaveDays();

		theModel.addAttribute("leavedays", theLeaveDays);

		return "leavedays/leavedays-form";

	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("leavedaysId") int theId, Model theModel) {

		// get the leave days form the service
		LeaveDays theLeaveDays = leaveDaysService.findById(theId);

		// set leave day as a model attribute to pre-populate the form
		theModel.addAttribute("leavedays", theLeaveDays);

		// send over to our form
		return "leavedays/leavedays-form";
	}

	@PostMapping("/save")
	public String saveLeaveDays(@ModelAttribute("leavedays") LeaveDays theLeaveDays) {

		// save the leave day
		leaveDaysService.save(theLeaveDays);

		Employee employee = new Employee();

		// get employee by id from db
		employee = employeeService.findById(theLeaveDays.getEmployee().getId());

		// get your leave days from employee
		int yourLeaveDays = employee.getYourLeaveDays();

		// update your leave days
		employee.setYourLeaveDays(yourLeaveDays - theLeaveDays.getLeaveDays());

		// save employee
		employeeService.save(employee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/leavedays/list";

	}

	@GetMapping("/delete")
	public String delete(@RequestParam("leavedaysId") int theId) {

		Employee employee = new Employee();

		// get employee by id from db
		employee = employeeService.findById(leaveDaysService.findById(theId).getEmployee().getId());

		// get your leave days from employee
		int yourLeaveDays = employee.getYourLeaveDays();

		// update your leave days
		employee.setYourLeaveDays(yourLeaveDays + leaveDaysService.findById(theId).getLeaveDays());

		// delete the leave day
		leaveDaysService.deleteById(theId);

		// use a redirect to /employees/list
		return "redirect:/leavedays/list";

	}

}
