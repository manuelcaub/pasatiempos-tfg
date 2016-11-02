package com.mcapp.springapp.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.service.CrosswordService;
import com.mcapp.springapp.service.WordSearchService;

@Controller
public class WelcomeController {

    @Resource
    private CrosswordService srvCrossword;
	
    @Resource
    private WordSearchService srvWordSearch;
    
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView getRegistrationView (Model model) { 
        model.addAttribute("usuarioLogin", new UsuarioDto());
	    return new ModelAndView("welcome");
	}
}
