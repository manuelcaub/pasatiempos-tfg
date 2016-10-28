package com.mcapp.springapp.service.validator;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.domain.Usuario;
import com.mcapp.springapp.service.interfaces.IUsuarioService;

@Component
public class UsuarioValidator implements Validator {
	
    @Resource
    private IUsuarioService srvUsuario;

    @Override
    public boolean supports(Class<?> aClass) {
        return Usuario.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UsuarioDto user = (UsuarioDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");        
        if (srvUsuario.getUsuarioByEmail(user.getEmail()) != null) {
            errors.rejectValue("mail", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
    }
}