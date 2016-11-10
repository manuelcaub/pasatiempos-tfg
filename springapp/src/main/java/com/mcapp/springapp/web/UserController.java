package com.mcapp.springapp.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mcapp.springapp.common.dto.UserDto;
import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.service.interfaces.UserService;
import com.mcapp.springapp.service.validator.EmailExistsException;

@Controller
public class UserController {
	
	@Resource
	private UserService srvUser;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView getRegistrationView (Model model) {
		model.addAttribute("usuarioRegistro", new UserDto());
		model.addAttribute("usuarioLogin", new UserDto());
	    return new ModelAndView("registration");
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registrarUser (@ModelAttribute("usuario") @Valid User user, BindingResult result, WebRequest request, Errors errors) {    
	    User registrado = new User();
	    if (!result.hasErrors()) {
	    	registrado = crearUser(user, result);
	    }
	    
	    if (registrado == null) {
	        result.rejectValue("email", "message.regError");
	    }
	    
	    return new ModelAndView("successRegister", "usuario", registrado);
	}
	
	private User crearUser(User user, BindingResult result) {
		User registered = null;
	    try {
	        registered = this.srvUser.registerUser(user);
	    } catch (EmailExistsException e) {
	        return null;
	    }    
	    
	    return registered;
	}
	
	
}
