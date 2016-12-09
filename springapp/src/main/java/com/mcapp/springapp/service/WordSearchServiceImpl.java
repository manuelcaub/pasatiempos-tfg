package com.mcapp.springapp.service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mcapp.springapp.common.dto.Direction;
import com.mcapp.springapp.common.dto.Position;
import com.mcapp.springapp.common.dto.QuoteDto;
import com.mcapp.springapp.common.dto.WordDto;
import com.mcapp.springapp.common.dto.WordSearch;
import com.mcapp.springapp.domain.Quote;
import com.mcapp.springapp.repository.QuoteRepository;
import com.mcapp.springapp.repository.WordRepository;
import com.mcapp.springapp.service.interfaces.WordSearchService;
import com.mcapp.springapp.websocket.WSServer;

@Service
public class WordSearchServiceImpl implements WordSearchService {
	
	private static final Logger logger = LoggerFactory.getLogger(WordSearchService.class);
	
	@Resource
	private WordRepository repWord;
	
	@Resource
	private QuoteRepository repQuote;
	
	private final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	
	private Random random;
	
	public WordSearchServiceImpl() {
		this.random = new Random();
	}
	
	public WordSearch generateWordSearchPuzzle(int size, int numWords, String sessionId) {
		List<String> words = this.repWord.getWordsBetween(3, size);
		WordSearch puzzle = new WordSearch(size);
		this.backtracking(puzzle, words, words, numWords, 0, sessionId);
		
		Quote quote = this.repQuote.getQuoteByLength(this.countEmpty(puzzle.getBoard()));
		if(quote == null) {
			this.fillBoard(puzzle);
		} else {
			QuoteDto quoteDto = new QuoteDto(quote);
			this.insertQuote(puzzle, quoteDto);
		}
		
		return puzzle;
	}
	
	private boolean backtracking(WordSearch puzzle, List<String> candidates, List<String> words, int numWords, int step, String sessionId) {
		
		// Caso base, si se han establecido todas las palabras indicadas.
		if (step == numWords)
			return true;

		// Se reordena la lista de posiciones para que las palabras se establezcan más desordenadas en el tablero
		Collections.shuffle(puzzle.getPositions());
		for (String candidate : candidates) {
			for (Direction dir : puzzle.getDirections()) {
				for (Position pos : puzzle.getPositions()) {
					if(this.isSolution(puzzle, pos.getRow(), pos.getColumn(), dir, candidate)) {
						WordDto word = new WordDto(candidate, pos, dir, candidate.length());
						puzzle.getWords().add(word);
						String previous = this.updateBoard(puzzle, pos.getRow(), pos.getColumn(), dir, candidate);
						this.sendWordSearch(puzzle, sessionId);
						if(this.backtracking(puzzle, words.parallelStream().filter(x -> puzzle.getWords().stream().allMatch(y -> !x.contains(y.getWord()) && !y.getWord().contains(x))).unordered().collect(Collectors.toList()), words, numWords, step + 1, sessionId)) {
							return true;
						}
						
						puzzle.getWords().remove(word);
						this.deleteWord(puzzle, pos.getRow(), pos.getColumn(), dir, previous);
					}
				}
			}
		}

		return false;
	}

	private void deleteWord(WordSearch puzzle, int row, int col, Direction dir, String previous) {
		Position pos = new Position(row, col);
		for (int i = 0; i < previous.length(); i++) {
			puzzle.getBoard()[pos.getRow()][pos.getColumn()] = previous.charAt(i);
			pos = move(pos, dir);
		}
	}

	private String updateBoard(WordSearch puzzle, int row, int col, Direction dir, String candidate) {
		Position pos = new Position(row, col);
		StringBuilder previous = new StringBuilder();
		for (int i = 0; i < candidate.length(); i++) {
			previous.append(puzzle.getBoard()[pos.getRow()][pos.getColumn()]);
			puzzle.getBoard()[pos.getRow()][pos.getColumn()] = candidate.charAt(i);
			pos = move(pos, dir);
		}
		
		return previous.toString();
	}

	private boolean isSolution(WordSearch puzzle, int row, int col, Direction dir, String candidate) {
		Position pos = new Position(row, col);
		
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
		case SE:
			return new Position(pos.getRow() - 1, pos.getColumn() + 1);
		case SW:
			return new Position(pos.getRow() - 1, pos.getColumn() - 1);
		case NE:
			return new Position(pos.getRow() + 1, pos.getColumn() + 1);
		case NW:
			return new Position(pos.getRow() + 1, pos.getColumn() - 1);
		case E:
			return new Position(pos.getRow(), pos.getColumn() + 1);
		case W:
			return new Position(pos.getRow(), pos.getColumn() - 1);
		case S:
			return new Position(pos.getRow() - 1, pos.getColumn());
		case N:
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
	
	private void insertQuote(WordSearch puzzle, QuoteDto quote) {
		puzzle.setQuoteDto(quote);
		List<Character> unorderedQuote = quote.getQuoteUpper().chars().mapToObj(e -> (char)e).collect(Collectors.toList());
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
	
	private void sendWordSearch(WordSearch wordsearch, String sessionId) {
		JSONObject jso = new JSONObject();
		jso.put("type",  "WORDSEARCH");
		jso.put("puzzle", new JSONObject(wordsearch));
		WSServer.send(sessionId, jso);
	}
}
