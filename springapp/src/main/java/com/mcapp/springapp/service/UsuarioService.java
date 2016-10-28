package com.mcapp.springapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.domain.Usuario;
import com.mcapp.springapp.repository.UsuarioDao;
import com.mcapp.springapp.service.interfaces.IUsuarioService;
import com.mcapp.springapp.service.validator.EmailExistsException;

@Service
public class UsuarioService implements IUsuarioService {
	
	@Resource
	private UsuarioDao daoUsuario;
	
	@Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public List<UsuarioDto> getUsuarios(){
		return this.daoUsuario.getUsuariosDto();
	}
	
	@Override
    public void guardar(UsuarioDto dto) {
		dto.setPassword(this.bCryptPasswordEncoder.encode(dto.getPassword()));
		Usuario user = new Usuario(dto);
        this.daoUsuario.saveOrUpdate(user);
    }

	@Override
	public UsuarioDto getUsuarioByEmail(String email) {
		return this.daoUsuario.getUsuarioByEmail(email);
	}
	
    @Override
    public UsuarioDto registrarUsuario(UsuarioDto dto) throws EmailExistsException {
         
        if (existeEmail(dto.getEmail())) {  
            throw new EmailExistsException ("La dirección de email ya existe: " +  dto.getEmail());
        }

		dto.setPassword(this.bCryptPasswordEncoder.encode(dto.getPassword()));
        this.daoUsuario.saveOrUpdate(new Usuario(dto)); 
        return dto;
    }
    
    private boolean existeEmail(String email) {        
        return this.daoUsuario.getUsuarioByEmail(email) != null;
    }
}
