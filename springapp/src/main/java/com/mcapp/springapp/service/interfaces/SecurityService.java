package com.mcapp.springapp.service.interfaces;

public interface SecurityService {

	public String findLoggedInUsername();
	
	public void autologin(String email, String password);
}
