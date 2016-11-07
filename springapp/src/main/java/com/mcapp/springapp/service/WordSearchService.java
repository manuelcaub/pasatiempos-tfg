package com.mcapp.springapp.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcapp.springapp.common.dto.Direction;
import com.mcapp.springapp.common.dto.FraseDto;
import com.mcapp.springapp.common.dto.Position;
import com.mcapp.springapp.common.dto.WordSearch;
import com.mcapp.springapp.repository.FraseDao;
import com.mcapp.springapp.repository.PalabraDao;
import com.mcapp.springapp.service.interfaces.IWordSearchService;

@Service
public class WordSearchService implements IWordSearchService {
	
	@Resource
	private PalabraDao daoPalabra;
	
	@Resource
	private FraseDao daoFrase;
	
	private final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	private Random random;
	
	public WordSearchService() {
		this.random = new Random();
	}
	
	public WordSearch generateWordSearchPuzzle(int size, int numWords) {
		List<String> words = this.daoPalabra.getPalabrasByMaxLength(size).parallelStream().map(x -> x.getValor()).collect(Collectors.toList());
		Collections.shuffle(words);
		WordSearch puzzle = new WordSearch(size);
		this.backtracking(puzzle, words, numWords, 0);
		
		FraseDto quote = this.daoFrase.getFraseByLength(this.countEmpty(puzzle.getBoard()));		

		if(quote == null) {
			this.fillBoard(puzzle);
		} else {
			this.insertQuote(puzzle, quote);
		}
		
		return puzzle;
	}
	
	private boolean backtracking(WordSearch puzzle, List<String> words, int numWords, int step) {
		// Se escoge una palabra candidata que no se haya 
		String candidate = words.parallelStream().filter(x -> !puzzle.getWords().contains(x)).unordered().findAny().get();
		
		// Caso base, si se han establecido todas las palabras indicadas.
		if (step == numWords)
			return true;

		// Se reordena la lista de posiciones para que las palabras se establezcan más desordenadas en el tablero
		Collections.shuffle(puzzle.getPositions());
		for (Position pos : puzzle.getPositions()) {
			// Se reordena la lista de direcciones para que se estudien aleatoriamente.
			Collections.shuffle(puzzle.getDirections());
			for (Direction dir : puzzle.getDirections()) {
				if(this.isSolution(puzzle, pos, dir, candidate)) {
					puzzle.getWords().add(candidate);
					String previous = this.updateBoard(puzzle, pos, dir, candidate);
					if(this.backtracking(puzzle, words, numWords, step + 1)) {
						return true;
					}
					
					puzzle.getWords().remove(candidate);
					this.deleteWord(puzzle, pos, dir, previous);
				}
			}
		}

		return false;
	}

	private void deleteWord(WordSearch puzzle, Position pos, Direction dir, String previous) {
		for (int i = 0; i < previous.length(); i++) {
			puzzle.getBoard()[pos.getRow()][pos.getColumn()] = previous.charAt(i);
			pos = move(pos, dir);
		}
	}

	private String updateBoard(WordSearch puzzle, Position pos, Direction dir, String candidate) {
		StringBuilder previous = new StringBuilder();
		for (int i = 0; i < candidate.length(); i++) {
			previous.append(puzzle.getBoard()[pos.getRow()][pos.getColumn()]);
			puzzle.getBoard()[pos.getRow()][pos.getColumn()] = candidate.charAt(i);
			pos = move(pos, dir);
		}
		
		return previous.toString();
	}

	private boolean isSolution(WordSearch puzzle, Position pos, Direction dir, String candidate) {
		for(int i = 0; i < candidate.length(); i++)
		{
			if(pos.getRow() >= puzzle.getSize() || pos.getColumn() >= puzzle.getSize() || pos.getRow() < 0 || pos.getColumn() < 0 
					|| (puzzle.getBoard()[pos.getRow()][pos.getColumn()] != '-' && puzzle.getBoard()[pos.getRow()][pos.getColumn()] != candidate.charAt(i)))
			{
				return false;
			}
			
			pos = move(pos, dir);
		}
		
		return true;
	}
	
	private Position move(Position pos, Direction dir) {
		switch(dir) {
		case DiagonalDown:
			return new Position(pos.getRow() - 1, pos.getColumn() + 1);
		case DiagonalDownLeft:
			return new Position(pos.getRow() - 1, pos.getColumn() - 1);
		case DiagonalUp:
			return new Position(pos.getRow() + 1, pos.getColumn() + 1);
		case DiagonalUpLeft:
			return new Position(pos.getRow() + 1, pos.getColumn() - 1);
		case Horizontal:
			return new Position(pos.getRow(), pos.getColumn() + 1);
		case HorizontalB:
			return new Position(pos.getRow(), pos.getColumn() - 1);
		case Vertical:
			return new Position(pos.getRow() - 1, pos.getColumn());
		case VerticalB:
			return new Position(pos.getRow() + 1, pos.getColumn());
		default:
			return null;
		}
	}
	
	private int countEmpty(char[][] board) {
		int count = 0;
		for(int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '-') {
					count++;
				}
			}
		}
		
		return count;		
	}
	
	private void insertQuote(WordSearch puzzle, FraseDto quote) {
		puzzle.setQuote(quote);
		List<Character> unorderedQuote = quote.getValorMayus().chars().mapToObj(e -> (char)e).collect(Collectors.toList());
		Collections.shuffle(unorderedQuote);
		Iterator<Character> it = unorderedQuote.iterator();
		for (int i = 0; i < puzzle.getSize() && it.hasNext(); i++) {
			for (int j = 0; j < puzzle.getSize() && it.hasNext(); j++) {
				if(puzzle.getBoard()[i][j] == '-') {
					puzzle.getBoard()[i][j] = it.next();
				}
			}
		}
	}
	
	private void fillBoard(WordSearch puzzle) {
		for (int i = 0; i < puzzle.getSize(); i++) {
			for (int j = 0; j < puzzle.getSize(); j++) {
				if(puzzle.getBoard()[i][j] == '-') {
					puzzle.getBoard()[i][j] = ALPHABET[this.random.nextInt(ALPHABET.length)];
				}
			}
		}
	}
}
