package com.mcapp.springapp.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.common.dto.PalabraDto;
import com.mcapp.springapp.domain.Palabra;

@Repository
public class PalabraDao extends AbstractDao<Palabra> {
	
	public PalabraDao() {
		super(Palabra.class);
	}
	
	public List<PalabraDto> getPalabrasDto(){
		return this.findAll().parallelStream().map(PalabraDto::new).collect(Collectors.toList());
	}
	
	public List<String> getPalabras(){
		return this.findAll().parallelStream().map(x -> x.getSinAcentosMayus()).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public List<PalabraDto> getPalabrasByMaxLength(int longitud) {
		@SuppressWarnings("unchecked")
		List<Palabra> palabras = (List<Palabra>) this.getSessionFactory().getCurrentSession()
				.createQuery("from Palabra as p left outer join fetch p.definiciones where p.longitud < :longitud")
				.setParameter("longitud", longitud).getResultList();
		
		return palabras.parallelStream().map(PalabraDto::new).collect(Collectors.toList());
	}
}
