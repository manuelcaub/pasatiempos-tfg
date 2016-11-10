package com.mcapp.springapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.repository.UserRepository;
import com.mcapp.springapp.service.interfaces.UserService;
import com.mcapp.springapp.service.validator.EmailExistsException;

@Service
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserRepository repUser;
	
	@Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<User> getUsers(){
		return this.repUser.findAll();
	}
	
	@Override
    public void save(User user) {
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        this.repUser.saveOrUpdate(user);
    }

	@Override
	public User getUserByEmail(String email) {
		return this.repUser.getUserByEmail(email);
	}
	
    @Override
    public User registerUser(User user) throws EmailExistsException {
         
        if (existEmail(user.getEmail())) {  
            throw new EmailExistsException ("La dirección de email ya existe: " +  user.getEmail());
        }

		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        this.repUser.saveOrUpdate(user); 
        return user;
    }
    
    private boolean existEmail(String email) {        
        return this.repUser.getUserByEmail(email) != null;
    }
}
