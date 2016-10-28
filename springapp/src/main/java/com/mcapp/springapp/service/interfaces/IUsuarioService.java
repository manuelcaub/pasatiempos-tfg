package com.mcapp.springapp.service.interfaces;

import java.util.List;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.service.validator.EmailExistsException;

public interface IUsuarioService {

	public List<UsuarioDto> getUsuarios();
	
	public UsuarioDto getUsuarioByEmail(String email);

	public void guardar(UsuarioDto dto);

	public UsuarioDto registrarUsuario(UsuarioDto dto) throws EmailExistsException;
	
}
