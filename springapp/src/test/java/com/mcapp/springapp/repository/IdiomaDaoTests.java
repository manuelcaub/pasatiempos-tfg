package com.mcapp.springapp.repository;

import java.util.List;

import javax.annotation.Resource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.domain.Idioma;

public class IdiomaDaoTests {

    private ApplicationContext context;
	
	@Resource
    private IdiomaDao daoIdioma;
	
    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        daoIdioma = (IdiomaDao) context.getBean("idiomaDao");
    }

    @Test
    public void testGetIdiomasList() {
        List<Idioma> idiomas = this.daoIdioma.findAll();
        assertTrue(idiomas.size() > 0);
        assertTrue(idiomas.get(0).getClass().equals(Idioma.class));
    }
}