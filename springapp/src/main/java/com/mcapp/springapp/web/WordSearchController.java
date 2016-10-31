package com.mcapp.springapp.web;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.service.WordSearchService;

@Controller
public class WordSearchController {

	@Resource
	private WordSearchService srvWordSearch;
	
	@RequestMapping(value = "/wordsearch", method = RequestMethod.GET)
	public ModelAndView getWordSearchView (Model model) { 
        model.addAttribute("usuarioLogin", new UsuarioDto());
        model.addAttribute("sopa", new JSONObject(this.srvWordSearch.generateWordSearchPuzzle(10, 10)));
	    return new ModelAndView("wordsearch");
	}
}
