package com.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LoginController {
	
	@GetMapping("showMyLoginPage")
	public String showMyLoginPage() {
	
			return "plain-login";
		
		
	}

}
