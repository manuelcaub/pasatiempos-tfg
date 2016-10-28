package com.mcapp.springapp.repository;

import org.springframework.stereotype.Repository;

import com.mcapp.springapp.domain.Pais;

@Repository
public class PaisDao extends AbstractDao<Pais> {

	public PaisDao() {
		super(Pais.class);
	}
}
