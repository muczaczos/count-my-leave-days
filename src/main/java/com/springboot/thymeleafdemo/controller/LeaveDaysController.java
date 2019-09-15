package com.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.thymeleafdemo.entity.LeaveDays;
import com.springboot.thymeleafdemo.service.LeaveDaysService;

@Controller
@RequestMapping("/leavedays")
public class LeaveDaysController {
	
	private LeaveDaysService leaveDaysService;
	
	public LeaveDaysController(LeaveDaysService leaveDaysService) {
		this.leaveDaysService = leaveDaysService;
	}

	// add mapping for "/list"
	
	@GetMapping("/list")
	public String lisLeaveDays(Model theModel) {
		
		// get employees from db
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
	
	@GetMapping ("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("leavedaysId") int theId,
									Model theModel) {
		
		// get the employee form the service 
		LeaveDays theLeaveDays = leaveDaysService.findById(theId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("leavedays", theLeaveDays);
		
		// send over to our form
		return "leavedays/leavedays-form";
	}
	
	@PostMapping("/save")
	public String saveLeaveDays(@ModelAttribute("leavedays") LeaveDays theLeaveDays) {
		
		// save the employee
		leaveDaysService.save(theLeaveDays);
		
		//use a redirect to prevent duplicate submissions
		return "redirect:/leavedays/list";
				
				
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("leavedaysId") int theId) {
		
		// delete the employee
		leaveDaysService.deleteById(theId);
		
		//use a redirect to /employees/list
		return "redirect:/leavedays/list";	
				
	}
	

}
