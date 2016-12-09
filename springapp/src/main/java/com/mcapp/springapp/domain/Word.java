package com.mcapp.springapp.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "word")
public class Word implements Serializable {

	private static final long serialVersionUID = -2767279015248091208L;

	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "word", nullable = false)
	private String word;
	
	@Column(name = "length", nullable = false)
	private int length;
	
	@Column(name = "withoutMarks", nullable = false)
	private String withoutMarks;

	@Column(name = "withoutMarksUpper", nullable = false)
	private String withoutMarksUpper;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "language", nullable = false)
	private Language language;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "word2definition", joinColumns = {
			@JoinColumn(name = "word", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "definition",
					nullable = false, updatable = false) })
    private Set<Definition> definitions;

    public Word(){
    	this.definitions = new HashSet<Definition>();
    }
    
    public Word(String word) {
    	this();
    	this.word = word;
    }
    public Word(String word, String definition) {
    	this(word);
    	this.definitions.add(new Definition(definition));
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getWithoutMarks() {
		return withoutMarks;
	}

	public void setWithoutMarks(String withoutMarks) {
		this.withoutMarks = withoutMarks;
	}

	public String getWithoutMarksUpper() {
		return withoutMarksUpper;
	}

	public void setWithoutMarksUpper(String withoutMarksUpper) {
		this.withoutMarksUpper = withoutMarksUpper;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Set<Definition> getDefinitions() {
		return definitions;
	}

	public void setDefinitions(Set<Definition> definitions) {
		this.definitions = definitions;
	}
    
}
