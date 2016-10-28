package com.mcapp.springapp.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "frase")
public class Frase implements Serializable {

	private static final long serialVersionUID = -6558455919750985487L;

	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "valor", nullable = false)
	private String valor;
	
	@Column(name = "autor")
	private String autor;
	
	@Column(name = "fraseSoloLetrasMayus")
	private String fraseSoloLetrasMayus;
	
	@Column(name = "longitud", nullable = false)
	private int longitud;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idioma", nullable = false)
	private Idioma idioma;
    
    public Frase(){
    }

	public Frase(int id, String valor, String fraseSoloLetrasMayus, String autor, int longitud) {
		this.id = id;
		this.valor = valor;
		this.autor = autor;
		this.longitud = longitud;
		this.fraseSoloLetrasMayus = fraseSoloLetrasMayus;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getLongitud() {
		return longitud;
	}

	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}

	public String getFraseSoloLetrasMayus() {
		return fraseSoloLetrasMayus;
	}

	public void setFraseSoloLetrasMayus(String fraseSoloLetrasMayus) {
		this.fraseSoloLetrasMayus = fraseSoloLetrasMayus;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}
	
}
