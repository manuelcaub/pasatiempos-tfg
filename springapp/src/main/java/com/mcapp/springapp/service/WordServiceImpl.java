package com.mcapp.springapp.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcapp.springapp.domain.Definition;
import com.mcapp.springapp.domain.Word;
import com.mcapp.springapp.repository.WordRepository;
import com.mcapp.springapp.service.interfaces.WordService;

@Service
public class WordServiceImpl implements WordService {
	
	@Resource
	private WordRepository repWord;
	
	@Override
	public Set<Definition> getDefinitions(int pk) {
		return this.repWord.findByPk(pk).getDefinitions();
	}
	
	@Override
	public List<Word> getWordsByMaxLength(int longitud) {
		return this.repWord.getWordsByMaxLength(longitud);
	}
	
	@Override
	public String getDefinition(String word) {
		return this.repWord.getDefinition(word);
	}

	@Override
	public void setDefinition(String wordValue, String definition) {
		Word word = this.repWord.getWordByValue(wordValue);
		word.getDefinitions().add(new Definition(definition));
		this.repWord.saveOrUpdate(word);
	}
}
