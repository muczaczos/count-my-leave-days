package com.springboot.thymeleafdemo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name="employee")
public class Employee {
	
	//define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id")
	private int id;
	
	@Column(name="login")
	private String login;
	
	@Column(name="password")
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="tel")
	private int tel;
	
	//I chooosen sql date format
	@Nullable
	@Column(name="date_of_employment", nullable = true)
	private Date dateOfEmployment;
	
	@Column(name="date_of_expire", nullable = true)
	private Date dateOfExpire;
	
	@Column(name="leave_days_limit")
	private int leaveDaysLimit;
	
	@Column(name="your_leave_days")
	private int yourLeaveDays;
	
	@Column(name="current_year")
	private int currentYear;
	
	@Column(name="role")
	private String role;
	
	// define constructors
	
	public Employee() {
		
	}
	
	public Employee(String login, String password, String firstName, String lastName, int tel,
			Date dateOfEmployment, Date dateOfExpire, int leaveDaysLimit, int yourLeaveDays, int currentYear,
			String role) {
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.tel = tel;
		this.dateOfEmployment = dateOfEmployment;
		this.dateOfExpire = dateOfExpire;
		this.leaveDaysLimit = leaveDaysLimit;
		this.yourLeaveDays = yourLeaveDays;
		this.currentYear = currentYear;
		this.role = role;
	}


	public Employee(int id, String login, String password, String firstName, String lastName, int tel,
			Date dateOfEmployment, Date dateOfExpire, int leaveDaysLimit, int yourLeaveDays, int currentYear,
			String role) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.tel = tel;
		this.dateOfEmployment = dateOfEmployment;
		this.dateOfExpire = dateOfExpire;
		this.leaveDaysLimit = leaveDaysLimit;
		this.yourLeaveDays = yourLeaveDays;
		this.currentYear = currentYear;
		this.role = role;
	}



	// define getter / setter
	
	public int getId() {
		return id;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public int getTel() {
		return tel;
	}


	public void setTel(int tel) {
		this.tel = tel;
	}


	public Date getDateOfEmployment() {
		return dateOfEmployment;
	}


	public void setDateOfEmployment(Date dateOfEmployment) {
		this.dateOfEmployment = dateOfEmployment;
	}


	public Date getDateOfExpire() {
		return dateOfExpire;
	}


	public void setDateOfExpire(Date dateOfExpire) {
		this.dateOfExpire = dateOfExpire;
	}


	public int getLeaveDaysLimit() {
		return leaveDaysLimit;
	}


	public void setLeaveDaysLimit(int leaveDaysLimit) {
		this.leaveDaysLimit = leaveDaysLimit;
	}


	public int getYourLeaveDays() {
		return yourLeaveDays;
	}


	public void setYourLeaveDays(int yourLeaveDays) {
		this.yourLeaveDays = yourLeaveDays;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// define toString method
	@Override
	public String toString() {
		return "Employee [id=" + id + ", login=" + login + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", tel=" + tel + ", dateOfEmployment=" + dateOfEmployment
				+ ", dateOfExpire=" + dateOfExpire + ", leaveDaysLimit=" + leaveDaysLimit + ", yourLeaveDays="
				+ yourLeaveDays + ", currentYear=" + currentYear + ", role=" + role + "]";
	}


	

	




	

	
	

}
