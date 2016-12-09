package com.mcapp.springapp.web;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mcapp.springapp.common.dto.QuoteDto;
import com.mcapp.springapp.common.dto.WordDto;
import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.service.interfaces.QuoteService;
import com.mcapp.springapp.service.interfaces.WordService;

@Controller
public class DataController {
	
	@Resource
	private WordService srvWord;
	
	@Resource
	private QuoteService srvQuote;
	
	@RequestMapping(value = "/secure/data", method = RequestMethod.GET)
	public ModelAndView getDataView (Model model) { 
        model.addAttribute("usuarioLogin", new User());
	    return new ModelAndView("data");
	}
	
	@RequestMapping(value = "/secure/saveword", method = RequestMethod.POST)
	@ResponseBody
	public String saveWord (@RequestBody WordDto word) {
		if(word.getDefinition().trim().isEmpty()) {
			this.srvWord.saveWord(word.getWord());
		} else {
			this.srvWord.saveWord(word.getWord(), word.getDefinition());
		}
		
        return "success";
	}
	
	@RequestMapping(value = "/secure/savequote", method = RequestMethod.POST)
	@ResponseBody
	public String saveQuote (@RequestBody QuoteDto quote) {
		this.srvQuote.saveQuote(quote.getQuote(), quote.getAuthor());
        return "success";
	}
}
