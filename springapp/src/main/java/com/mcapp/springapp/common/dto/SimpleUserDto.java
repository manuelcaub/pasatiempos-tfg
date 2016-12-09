package com.mcapp.springapp.common.dto;

public class SimpleUserDto {
	private String name;
	
	private String email;
	
	private String roles;
	
	public SimpleUserDto() {
	}
	
	public SimpleUserDto(String name, String email, String roles) {
		this.name = name;
		this.email = email;
		this.roles = roles;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
