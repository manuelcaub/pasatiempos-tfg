package com.mcapp.springapp.service.interfaces;

import java.util.List;
import java.util.Set;

import com.mcapp.springapp.domain.Definition;
import com.mcapp.springapp.domain.Word;

public interface WordService {
	
	public Set<Definition> getDefinitions(int pk);
	
	public List<Word> getWordsByMaxLength(int length);
	
	public String getDefinition(String word);
	
	public void setDefinition(String word, String definition);
	
}
