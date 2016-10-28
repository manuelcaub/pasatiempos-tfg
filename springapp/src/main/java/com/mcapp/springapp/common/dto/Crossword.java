package com.mcapp.springapp.common.dto;

import java.util.List;

public class Crossword {
	private final int size;
	private char[][] board;
	private List<Word> boardWords;

	public Crossword(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public List<Word> getBoardWords() {
		return boardWords;
	}

	public void setBoardWords(List<Word> boardWords) {
		this.boardWords = boardWords;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}
	
	public String toString(){
		StringBuilder str = new StringBuilder();		
		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				str.append(this.board[i][j] + " ");
			}
			
			str.append("\n");
		}
		
		return str.toString();
	}
}
