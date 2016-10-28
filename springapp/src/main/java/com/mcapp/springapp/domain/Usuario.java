package com.mcapp.springapp.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mcapp.springapp.common.dto.UsuarioDto;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -7983219529059769019L;
	
	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue
	private int id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "fechaDeAlta")
	private Timestamp fechaDeAlta;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pais", nullable = false)
    private Pais pais;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuariorol", joinColumns = @JoinColumn(name = "usuario"), inverseJoinColumns = @JoinColumn(name = "rol"))
    private Set<Rol> roles;

    public Usuario(){
    }
    
    public Usuario(UsuarioDto dto){
    	this.nombre = dto.getNombre();
    	this.email = dto.getEmail();
    	this.password = dto.getPassword();
    }
    
	public Usuario(int id, String nombre, String apellido1, String apellido2, String email, String password, Pais pais) {
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.pais = pais;
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

	public Timestamp getFechaDeAlta() {
		return fechaDeAlta;
	}

	public void setFechaDeAlta(Timestamp fechaDeAlta) {
		this.fechaDeAlta = fechaDeAlta;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

}
