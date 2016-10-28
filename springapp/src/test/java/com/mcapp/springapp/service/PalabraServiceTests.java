package com.mcapp.springapp.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.common.dto.PalabraDto;
import com.mcapp.springapp.service.interfaces.IPalabraService;

public class PalabraServiceTests {

    private ApplicationContext context;
	
	@Resource
    private IPalabraService srvPalabra;
	
    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.srvPalabra = (IPalabraService) this.context.getBean("palabraService");
    }

    @Test
    public void testGetPalabrasList() {
        List<PalabraDto> Palabras = this.srvPalabra.getPalabrasDto();
        assertTrue(Palabras.size() > 0);
        assertTrue(Palabras.get(0).getClass().equals(PalabraDto.class));
        assertNotNull(Palabras.get(0).getIdioma());
        assertTrue(Palabras.get(0).getDefiniciones().size() > 0);
    }
    
    @Test
    public void testGetPalabrasListByMaxLength() {
        List<PalabraDto> Palabras = this.srvPalabra.getPalabrasDtoByMaxLength(5);
        assertTrue(Palabras.stream().allMatch(x -> x.getLongitud() < 5));
        assertTrue(Palabras.size() > 0);
        assertTrue(Palabras.get(0).getClass().equals(PalabraDto.class));
        assertNotNull(Palabras.get(0).getIdioma());
        assertTrue(Palabras.get(0).getDefiniciones().size() > 0);
    }
}