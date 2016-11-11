package com.mcapp.springapp.service.interfaces;

import java.util.List;

import com.mcapp.springapp.common.dto.Crossword;
import com.mcapp.springapp.common.dto.WordSearch;

public interface PuzzleService {
	
	public void saveCrossword(Crossword crossword, String user);
	
	public List<String> getCrosswordByUser(String user);
	
	public void saveWordSearch(WordSearch wordsearch, String user);
	
	public List<WordSearch> getWordSearchByUser(String user);
	
	public String getPuzzlesByUser(String user);
}
