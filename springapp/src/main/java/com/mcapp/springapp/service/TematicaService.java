package com.mcapp.springapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcapp.springapp.common.dto.TematicaDto;
import com.mcapp.springapp.repository.TematicaDao;

import com.mcapp.springapp.service.interfaces.ITematicaService;

@Service
public class TematicaService implements ITematicaService {
	
	@Resource
	private TematicaDao daoTematica;
	
	public List<TematicaDto> getTematicasDto(){
		return this.daoTematica.getTematicasDto();
	}
}