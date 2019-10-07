package com.springboot.thymeleafdemo;


import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@EnableScheduling
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ThymeleafdemoApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafdemoApplication.class, args);
		
	}
	
	   @Bean
	   public LocaleResolver localeResolver(){
	        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	        localeResolver.setDefaultLocale(Locale.CHINA);
	        return  localeResolver;
	    }

	    @Bean
	    public LocaleChangeInterceptor localeChangeInterceptor() {
	        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	        localeChangeInterceptor.setParamName("lang");
	        return localeChangeInterceptor;
	    }

	    @Override
	    public void addInterceptors(InterceptorRegistry registry){
	        registry.addInterceptor(localeChangeInterceptor());
	    }



}
