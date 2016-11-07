package com.mcapp.springapp.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.service.interfaces.ICrosswordService;
import com.mcapp.springapp.service.interfaces.IWordSearchService;

@Controller
public class MyPuzzlesController {
	
	@Resource
	private IWordSearchService srvWordSearch;
	
	@Resource
	private ICrosswordService srvCrossword;
	
	@RequestMapping(value = "/mypuzzles", method = RequestMethod.GET)
	public ModelAndView getMyPuzzlesView (Model model) {
		model.addAttribute("usuarioLogin", new UsuarioDto());
		return new ModelAndView("mypuzzles");
	}

}
