package com.mcapp.springapp.common.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSearch {
	
	private final int size;
	
	private char[][] board;

	private List<Position> positions;
	
	private final List<Direction> directions;
	
	private List<String> words;
	
	private QuoteDto quote;
	
	private final char EMPTY = '-';
	
	public WordSearch(int size, List<Direction> directions) {
		this.size = size;
		this.words = new ArrayList<String>();
		this.initializeBoard();
		
		if(directions == null) {
			this.directions = Arrays.asList(Direction.values());
		} else {
			this.directions = directions;
		}
	}
	
	public WordSearch(int size) {
		this(size, null);
	}
	
	private void initializeBoard() {
		this.positions = new ArrayList<Position>();
		this.board = new char[this.size][this.size];
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size; j++) {
				this.positions.add(new Position(i, j));
				this.board[i][j] = EMPTY;
			}
		}
	}
	
	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public List<Direction> getDirections() {
		return directions;
	}

	public List<String> getWords() {
		return words;
	}

	public int getSize() {
		return size;
	}

	public QuoteDto getQuoteDto() {
		return quote;
	}

	public void setQuoteDto(QuoteDto quote) {
		this.quote = quote;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				str.append(this.board[i][j] + " ");
			}
			
			str.append('\n');
		}
		
		str.append('\n');
		for(String word : this.words) {
			str.append(word + '\n');
		}
		
		if(this.getQuoteDto() != null) {
			str.append('\n' + this.getQuoteDto().getQuote());
		}
		
		return str.toString();
	}
}
