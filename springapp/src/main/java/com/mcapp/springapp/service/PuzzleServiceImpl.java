package com.mcapp.springapp.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.mcapp.springapp.domain.Puzzle;
import com.mcapp.springapp.repository.PuzzleRepository;
import com.mcapp.springapp.repository.UserRepository;
import com.mcapp.springapp.service.interfaces.PuzzleService;

@Service
public class PuzzleServiceImpl implements PuzzleService {

	@Resource
	private PuzzleRepository repPuzzle;
	
	@Resource
	private UserRepository repUser;
	
	@Override
	public void savePuzzle(Object specificPuzzle, String user) throws JsonProcessingException {
		Puzzle puzzle = new Puzzle();
		puzzle.setUser(this.repUser.getUserByEmail(user));
		puzzle.setPuzzle(this.toJson(specificPuzzle));
		this.repPuzzle.saveOrUpdate(puzzle);
	}
	
	@Override
	public String getPuzzlesByUser(String email) {
		return this.repPuzzle.getPuzzlesByUser(email);
	}
	
	private String toJson(Object specificPuzzle) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);
        return mapper.writeValueAsString(specificPuzzle);
	}

	@Override
	public void removePuzzle(String jsonPuzzle, String user) {
		this.repPuzzle.removeByPuzzle(jsonPuzzle, user);
		
	}
}
