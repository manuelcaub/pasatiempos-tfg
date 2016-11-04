package com.mcapp.springapp.web;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.common.dto.Word;
import com.mcapp.springapp.service.CrosswordService;
import com.mcapp.springapp.service.PalabraService;

@Controller
public class CrosswordController {
	
	@Resource
	private CrosswordService srvCrossword;
	
	@Resource
	private PalabraService srvPalabra;
	
	@RequestMapping(value = "/crossword", method = RequestMethod.GET)
	public ModelAndView getCrosswordView (Model model) { 
        model.addAttribute("usuarioLogin", new UsuarioDto());
	    return new ModelAndView("crossword");
	}
	
	@RequestMapping(value = "/newcrossword", method = RequestMethod.GET, params = {"size", "blacks"})
	@ResponseBody
	public String getNewCrossword (@RequestParam("size") int size, @RequestParam("blacks") int blacks) { 
        return new JSONObject(this.srvCrossword.generateCrossword(size, blacks)).toString();
	}
	
	@RequestMapping(value = "/savedefinition", method = RequestMethod.POST)
	@ResponseBody
	public String setDefinition (@RequestBody Word word) { 
        this.srvPalabra.setDefinition(word.getWord(), word.getDefinition());
        return "success";
	}
}
