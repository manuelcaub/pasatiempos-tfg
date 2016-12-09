package com.mcapp.springapp.service.interfaces;

import java.util.List;

import com.mcapp.springapp.common.dto.SimpleUserDto;
import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.service.validator.EmailExistsException;
import com.mcapp.springapp.service.validator.RoleInUserExistException;

public interface UserService {

	public List<User> getUsers();
	
	public User getUserByEmail(String email);

	public void save(User dto);

	public User registerUser(User dto) throws EmailExistsException;

	public List<SimpleUserDto> getSimpleUsersExceptAdmin();

	public void removeUser(String email);

	public void setCollaborator(String user) throws RoleInUserExistException;
	
}
