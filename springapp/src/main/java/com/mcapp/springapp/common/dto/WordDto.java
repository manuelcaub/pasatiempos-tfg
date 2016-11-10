package com.mcapp.springapp.common.dto;

import org.json.JSONObject;

public class WordDto {
	private Position pos;
	private Direction direction;
	private String word;
	private int length;
	private String definition;

	public WordDto() {
	}
	
	public WordDto(String word, String definition) {
		this.word = word;
		this.definition = definition;
	}
	
	public WordDto(Position pos, Direction direction, int length) {
		this.pos = pos;
		this.direction = direction;
		this.length = length;
	}
	
	public WordDto(int row, int column, Direction direction, int length) {
		this.pos = new Position(row, column);
		this.direction = direction;
		this.length = length;
	}

	public Position getPos() {
		return this.pos;
	}
	
	public int getCol() {
		return this.pos.getColumn();
	}
	
	public int getRow() {
		return this.pos.getRow();
	}

	public Direction getDirection() {
		return this.direction;
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public int getLength() {
		return this.length;
	}
	
	public String toString(){
		return new JSONObject(this).toString();
	}
}
