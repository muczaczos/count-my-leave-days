package com.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.thymeleafdemo.entity.Employee;

@Controller
@RequestMapping("/")
public class StartController {
	
	@GetMapping("/")
	public String listEmployees(Model theModel) {

		return "start/index";
	}

}
