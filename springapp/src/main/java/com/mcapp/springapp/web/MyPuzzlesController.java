package com.mcapp.springapp.web;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mcapp.springapp.common.dto.Crossword;
import com.mcapp.springapp.common.dto.WordSearch;
import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.service.interfaces.CrosswordService;
import com.mcapp.springapp.service.interfaces.PuzzleService;
import com.mcapp.springapp.service.interfaces.WordSearchService;

@Controller
public class MyPuzzlesController {
	
	@Resource
	private WordSearchService srvWordSearch;
	
	@Resource
	private CrosswordService srvCrossword;
	
	@Resource
	private PuzzleService srvPuzzle;
	
	@RequestMapping(value = "/mypuzzles", method = RequestMethod.GET)
	public ModelAndView getMyPuzzlesView (Model model) {
		model.addAttribute("usuarioLogin", new User());
		return new ModelAndView("mypuzzles");
	}
	
	@RequestMapping(value = "/getpuzzles", method = RequestMethod.GET)
	@ResponseBody
	public String getCrosswords (Principal principal) { 
		return this.srvPuzzle.getPuzzlesByUser(principal.getName());
	}

	@RequestMapping(value = "/savewordsearch", method = RequestMethod.POST)
	@ResponseBody
	public String saveWordSearch (@RequestBody WordSearch wordsearch, Principal principal) {
		try {
			this.srvPuzzle.savePuzzle(wordsearch, principal.getName());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "success";
	}
	
	@RequestMapping(value = "/savecrossword", method = RequestMethod.POST)
	@ResponseBody
	public String saveWordSearch (@RequestBody Crossword crossword, Principal principal) {
		try {
			this.srvPuzzle.savePuzzle(crossword, principal.getName());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "success";
	}
}
