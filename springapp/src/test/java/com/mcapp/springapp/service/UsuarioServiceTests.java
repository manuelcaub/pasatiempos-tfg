package com.mcapp.springapp.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.service.interfaces.IUsuarioService;

public class UsuarioServiceTests {

    private ApplicationContext context;
	
	@Resource
    private IUsuarioService srvUsuario;
	
    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.srvUsuario = (IUsuarioService) this.context.getBean("usuarioService");
    }

    @Test
    public void testGetUsuariosList() {
        List<UsuarioDto> Usuarios = this.srvUsuario.getUsuarios();
        assertTrue(Usuarios.size() > 0);
        assertTrue(Usuarios.get(0).getClass().equals(UsuarioDto.class));
        assertNotNull(Usuarios.get(0).getPais());
    }
}