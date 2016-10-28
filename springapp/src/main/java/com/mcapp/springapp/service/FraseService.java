package com.mcapp.springapp.service;

import com.mcapp.springapp.repository.FraseDao;
import com.mcapp.springapp.service.interfaces.IFraseService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcapp.springapp.common.dto.FraseDto;
import com.mcapp.springapp.domain.Frase;

@Service
public class FraseService implements IFraseService {

	@Resource
	private FraseDao daoFrase;
	
	public List<Frase> getFrases(){
		return this.daoFrase.findAll();
	}
	
	public FraseDto getFraseByLength(int longitud) {
		return this.daoFrase.getFraseByLength(longitud);
	}
}