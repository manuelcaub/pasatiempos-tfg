package com.mcapp.springapp.common.dto;

import com.mcapp.springapp.domain.Frase;

public class FraseDto {

	private int id;
	
	private String valor;

	private int longitud;
	
	private String valorMayus;
	
	private String autor;
	
	public FraseDto(Frase frase) {
		this.id = frase.getId();
		this.valor = frase.getValor();
		this.longitud = frase.getLongitud();
		this.setAutor(frase.getAutor());
		this.valorMayus = frase.getFraseSoloLetrasMayus();
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

	public String getValorMayus() {
		return valorMayus;
	}

	public void setValorMayus(String valorMayus) {
		this.valorMayus = valorMayus;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
}
