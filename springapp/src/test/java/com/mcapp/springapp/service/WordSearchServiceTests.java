package com.mcapp.springapp.service;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.service.interfaces.WordSearchService;

public class WordSearchServiceTests {
	
    private ApplicationContext context;
	
	@Resource
    private WordSearchService srvWordSearch;
	
    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.srvWordSearch = (WordSearchService) context.getBean("wordSearchService");
    }
	
	@Test
	public void test() {
	}

}
