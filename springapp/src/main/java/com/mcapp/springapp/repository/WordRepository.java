package com.mcapp.springapp.repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.domain.Word;

@Repository
public class WordRepository extends AbstractRepository<Word> {
	
	private Map<Integer, List<String>> mapWords;
	
	private List<String> words;
	
	public WordRepository() {
		super(Word.class);
	}
	
	@Transactional(readOnly = true)
	public Map<Integer, List<String>> getWordsOrderByLength() {
		if(this.mapWords == null) {
			this.mapWords = new HashMap<Integer, List<String>>();
			List<String> allWords = this.getWords();
			int maxLength = Collections.max(allWords, Comparator.comparing(s -> s.length())).length();
			for(int i = 1; i <= maxLength; i++) {
				final int auxLength = i;
				this.mapWords.put(i, allWords.stream().filter(x -> x.length() == auxLength).collect(Collectors.toList()));
			}
		}
		
		return this.mapWords;
	}
	
	@Transactional(readOnly = true)
	public List<String> getWords(){
		String sql = "SELECT w.withoutMarksUpper"+
		          " FROM   word w"+
		          " order by rand();";
		
		return (List<String>)this.hibernate().createNativeQuery(sql).getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<String> getWordsBetween(int min, int max){
		if (this.words == null) {
			String sql = "SELECT w.withoutMarksUpper"+
			          " FROM   word w"+
			          " WHERE w.length order by rand();";
			
			this.words = (List<String>)this.hibernate().createNativeQuery(sql).getResultList();
		} else {
			Collections.shuffle(this.words);
		}
		
		return this.words.stream().filter(x -> x.length() <= max && x.length() >= min).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public String getDefinition(String word) {
		String sql = "SELECT d.definition"+
		          " FROM   word p"+
		          "       JOIN word2definition pd ON p.id = pd.word"+
		          "       JOIN definition d ON d.id = pd.definition"+
		          " WHERE  p.word LIKE :word order by rand() limit 1;";
		
		return (String)this.hibernate().createNativeQuery(sql).setParameter("word", word).getResultList().stream().findFirst().orElse("");
	}

	@Transactional(readOnly = true)
	public Word getWordByValue(String word) {
		return (Word)this.hibernate().createQuery("from Word as p where p.word = :word").setParameter("word", word).getResultList().get(0);
	}
}
