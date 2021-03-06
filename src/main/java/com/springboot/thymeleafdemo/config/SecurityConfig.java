package com.springboot.thymeleafdemo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// use jdbc authentication ... oh yeah!!!
		
		auth.jdbcAuthentication().dataSource(securityDataSource);

	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	//redirection after success login
    	http.formLogin().defaultSuccessUrl("/", true);
    	
        http.authorizeRequests()
            	.antMatchers("/employees/list").hasAnyRole("ADMIN", "MANAGER")
            	.antMatchers("/employees/showFormForAdd").hasAnyRole("ADMIN", "MANAGER")
            	.antMatchers("/employees/showFormForUpdate").hasAnyRole("ADMIN", "MANAGER")
            	.antMatchers("/employees/save").hasRole("ADMIN")
            	.antMatchers("/employees/delete").hasRole("ADMIN")
            	.antMatchers("/employees/error").hasRole("ADMIN")
            	.antMatchers("/leavedays/list").hasAnyRole("ADMIN","MANAGER")
            	.antMatchers("/leavedays/showFormForAdd").hasAnyRole("ADMIN","MANAGER")
            	.antMatchers("/leavedays/save").hasAnyRole("ADMIN","EMPLOYEE")
            	.antMatchers("/leavedays/delete").hasAnyRole("ADMIN","EMPLOYEE")
            	.antMatchers("/users/list").hasAnyRole("ADMIN","MANAGER")
            	.antMatchers("/users/showFormForAdd").hasAnyRole("ADMIN","MANAGER")
            	.antMatchers("/users/showFormForUpdate").hasAnyRole("ADMIN","MANAGER")
            	.antMatchers("/users/save").hasRole("ADMIN")
            	.antMatchers("/users/delete").hasRole("ADMIN")
            	.antMatchers("/authorities/list").hasAnyRole("ADMIN","MANAGER")
            	.antMatchers("/authorities/showFormForAdd").hasAnyRole("ADMIN","MANAGER")
            	.antMatchers("/authorities/showFormForUpdate").hasAnyRole("ADMIN","MANAGER")
            	.antMatchers("/authorities/save").hasRole("ADMIN")
            	.antMatchers("/authorities/delete").hasRole("ADMIN")
             	.antMatchers(
                        "/js/**",
                        "/css/**",
                        "/img/**").permitAll()
            	//antMatchers below is needed for i18n on login page. 
            	.antMatchers("/showMyLoginPage/**").permitAll().anyRequest().fullyAuthenticated()
            	.anyRequest().authenticated()
            .and()
            	.formLogin()
            	.loginPage("/showMyLoginPage")
            	.loginProcessingUrl("/authenticateTheUser")
            	.permitAll()
            .and()
            	.logout().permitAll();
        		//.permitAll();
    }
    
    @Bean
	public UserDetailsManager userDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		
		return jdbcUserDetailsManager; 
	}
    
  
    
 
	
}