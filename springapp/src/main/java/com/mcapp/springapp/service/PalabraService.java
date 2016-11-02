package com.mcapp.springapp.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcapp.springapp.common.dto.PalabraDto;
import com.mcapp.springapp.domain.Definicion;
import com.mcapp.springapp.repository.PalabraDao;
import com.mcapp.springapp.service.interfaces.IPalabraService;

@Service
public class PalabraService implements IPalabraService {
	
	@Resource
	private PalabraDao daoPalabra;
	
	public List<PalabraDto> getPalabrasDto() {
		 return this.daoPalabra.getPalabrasDto();
	}
	
	public Set<Definicion> getDefiniciones(int pk) {
		return this.daoPalabra.findByPk(pk).getDefiniciones();
	}
	
	public List<String> getPalabras() {
		return this.daoPalabra.getPalabras();
	}
	
	public List<PalabraDto> getPalabrasDtoByMaxLength(int longitud) {
		return this.daoPalabra.getPalabrasByMaxLength(longitud);
	}
	
	public String getDefinicion(String palabra) {
		return this.daoPalabra.getDefinicion(palabra);
	}

}
