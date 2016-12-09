package com.mcapp.springapp.service.interfaces;

import java.util.Set;

import com.mcapp.springapp.domain.Definition;

public interface WordService {
	
	public Set<Definition> getDefinitions(int pk);
	
	public String getDefinition(String word);
	
	public void setDefinition(String word, String definition);
	
	public void saveWord(String word, String definition);
	
	public void saveWord(String word);
}
