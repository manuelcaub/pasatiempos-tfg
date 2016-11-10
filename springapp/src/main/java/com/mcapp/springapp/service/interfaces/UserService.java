package com.mcapp.springapp.service.interfaces;

import java.util.List;

import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.service.validator.EmailExistsException;

public interface UserService {

	public List<User> getUsers();
	
	public User getUserByEmail(String email);

	public void save(User dto);

	public User registerUser(User dto) throws EmailExistsException;
	
}
