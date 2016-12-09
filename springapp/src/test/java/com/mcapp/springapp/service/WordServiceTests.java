package com.mcapp.springapp.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.domain.Word;
import com.mcapp.springapp.service.interfaces.WordService;

public class WordServiceTests {

    private ApplicationContext context;
	
	@Resource
    private WordService srvWord;
	
    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.srvWord = (WordService) this.context.getBean("wordService");
    }
    
    @Test
    public void testGetDefinition() {
    	String definiciones = this.srvWord.getDefinition("A");
    	assertNotNull(definiciones);
    	
    	String definicionesNull = this.srvWord.getDefinition("aasdfasdwerdfvxzc");
    	assertNull(definicionesNull);
    }
}