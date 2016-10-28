package com.mcapp.springapp.common.dto;

import com.mcapp.springapp.domain.Tematica;

public class TematicaDto {

	private int id;
	
	private String valor;
	
	public TematicaDto(Tematica tematica){
		this.id = tematica.getId();
		this.valor = tematica.getValor();
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
	
	public static TematicaDto toDto(Tematica tematica) {
		return new TematicaDto(tematica);
	}
	
}
