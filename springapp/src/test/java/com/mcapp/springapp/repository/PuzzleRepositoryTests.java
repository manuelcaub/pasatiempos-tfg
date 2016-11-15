package com.mcapp.springapp.repository;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcapp.springapp.common.dto.Crossword;

public class PuzzleRepositoryTests {
    private ApplicationContext context;
	
	@Resource
    private PuzzleRepository repPuzzle;
	
    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        this.repPuzzle = (PuzzleRepository) context.getBean("pasatiempoDao");
    }

    @Test
    public void testGetCrossword() throws JsonParseException, JsonMappingException, IOException {

    }
}
