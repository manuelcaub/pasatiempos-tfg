package com.mcapp.springapp.service.interfaces;

import java.util.List;

import com.mcapp.springapp.domain.Quote;

public interface QuoteService {
	
	public List<Quote> getQuotes();
	
	public Quote getQuoteByLength(int longitud);

	public void saveQuote(String quote, String author);
}
