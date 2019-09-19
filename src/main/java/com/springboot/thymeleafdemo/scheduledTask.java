package com.springboot.thymeleafdemo;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.service.EmployeeService;


@Component
public class scheduledTask {
	
	  private static final Logger log = LoggerFactory.getLogger(scheduledTask.class);

	    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    
	    private EmployeeService employeeService;
	   
 	    private List<Employee> theEmployees;

	    @Autowired
	    public scheduledTask(EmployeeService employeeService, List<Employee> theEmployees) {
	        this.employeeService = employeeService;
	        this.theEmployees = theEmployees;
	    }
	    

	    //@Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")

	   
	    @Scheduled(cron ="0 10 12 1 1 ? *") //fires first day of week, 12:10, january, every year
	    //@Scheduled(fixedRate = 15000) //fires every 15 seconds
	    public void addLeaveDays() {
	    
	 	  
	    	System.err.println("server runing!!!");
	    	
	    	LocalDate today = LocalDate.now();
	    	
	    	/*
	        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
	        LocalDate yesterday = tomorrow.minusDays(2);
	        LocalDate beforeYesterday = tomorrow.minusDays(3);
	        
	        System.err.println(today.getYear());
	        System.err.println(beforeYesterday.getYear());
	        System.err.println(today);
	        
	        if(today.getYear() - beforeYesterday.getYear() == 1) {
	        	System.err.println("trzeba dodac nową pulę urlopu!!");
  	        }
	        */
	    	
	        
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 2 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays2() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 3 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays3() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 4 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays4() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 5 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays5() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 6 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays6() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 7 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays7() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 8 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays8() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 9 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays9() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 10 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays10() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 11 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays11() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 12 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays12() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 13 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays13() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }
	    
	    @Scheduled(cron ="0 10 12 14 1 ? *") //fires first day of week, 12:10, january, every year
	    public void addLeaveDays14() {
	    
	    	LocalDate today = LocalDate.now();
	  
	        theEmployees = employeeService.findAll();
	        
	        for (Employee employee : theEmployees) 
	        { 
	            if(today.getYear() - employee.getCurrentYear() == 1) {
	            	employee.setCurrentYear(today.getYear());
	            	employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
	            	employeeService.save(employee);
	            }
	        }
	        
	    }

}
