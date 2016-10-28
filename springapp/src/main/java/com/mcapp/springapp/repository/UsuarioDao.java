package com.mcapp.springapp.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.common.dto.UsuarioDto;
import com.mcapp.springapp.domain.Usuario;

@Repository
public class UsuarioDao extends AbstractDao<Usuario> {
	
	public UsuarioDao() {
		super(Usuario.class);
	}
	
	public List<UsuarioDto> getUsuariosDto() {
		return this.findAll().stream().map(UsuarioDto::new).collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public UsuarioDto getUsuarioByEmail(String email) {
		Optional<Usuario> usuario = this.getSessionFactory().getCurrentSession()
										.createQuery("from Usuario as u left outer join fetch u.roles where u.email = :email")
										.setParameter("email", email)
										.uniqueResultOptional();
		if(!usuario.isPresent() || !usuario.get().getClass().equals(Usuario.class)) {
			return null;
		}
		
		return new UsuarioDto(usuario.get());
	}
}