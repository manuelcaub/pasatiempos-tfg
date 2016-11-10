package com.mcapp.springapp.web;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
	
//	@RequestMapping(value = "/getcrosswords", method = RequestMethod.GET)
//	@ResponseBody
//	public CrosswordList getCrosswords (Principal principal) { 
//		CrosswordList list = new CrosswordList();
//		list.addAll(this.srvPuzzle.getCrosswordByUser(principal.getName()));
//        return list;
//	}

}
