package com.mcapp.springapp.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.domain.Definicion;
import com.mcapp.springapp.domain.Idioma;
import com.mcapp.springapp.domain.Palabra;

public class PalabraDaoTests {
	
    private ApplicationContext context;
	
	@Resource
    private PalabraDao daoPalabra;
	
    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        daoPalabra = (PalabraDao) context.getBean("palabraDao");
    }

    @Test
    public void testGetPalabrasList() {
        List<Palabra> palabras = this.daoPalabra.findAll();
        assertTrue(palabras.size() > 0);
        assertTrue(palabras.get(0).getClass().equals(Idioma.class));
    }
    
    @Test
    public void testGetPalabraByValor() {
    	Palabra palabra = this.daoPalabra.getPalabraByValue("A");
    	assertNotNull(palabra);
    	assertTrue(palabra.getClass().equals(Palabra.class));
    	assertTrue(palabra.getDefiniciones().size() > 1);
    }
    
    @Test
    public void testSetDefinicionPalabra() {
    	Palabra palabra = this.daoPalabra.getPalabraByValue("A");
    	palabra.getDefiniciones().add(new Definicion("Hola mundo"));
    	this.daoPalabra.saveOrUpdate(palabra);
    }
}
