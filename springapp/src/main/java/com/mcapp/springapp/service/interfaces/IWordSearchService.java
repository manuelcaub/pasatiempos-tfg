package com.mcapp.springapp.service.interfaces;

import com.mcapp.springapp.common.dto.WordSearch;

public interface IWordSearchService {
	
	public WordSearch generateWordSearchPuzzle(int size, int numWords);

}
