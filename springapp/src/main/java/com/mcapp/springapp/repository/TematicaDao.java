package com.mcapp.springapp.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.mcapp.springapp.common.dto.TematicaDto;
import com.mcapp.springapp.domain.Tematica;

@Repository
public class TematicaDao extends AbstractDao<Tematica> {
	
	public TematicaDao() {
		super(Tematica.class);
	}
	
	public List<TematicaDto> getTematicasDto () {
		return this.findAll().stream().map(TematicaDto::toDto).collect(Collectors.toList());
	}
}