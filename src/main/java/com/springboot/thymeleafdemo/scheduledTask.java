package com.springboot.thymeleafdemo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.springboot.thymeleafdemo.entity.Employee;
import com.springboot.thymeleafdemo.service.EmployeeService;

@Component
public class scheduledTask {

	private EmployeeService employeeService;

	private List<Employee> theEmployees;

	@Autowired
	public scheduledTask(EmployeeService employeeService, List<Employee> theEmployees) {
		this.employeeService = employeeService;
		this.theEmployees = theEmployees;
	}

	/*
	  @Scheduled(fixedRate = 5000) //fires every 5 seconds public void addDupa()
	  public void test() throws FileNotFoundException, DocumentException
	  {
		  
		System.err.println("test");
		  
	  }
	*/
	
	// @Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of
	// week]")
	@Scheduled(cron = "0 10 12 1 1 ?") // fires first day of january, 12:10, january, every year
	public void addLeaveDays() {

		/*
		 * LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS); LocalDate yesterday =
		 * tomorrow.minusDays(2); LocalDate beforeYesterday = tomorrow.minusDays(3);
		 * 
		 * System.err.println(today.getYear());
		 * System.err.println(beforeYesterday.getYear()); System.err.println(today);
		 * 
		 * if(today.getYear() - beforeYesterday.getYear() == 1) {
		 * System.err.println("trzeba dodac nową pulę urlopu!!"); }
		 */

		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 2 1 ?")
	public void addLeaveDays2() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 3 1 ?")
	public void addLeaveDays3() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 4 1 ?")
	public void addLeaveDays4() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 5 1 ?")
	public void addLeaveDays5() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 6 1 ?")
	public void addLeaveDays6() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 7 1 ?")
	public void addLeaveDays7() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 8 1 ?")
	public void addLeaveDays8() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 9 1 ?")
	public void addLeaveDays9() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 10 1 ?")
	public void addLeaveDays10() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 11 1 ?")
	public void addLeaveDays11() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 12 1 ?")
	public void addLeaveDays12() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 13 1 ?")
	public void addLeaveDays13() {
		addLeaveDaysLimit();
	}

	@Scheduled(cron = "0 10 12 14 1 ?")
	public void addLeaveDays14() {
		addLeaveDaysLimit();
	}

	private void addLeaveDaysLimit() {

		LocalDate today = LocalDate.now();

		theEmployees = employeeService.findAll();

		for (Employee employee : theEmployees) {
			if (today.getYear() - employee.getCurrentYear() == 1) {
				employee.setCurrentYear(today.getYear());
				employee.setYourLeaveDays(employee.getYourLeaveDays() + employee.getLeaveDaysLimit());
				employeeService.save(employee);
			}
		}
	}

	private void addLeaveDaysLimit2() {

		System.err.println("Sheduled test!");
	}

}
