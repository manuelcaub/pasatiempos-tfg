package com.mcapp.springapp.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface PuzzleService {
	
	public void savePuzzle(Object specificPuzzle, String user) throws JsonProcessingException;
	
	public String getPuzzlesByUser(String user);
}
