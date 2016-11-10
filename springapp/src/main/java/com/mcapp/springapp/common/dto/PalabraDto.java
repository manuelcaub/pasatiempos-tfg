package com.mcapp.springapp.common.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.mcapp.springapp.domain.Word;

public class PalabraDto {

	private int id;
	
	private String valor;

	private int longitud;
	
	private String idioma;
    
    private List<String> definiciones;
    

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

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public List<String> getDefiniciones() {
		return definiciones;
	}

	public void setDefiniciones(List<String> definiciones) {
		this.definiciones = definiciones;
	}
	
}
