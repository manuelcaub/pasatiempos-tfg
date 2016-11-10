package com.mcapp.springapp.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.domain.Quote;

@Repository
public class QuoteRepository extends AbstractRepository<Quote> {

	public QuoteRepository() {
		super(Quote.class);
	}
	
	@Transactional(readOnly = true)
	public Quote getQuoteByLength(int length) {
		@SuppressWarnings("unchecked")
		List<Quote> quotes = (List<Quote>) this.getSessionFactory().getCurrentSession()
				.createQuery("from Quote as f where f.length = :length")
				.setParameter("length", length).getResultList();
		
		// TODO Cambiar orElse(null) para evitar nulos
		return quotes.parallelStream().unordered().findAny().orElse(null);
	}
}
