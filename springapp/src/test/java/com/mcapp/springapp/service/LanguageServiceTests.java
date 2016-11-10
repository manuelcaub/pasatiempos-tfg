package com.mcapp.springapp.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.domain.Language;
import com.mcapp.springapp.service.interfaces.LanguageService;

public class LanguageServiceTests {

    private ApplicationContext context;
	
	@Resource
    private LanguageService srvLanguage;
	
    @Before
    public void setUp() throws Exception {
        this.context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.srvLanguage = (LanguageService) context.getBean("idiomaService");
    }

    @Test
    public void testGetLanguagesList() {
        List<Language> idiomas = this.srvLanguage.getLanguages();
        assertTrue(idiomas.size() > 0);
        assertTrue(idiomas.get(0).getClass().equals(Language.class));
    }
}
