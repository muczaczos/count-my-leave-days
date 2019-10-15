package com.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LoginController {
	
	private static final String LANGUAGE = "language";
	
	private final LocaleResolver localeResolver;

	public LoginController(LocaleResolver localeResolver) {

	    this.localeResolver = localeResolver;
	}
	
	@GetMapping("showMyLoginPage")
	public String showMyLoginPage() {
	
			return "plain-login";
		
		
	}
	
	@GetMapping("showMyLoginPage2")
	public String showMyLoginPage2() {
	
			return "plain-login2";
		
		
	}

}
