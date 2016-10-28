package com.mcapp.springapp.service;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CrosswordServiceTests {

    private ApplicationContext context;
	
	@Resource
    private CrosswordService srvCrossword;
	
    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.srvCrossword = (CrosswordService) context.getBean("crosswordService");
    }
	
	@Test
	public void test() {
		System.out.println(this.srvCrossword.generateCrossword(6, 30).toString());
	}

}
