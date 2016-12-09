package com.mcapp.springapp.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.service.interfaces.CrosswordService;
import com.mcapp.springapp.service.interfaces.WordSearchService;

@Controller
public class WelcomeController {

    @Resource
    private CrosswordService srvCrossword;
	
    @Resource
    private WordSearchService srvWordSearch;
    
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView getRegistrationView (Model model) { 
        model.addAttribute("usuarioLogin", new User());
	    return new ModelAndView("welcome");
	}
	
	@RequestMapping(value ="/legalinfo", method = RequestMethod.GET)
	public ModelAndView getLegalInfoView (Model model) {
		model.addAttribute("usuarioLogin", new User());
		return new ModelAndView("legalinfo");
	}
}
