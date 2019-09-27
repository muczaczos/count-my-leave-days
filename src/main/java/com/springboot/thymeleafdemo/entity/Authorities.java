package com.springboot.thymeleafdemo.entity;

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
@Table(name="authorities")
public class Authorities {
	
	//define fields
	@Id
	@Column(name="username")
	private String username;
	
	
	@Column(name="authority")
	private String authority;
	
	
	//define constructors
	public Authorities() {
		
	}

	public Authorities(String users, String authority) {
		this.username = users;
		this.authority = authority;
	}

	
	//define getters and setters
	

	public String getAuthority() {
		return authority;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	


}
