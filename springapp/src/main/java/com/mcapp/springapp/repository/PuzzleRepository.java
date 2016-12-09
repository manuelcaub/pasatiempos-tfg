package com.mcapp.springapp.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.mcapp.springapp.domain.Puzzle;

@Repository
public class PuzzleRepository extends AbstractRepository<Puzzle> {

	@Resource
	private UserRepository repUser;
	
	public PuzzleRepository() {
		super(Puzzle.class);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getPuzzlesByUser(String email) {
		String query = String.format("SELECT p.puzzle"+
		          " FROM   puzzle p JOIN user u ON p.user = u.id"+
		          " WHERE  u.email = \"%s\" ;", email) ;
		
		
		return "[" + StringUtils.collectionToCommaDelimitedString((List<String>)this.hibernate().createNativeQuery(query).getResultList()) + "]";
	}

	@Transactional
	public void removeByPuzzle(String jsonPuzzle, String email) {		
		@SuppressWarnings("unchecked")
		List<Puzzle> puzzles = (List<Puzzle>) this.getSessionFactory().getCurrentSession()
				.createQuery("from Puzzle as p where p.puzzle = :puzzle and p.user.email = :email")
				.setParameter("puzzle", jsonPuzzle).setParameter("email", email).getResultList();
		
		if(!puzzles.isEmpty()) {
			this.hibernate().remove(puzzles.get(0));
		}
	}
}
