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

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.service.interfaces.IUsuarioService;
import com.mcapp.springapp.service.validator.EmailExistsException;

@Controller
public class UsuarioController {
	
	@Resource
	private IUsuarioService srvUsuario;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView getRegistrationView (Model model) {
		model.addAttribute("usuarioRegistro", new UsuarioDto());
		model.addAttribute("usuarioLogin", new UsuarioDto());
	    return new ModelAndView("registration");
	}
	
	@ModelAttribute("usuarioLogin")
	public UsuarioDto getUsuarioVacio() {
		return new UsuarioDto();
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView registrarUsuario (@ModelAttribute("usuario") @Valid UsuarioDto dto, BindingResult result, WebRequest request, Errors errors) {    
	    UsuarioDto registrado = new UsuarioDto();
	    if (!result.hasErrors()) {
	    	registrado = crearUsuario(dto, result);
	    }
	    
	    if (registrado == null) {
	        result.rejectValue("email", "message.regError");
	    }
	    
	    return new ModelAndView("successRegister", "usuario", registrado);
	}
	
	private UsuarioDto crearUsuario(UsuarioDto dto, BindingResult result) {
		UsuarioDto registered = null;
	    try {
	        registered = this.srvUsuario.registrarUsuario(dto);
	    } catch (EmailExistsException e) {
	        return null;
	    }    
	    
	    return registered;
	}
	
	
}
