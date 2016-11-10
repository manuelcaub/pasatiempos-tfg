package com.mcapp.springapp.repository;

import java.util.List;

import javax.annotation.Resource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.domain.User;

public class UserRepositoryTests {

    private ApplicationContext context;
	
	@Resource
    private UserRepository repUser;
	
    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        repUser = (UserRepository) context.getBean("userRepository");
    }

    @Test
    public void testGetUsersList() {
        List<User> Users = this.repUser.findAll();
        assertTrue(Users.size() > 0);
        assertTrue(Users.get(0).getClass().equals(User.class));
    }
    
    @Test
    public void testGetUserByEmail() {
    	User User = this.repUser.getUserByEmail("ma@ma.com");
    	assertNotNull(User);
    	
    	User UserNull = this.repUser.getUserByEmail("ma@mmm.com");
    	assertNull(UserNull);
    }
}