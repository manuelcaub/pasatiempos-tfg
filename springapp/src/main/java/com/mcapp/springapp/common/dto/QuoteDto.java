package com.mcapp.springapp.common.dto;

import com.mcapp.springapp.domain.Quote;

public class QuoteDto {

	private int id;
	
	private String quote;

	private int length;
	
	private String quoteUpper;
	
	private String author;
	
	public QuoteDto () {
		
	}
	
	public QuoteDto (Quote quote) {
		this.id = quote.getId();
		this.quote = quote.getQuote();
		this.length = quote.getLength();
		this.quoteUpper = quote.getQuoteUpper();
		this.author = quote.getAuthor();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public int getLongitud() {
		return length;
	}

	public void setLongitud(int length) {
		this.length = length;
	}

	public String getQuoteUpper() {
		return quoteUpper;
	}

	public void setQuoteUpper(String quoteUpper) {
		this.quoteUpper = quoteUpper;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}
