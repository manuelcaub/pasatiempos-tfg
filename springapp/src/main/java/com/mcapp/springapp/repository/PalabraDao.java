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
		List<Palabra> palabras = (List<Palabra>) this.hibernate()
				.createQuery("from Palabra as p where p.longitud < :longitud")
				.setParameter("longitud", longitud).getResultList();
		
		return palabras.parallelStream().map(PalabraDto::new).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public String getDefinicion(String palabra) {
		String sql = "SELECT d.valor"+
		          " FROM   palabra p"+
		          "       JOIN palabradefinicion pd ON p.id = pd.palabra"+
		          "       JOIN definicion d ON d.id = pd.definicion"+
		          " WHERE  p.valor LIKE :palabra order by rand() limit 1;";
		
		return (String)this.hibernate().createNativeQuery(sql).setParameter("palabra", palabra).getResultList().stream().findFirst().orElse(null);
	}

	@Transactional(readOnly = true)
	public Palabra getPalabraByValue(String palabra) {
		return (Palabra)this.hibernate().createQuery("from Palabra as p where p.valor = :palabra").setParameter("palabra", palabra).getResultList().get(0);
	}
}
