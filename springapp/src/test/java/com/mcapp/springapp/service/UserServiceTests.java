package com.mcapp.springapp.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.service.interfaces.UserService;

public class UserServiceTests {

    private ApplicationContext context;
	
	@Resource
    private UserService srvUser;
	
    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.srvUser = (UserService) this.context.getBean("userService");
    }

    @Test
    public void testGetUsersList() {
        List<User> Users = this.srvUser.getUsers();
        assertTrue(Users.size() > 0);
        assertTrue(Users.get(0).getClass().equals(User.class));
        assertNotNull(Users.get(0).getCountry());
    }
}