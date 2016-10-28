package com.mcapp.springapp.service;

import com.mcapp.springapp.repository.IdiomaDao;
import com.mcapp.springapp.service.interfaces.IIdiomaService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcapp.springapp.domain.Idioma;

@Service
public class IdiomaService implements IIdiomaService {

	@Resource
	private IdiomaDao daoIdioma;
	
	public List<Idioma> getIdiomas(){
		return this.daoIdioma.findAll();
	}
}
