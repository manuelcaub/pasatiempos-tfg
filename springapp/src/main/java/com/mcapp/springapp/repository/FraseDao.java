package com.mcapp.springapp.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.common.dto.FraseDto;
import com.mcapp.springapp.domain.Frase;

@Repository
public class FraseDao extends AbstractDao<Frase> {

	public FraseDao() {
		super(Frase.class);
	}
	
	@Transactional(readOnly = true)
	public FraseDto getFraseByLength(int longitud) {
		@SuppressWarnings("unchecked")
		List<Frase> frases = (List<Frase>) this.getSessionFactory().getCurrentSession()
				.createQuery("from Frase as f where f.longitud = :longitud")
				.setParameter("longitud", longitud).getResultList();
		
		return frases.parallelStream().map(FraseDto::new).unordered().findAny().orElse(null);
	}
}
