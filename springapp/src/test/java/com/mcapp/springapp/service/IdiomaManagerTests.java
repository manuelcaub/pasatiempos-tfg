package com.mcapp.springapp.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.domain.Idioma;

public class IdiomaManagerTests {

    private ApplicationContext context;
	
	@Resource
    private IdiomaService srvIdioma;
	
    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.srvIdioma = (IdiomaService) context.getBean("idiomaService");
    }

    @Test
    public void testGetIdiomasList() {
        List<Idioma> idiomas = this.srvIdioma.getIdiomas();
        assertTrue(idiomas.size() > 0);
        assertTrue(idiomas.get(0).getClass().equals(Idioma.class));
    }
}
