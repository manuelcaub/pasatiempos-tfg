package com.mcapp.springapp.service.interfaces;

import java.util.List;
import java.util.Set;

import com.mcapp.springapp.common.dto.PalabraDto;
import com.mcapp.springapp.domain.Definicion;

public interface IPalabraService {

	public List<PalabraDto> getPalabrasDto();
	
	public Set<Definicion> getDefiniciones(int pk);
	
	public List<String> getPalabras();
	
	public List<PalabraDto> getPalabrasDtoByMaxLength(int longitud);
}
