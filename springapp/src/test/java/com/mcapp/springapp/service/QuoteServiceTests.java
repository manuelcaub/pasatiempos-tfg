package com.mcapp.springapp.service;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.service.interfaces.QuoteService;

public class QuoteServiceTests {

    private ApplicationContext context;
	
	@Resource
    private QuoteService srvQuote;
	
    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.srvQuote = (QuoteService) context.getBean("quoteService");
    }
	
	@Test
	public void test() {

	}
}
