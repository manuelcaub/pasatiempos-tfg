package com.mcapp.springapp.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "palabra")
public class Palabra implements Serializable {

	private static final long serialVersionUID = -2767279015248091208L;

	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "valor", nullable = false)
	private String valor;
	
	@Column(name = "longitud", nullable = false)
	private int longitud;
	
	@Column(name = "sin_acentos", nullable = false)
	private String sinAcentos;

	@Column(name = "sin_acentos_mayus", nullable = false)
	private String sinAcentosMayus;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idioma", nullable = false)
	private Idioma idioma;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "palabradefinicion", joinColumns = {
			@JoinColumn(name = "palabra", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "definicion",
					nullable = false, updatable = false) })
    private Set<Definicion> definiciones;

    public Palabra(){
    	this.definiciones = new HashSet<Definicion>();
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

	public Set<Definicion> getDefiniciones() {
		return definiciones;
	}

	public void setDefiniciones(Set<Definicion> definiciones) {
		this.definiciones = definiciones;
	}
	
	public String getSinAcentos() {
		return sinAcentos;
	}

	public void setSinAcentos(String sinAcentos) {
		this.sinAcentos = sinAcentos;
	}

	public String getSinAcentosMayus() {
		return sinAcentosMayus;
	}

	public void setSinAcentosMayus(String sinAcentosMayus) {
		this.sinAcentosMayus = sinAcentosMayus;
	}
}
