package com.mcapp.springapp.service.interfaces;

public interface ISecurityService {

	public String findLoggedInUsername();
	
	public void autologin(String email, String password);
}
