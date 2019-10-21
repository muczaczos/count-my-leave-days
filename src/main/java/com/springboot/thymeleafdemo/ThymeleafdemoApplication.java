package com.springboot.thymeleafdemo;


import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@EnableScheduling
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ThymeleafdemoApplication extends SpringBootServletInitializer implements WebMvcConfigurer    {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ThymeleafdemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafdemoApplication.class, args);
		
	}
	
	Locale cLocale = new Locale.Builder().setLanguage("pl").setRegion("PL").build();
	
	   @Bean
	   public LocaleResolver localeResolver(){
	        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	        localeResolver.setDefaultLocale(cLocale);
	        return  localeResolver;
	    }

	    @Bean
	    public LocaleChangeInterceptor localeChangeInterceptor() {
	        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	        localeChangeInterceptor.setParamName("lang");
	        return localeChangeInterceptor;
	    }

	    
	    /*
	     * extends WebMvcConfigurerAdapter if you want use that
	     */
	  @Override
	    public void addInterceptors(InterceptorRegistry registry){
	        registry.addInterceptor(localeChangeInterceptor());
	    }
	 
	    
	    //without that bean, i18n messages don't work (on Linux?)
	    @Bean
	    public MessageSource messageSource() {
	        ReloadableResourceBundleMessageSource messageSource
	          = new ReloadableResourceBundleMessageSource();
	         
	        messageSource.setBasename("classpath:messages");
	        messageSource.setDefaultEncoding("UTF-8");
	        return messageSource;
	    }



}
