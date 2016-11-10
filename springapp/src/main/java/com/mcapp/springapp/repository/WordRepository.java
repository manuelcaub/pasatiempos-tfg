package com.mcapp.springapp.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.domain.Word;

@Repository
public class WordRepository extends AbstractRepository<Word> {
	
	public WordRepository() {
		super(Word.class);
	}
	
	@Transactional(readOnly = true)
	public Map<Integer, List<String>> getWordsOrderByLength(int maxLength) {
		Map<Integer, List<String>> mapWords = new HashMap<Integer, List<String>>();
		for(int i = 1; i <= maxLength; i++) {
			mapWords.put(i, this.getWords(i));
		}
		
		return mapWords;
		
	}
	
	@Transactional(readOnly = true)
	public List<String> getWords(int length){
		String sql = "SELECT w.withoutMarksUpper"+
		          " FROM   word w"+
		          " WHERE  w.length = :length order by rand() LIMIT 100;";
		
		return (List<String>)this.hibernate().createNativeQuery(sql).setParameter("length", length).getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Word> getWordsByMaxLength(int length) {
		@SuppressWarnings("unchecked")
		List<Word> words = (List<Word>) this.hibernate()
				.createQuery("from Word as p where p.length < :length")
				.setParameter("length", length).getResultList();
		
		return words;
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
