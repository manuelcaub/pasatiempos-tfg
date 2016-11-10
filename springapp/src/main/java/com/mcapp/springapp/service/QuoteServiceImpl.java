package com.mcapp.springapp.service;

import com.mcapp.springapp.repository.QuoteRepository;
import com.mcapp.springapp.service.interfaces.QuoteService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcapp.springapp.domain.Quote;

@Service
public class QuoteServiceImpl implements QuoteService {

	@Resource
	private QuoteRepository repQuote;
	
	public List<Quote> getQuotes(){
		return this.repQuote.findAll();
	}
	
	public Quote getQuoteByLength(int longitud) {
		return this.repQuote.getQuoteByLength(longitud);
	}
}