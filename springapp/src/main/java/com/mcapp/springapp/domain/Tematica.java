package com.mcapp.springapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tematica" )
public class Tematica implements Serializable {

	private static final long serialVersionUID = -423477591336985025L;

	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "valor", nullable = false)
	private String valor;

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
	
}
