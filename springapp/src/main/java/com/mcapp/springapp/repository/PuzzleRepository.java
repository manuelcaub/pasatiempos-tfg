package com.mcapp.springapp.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mcapp.springapp.domain.Puzzle;

@Repository
public class PuzzleRepository extends AbstractRepository<Puzzle> {

	public PuzzleRepository() {
		super(Puzzle.class);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> getCrosswordsByUser(String user) {
		String query = "SELECT p.puzzle"+
		          " FROM   puzzle p JOIN user u ON p.user = u.id"+
		          " WHERE  (p.puzzle -> \"$.type\") = \"crossword\" AND u.email = \"ma@pasatiempos.com\";";
		
		return (List<String>)this.hibernate().createNativeQuery(query).getResultList();
	}
}
