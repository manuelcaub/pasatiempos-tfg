package com.mcapp.springapp.web;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.service.interfaces.PuzzleService;
import com.mcapp.springapp.service.interfaces.WordSearchService;

@Controller
public class WordSearchController {

	@Resource
	private WordSearchService srvWordSearch;
	
	@Resource
	private PuzzleService srvPuzzle;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/secure/wordsearch", method = RequestMethod.GET)
	public ModelAndView getWordSearchView (Model model) { 
        model.addAttribute("usuarioLogin", new User());
	    return new ModelAndView("wordsearch");
	}
	
	@RequestMapping(value = "/secure/newwordsearch", method = RequestMethod.GET, params = {"size", "words"})
	@ResponseBody
	public String getNewCrossword (@RequestParam("size") int size, @RequestParam("words") int words, @RequestParam("sessionId") String sessionId) throws JsonProcessingException { 
        return new ObjectMapper().writeValueAsString(this.srvWordSearch.generateWordSearchPuzzle(size, words, sessionId));
	}
}
