package com.mcapp.springapp.service;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.common.dto.FraseDto;

public class FraseServiceTests {

    private ApplicationContext context;
	
	@Resource
    private FraseService srvFrase;
	
    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.srvFrase = (FraseService) context.getBean("fraseService");
    }
	
	@Test
	public void test() {
		FraseDto frase = this.srvFrase.getFraseByLength(13);
		assertNotNull(frase);
	}
}
