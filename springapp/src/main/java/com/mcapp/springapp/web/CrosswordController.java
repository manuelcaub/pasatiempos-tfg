package com.mcapp.springapp.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcapp.springapp.common.dto.WordDto;
import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.service.interfaces.CrosswordService;
import com.mcapp.springapp.service.interfaces.WordService;
import com.mcapp.springapp.service.interfaces.PuzzleService;

@Controller
public class CrosswordController {
	
	@Resource
	private CrosswordService srvCrossword;
	
	@Resource
	private WordService srvWord;
	
	@Resource
	private PuzzleService srvPasatiempo;
	
	@RequestMapping(value = "/secure/crossword", method = RequestMethod.GET)
	public ModelAndView getCrosswordView (Model model) { 
        model.addAttribute("usuarioLogin", new User());
	    return new ModelAndView("crossword");
	}
	
	@RequestMapping(value = "/secure/newcrossword", method = RequestMethod.GET)
	public @ResponseBody String getNewCrossword (@RequestParam int size, @RequestParam int blacks, @RequestParam String sessionId) throws JsonProcessingException { 
		return new ObjectMapper().writeValueAsString(this.srvCrossword.generateCrossword(size, blacks, sessionId));
	}
	
	@RequestMapping(value = "/secure/savedefinition", method = RequestMethod.POST)
	@ResponseBody
	public String setDefinition (@RequestBody WordDto word) { 
        this.srvWord.setDefinition(word.getWord(), word.getDefinition());
        return "success";
	}
}
