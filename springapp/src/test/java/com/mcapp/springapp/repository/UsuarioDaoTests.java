package com.mcapp.springapp.repository;

import java.util.List;

import javax.annotation.Resource;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.domain.Usuario;

public class UsuarioDaoTests {

    private ApplicationContext context;
	
	@Resource
    private UsuarioDao daoUsuario;
	
    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        daoUsuario = (UsuarioDao) context.getBean("usuarioDao");
    }

    @Test
    public void testGetUsuariosList() {
        List<Usuario> Usuarios = this.daoUsuario.findAll();
        assertTrue(Usuarios.size() > 0);
        assertTrue(Usuarios.get(0).getClass().equals(Usuario.class));
    }
    
    @Test
    public void testGetUsuarioByEmail() {
    	UsuarioDto usuario = this.daoUsuario.getUsuarioByEmail("m@m.com");
    	assertNotNull(usuario);
    	
    	UsuarioDto usuarioNull = this.daoUsuario.getUsuarioByEmail("m@mm.com");
    	assertNull(usuarioNull);
    }
}