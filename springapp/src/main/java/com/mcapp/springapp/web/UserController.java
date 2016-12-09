package com.mcapp.springapp.web;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcapp.springapp.common.dto.SimpleUserDto;
import com.mcapp.springapp.common.dto.UserDto;
import com.mcapp.springapp.domain.User;
import com.mcapp.springapp.service.interfaces.UserService;
import com.mcapp.springapp.service.validator.EmailExistsException;
import com.mcapp.springapp.service.validator.RoleInUserExistException;

@Controller
public class UserController {
	
	@Resource
	private UserService srvUser;
	
	@RequestMapping(value = "/secure/getusers", method = RequestMethod.GET)
	public @ResponseBody String getUsers(Principal principal) throws JsonProcessingException {
		List<SimpleUserDto> users = this.srvUser.getSimpleUsersExceptAdmin();
		return new ObjectMapper().writeValueAsString(users);
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView getRegistrationView (Model model) {
		model.addAttribute("usuarioRegistro", new UserDto());
		model.addAttribute("usuarioLogin", new UserDto());
	    return new ModelAndView("registration");
	}
	
	@RequestMapping(value = "/secure/users", method = RequestMethod.GET)
	public ModelAndView getUsersView (Model model) {
        model.addAttribute("usuarioLogin", new User());
	    return new ModelAndView("users");
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
	
	@RequestMapping(value = "/secure/removeuser", method = RequestMethod.POST)
	public @ResponseBody String removePuzzle (@RequestBody String user) { 
		this.srvUser.removeUser(user);
		return "success";
	}
	
	@RequestMapping(value = "/secure/setcollaborator", method = RequestMethod.POST)
	public @ResponseBody String setCollaborator (@RequestBody String user) { 
		try {
			this.srvUser.setCollaborator(user);
		} catch (RoleInUserExistException e) {
			return "exists";
		}
		
		return "success";
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
