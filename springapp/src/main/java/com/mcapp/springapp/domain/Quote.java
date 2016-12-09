package com.mcapp.springapp.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "quote")
public class Quote implements Serializable {

	private static final long serialVersionUID = -6558455919750985487L;

	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "quote", nullable = false)
	private String quote;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "quoteUpper")
	private String quoteUpper;
	
	@Column(name = "length", nullable = false)
	private int length;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language", nullable = false)
	private Language language;
    
    public Quote(){
    }
    
    public Quote(String quote, String author) {
    	this.quote = quote;
    	this.author = author;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getQuoteUpper() {
		return quoteUpper;
	}

	public void setQuoteUpper(String quoteUpper) {
		this.quoteUpper = quoteUpper;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
	
}
