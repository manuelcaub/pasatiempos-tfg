package com.mcapp.springapp.service.interfaces;

import com.mcapp.springapp.common.dto.Crossword;

public interface CrosswordService {
	
	public Crossword generateCrossword(int size, int blacks, String sessionId);
	
}
