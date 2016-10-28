package com.mcapp.springapp.repository;

import org.springframework.stereotype.Repository;

import com.mcapp.springapp.domain.Idioma;

@Repository
public class IdiomaDao extends AbstractDao<Idioma> {

	public IdiomaDao() {
		super(Idioma.class);
	}
}
