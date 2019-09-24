package com.springboot.thymeleafdemo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="authorities")
public class Authorities {
	
	//define fields
	@ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, 
			 CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="username")
	private Users users;
	
	@Column(name="authority")
	private String authority;
	
	
	//define constructors
	public Authorities() {
		
	}

	public Authorities(Users users, String authority) {
		this.users = users;
		this.authority = authority;
	}

	
	//define getters and setters
	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	


}
