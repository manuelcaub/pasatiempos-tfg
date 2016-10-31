package com.mcapp.springapp.web;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.service.CrosswordService;

@Controller
public class CrosswordController {
	
	@Resource
	private CrosswordService srvCrossword;
	
	@RequestMapping(value = "/crossword", method = RequestMethod.GET)
	public ModelAndView getCrosswordView (Model model) { 
        model.addAttribute("usuarioLogin", new UsuarioDto());
        model.addAttribute("crucigrama", new JSONObject(this.srvCrossword.generateCrossword(4, 20)));
	    return new ModelAndView("crossword");
	}
}
