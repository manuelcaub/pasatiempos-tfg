package com.mcapp.springapp.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcapp.springapp.common.dto.Crossword;
import com.mcapp.springapp.common.dto.WordSearch;
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
	public void saveCrossword(Crossword crossword, String user) {
		Puzzle puzzle = new Puzzle();
		puzzle.setUser(this.repUser.getUserByEmail(user));
		puzzle.setPuzzle(this.crosswordToJson(crossword));
		this.repPuzzle.saveOrUpdate(puzzle);
	}

	@Override
	public List<Crossword> getCrosswordByUser(String user) {
		return this.repPuzzle.getCrosswordsByUser(user).stream().map(x -> this.jsonToCrossword(x)).collect(Collectors.toList());
	}
	
	private Crossword jsonToCrossword(String json) {
		Crossword crossword = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
        	crossword = mapper.readValue(json, Crossword.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return crossword;
	}
	
	private String crosswordToJson(Crossword crossword) {
		String json = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
			json = mapper.writeValueAsString(crossword);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return json;
	}

	@Override
	public void saveWordSearch(WordSearch wordsearch, String user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<WordSearch> getWordSearchByUser(String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
