package com.mcapp.springapp.service.interfaces;

import java.util.List;

import com.mcapp.springapp.common.dto.FraseDto;
import com.mcapp.springapp.domain.Frase;

public interface IFraseService {
	
	public List<Frase> getFrases();
	
	public FraseDto getFraseByLength(int longitud);

}
