package com.mcapp.springapp.common.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Email;

import com.mcapp.springapp.domain.Usuario;

public class UsuarioDto {

	private int id;
	
	private String nombre;
	
	private String pais;
	
	@Email
	private String email;
	
	private String password;
	
	private String passwordConfirm;
	
	private List<String> roles;
	
	public UsuarioDto(Usuario usuario){
		this.id = usuario.getId();
		this.nombre = usuario.getNombre();
		this.email = usuario.getEmail();
		this.password = usuario.getPassword();
		this.setRoles(usuario.getRoles().stream().map(x -> x.getNombre()).collect(Collectors.toList()));
	}
	
	public UsuarioDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
