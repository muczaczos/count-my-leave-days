package com.springboot.thymeleafdemo.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="leave_days")
public class LeaveDays {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id")
	private int id;
	
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, 
						 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="employee_id")
	private Employee employee;
	 
	//I chooosen sql date format lib
	@Column(name="date_from")
	private Date dateFrom;
	
	@Column(name="date_to")
	private Date dateTo;
	
	@Column(name="leave_days")
	private int leaveDays;
	
	@Column(name="year")
	private int year;
	
	
	////////////////
	//constructors//
	///////////////
	public LeaveDays() {
		
	}

	public LeaveDays(int id, Employee employee, Date dateFrom, Date dateTo, int leaveDays, int year) {
		this.id = id;
		this.employee = employee;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.leaveDays = leaveDays;
		this.year = year;
	}
	
	public LeaveDays(Employee employee, Date dateFrom, Date dateTo, int leaveDays, int year) {
		this.employee = employee;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.leaveDays = leaveDays;
		this.year = year;
	}

	
	///////////////////////
	//getters and setters//
	///////////////////////
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public int getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(int leaveDays) {
		this.leaveDays = leaveDays;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	////////////////////////////
	//define toString() method//
	////////////////////////////
	@Override
	public String toString() {
		return "LeaveDays [id=" + id + ", employee=" + employee + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo
				+ ", leaveDays=" + leaveDays + ", year=" + year + "]";
	}
	
	
	
	

}
