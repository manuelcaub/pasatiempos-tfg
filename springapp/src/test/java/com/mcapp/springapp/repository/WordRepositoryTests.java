package com.mcapp.springapp.repository;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mcapp.springapp.domain.Definition;
import com.mcapp.springapp.domain.Word;

public class WordRepositoryTests {
	
    private ApplicationContext context;
	
	@Resource
    private WordRepository repWord;
	
    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:all-config.xml");
        repWord = (WordRepository) context.getBean("wordRepository");
    }

    @Test
    public void testGetwordsList() {
        List<Word> words = this.repWord.findAll();
        assertTrue(words.size() > 0);
        assertTrue(words.get(0).getClass().equals(Word.class));
    }
    
    @Test
    public void testGetwordByValor() {
    	Word word = this.repWord.getWordByValue("A");
    	assertNotNull(word);
    	assertTrue(word.getClass().equals(Word.class));
    	assertTrue(word.getDefinitions().size() > 1);
    }
    
    @Test
    public void testSetDefinicionword() {
    	Word word = this.repWord.getWordByValue("A");
    	word.getDefinitions().add(new Definition("Hola mundo"));
    	this.repWord.saveOrUpdate(word);
    }
}
