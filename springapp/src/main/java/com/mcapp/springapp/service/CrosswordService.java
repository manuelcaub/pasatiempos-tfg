package com.mcapp.springapp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mcapp.springapp.common.dto.Crossword;
import com.mcapp.springapp.common.dto.Direction;
import com.mcapp.springapp.common.dto.Position;
import com.mcapp.springapp.common.dto.Word;
import com.mcapp.springapp.repository.PalabraDao;
import com.mcapp.springapp.service.interfaces.ICrosswordService;

@Service
public class CrosswordService implements ICrosswordService {

	@Resource
	private PalabraDao daoPalabra;
	
	public final char WHITE = '.';
	public final char BLACK = '-';
	
	/**
	 * Se encarga de realizar las operaciones necesarias para generar el crucigrama dado un tamaño y un porcentaje de espacios en negro.
	 * @param size Tamaño de los lados del crucigrama.
	 * @param blacks Porcentaje de espacios en negro.
	 * 
	 */
	public Crossword generateCrossword(int size, int blacks) {
		Crossword crossword = new Crossword(size);
		crossword.setBoard(this.initializeBoard(size, blacks));
		crossword.setBoardWords(this.initializeGaps(crossword.getBoard()));
		List<String> words = this.daoPalabra.getPalabrasDto().parallelStream().map(x -> x.getValor()).collect(Collectors.toList());
		Collections.shuffle(words);

		if(this.backtracking(crossword, words)){
			return crossword;
		}
		
		return null;
	}
	
	/**
	 * Inicializa el tablero con el tamaño y el porcentaje de espacios en negro dados.
	 * @param size Tamaño del lado del tablero.
	 * @param black Porcentaje de espacios en negro del tablero.
	 **/
	private char[][] initializeBoard(int size, int black) {
		final char[][] board = new char[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = WHITE;
			}
		}

		final int blacks = size * size * black / 100;
		for (int i = 0; i < blacks; i++) {
			int row, column;
			do {
				row = (int) (Math.random() * size);
				column = (int) (Math.random() * size);
			} while (board[row][column] == BLACK);

			board[row][column] = BLACK;
		}

		return board;
	}
	
	/**
	 * Inicializa los huecos del tablero.
	 * @param board Tablero del crucigrama
	 * @return Devuelve la lista de huecos del tablero.
	 **/
	private ArrayList<Word> initializeGaps(char[][] board) {
		final ArrayList<Word> words = new ArrayList<>();

		// Horizontal words
		for (int row = 0; row < board.length; row++) {
			int length = 0;
			for (int col = 0; col < board.length; col++) {
				if (board[row][col] == WHITE) {
					length++;
					if (col == board.length - 1) {
						words.add(new Word(row, col - (length - 1), Direction.Horizontal, length));
						length = 0;
					}
				} else if (board[row][col] == BLACK && length > 0) {
					words.add(new Word(row, col - length, Direction.Horizontal, length));
					length = 0;
				}
			}
		}

		// Vertical words
		for (int col = 0; col < board.length; col++) {
			int length = 0;
			for (int row = 0; row < board.length; row++) {
				if (board[row][col] == WHITE) {
					if((row != board.length - 1 && board[row + 1][col] != BLACK) || (row != 0 && board[row - 1][col] != BLACK) || (col != 0 && board[row][col - 1] != BLACK) || (col != board.length - 1 && board[row][col + 1] != BLACK))
					{					
						length++;
						if (row == board.length - 1) {
							words.add(new Word(row - (length - 1), col, Direction.Vertical, length));
							length = 0;
						}
					}
				} else if (board[row][col] == BLACK && length > 0) {
					words.add(new Word(row - length, col, Direction.Vertical, length));
					length = 0;
				}
			}
		}

		return words;
	}
	
	/**
	 * Método que realiza la búsqueda mediante backtracking de las palabras que rellenarán los huecos del crucigrama.
	 * @param crossword Crucigrama a rellenar.
	 * @param words Lista de palabras candidatas.
	 **/
	private boolean backtracking(Crossword crossword, List<String> words) {
		Word gap = this.getFirstGap(crossword.getBoardWords());
		if (gap == null)
			return true;

		for (final String word : words.parallelStream().filter(x -> x.length() == gap.getLength() && crossword.getBoardWords().parallelStream().allMatch(y -> !x.equals(y.getWord()))).collect(Collectors.toList())) {
			if (this.esSolucion(gap, word, words, crossword)) {
				gap.setWord(word);
				this.updateBoard(crossword.getBoard(), gap.getPos(), word);
				if (this.backtracking(crossword, words))
					return true;

				gap.setWord(null);
				this.deleteWord(crossword.getBoard(), gap.getPos(), word);
			}
		}

		return false;
	}
	
	/**
	 * Comprueba si puede añadirse la palabra al hueco estudiado.
	 * @param gap Hueco que se está estudiando.
	 * @param candidate Palabra candidata a ocupar el hueco.
	 * @param words Lista de palabras.
	 * @param crossword Crucigrama que se está generando.
	 * @return Devuelve 'true' si puede añadirse la palabra candidata al hueco, en caso contrario devuelve false.
	 **/
	private boolean esSolucion(Word gap, String candidate, List<String> words, Crossword crossword) {

		for (int j = gap.getPos().getColumn(), k = 0; j < gap.getPos().getColumn() + gap.getLength(); j++, k++) {
			final int auxj = j;
			final int auxk = k;
			
			// Si no está libre y no coincide
			if (crossword.getBoard()[gap.getPos().getRow()][j] != WHITE && candidate.charAt(k) != crossword.getBoard()[gap.getPos().getRow()][j]) {
				return false;
			}

			// Si hay intersección
			if ((gap.getRow() > 0 && crossword.getBoard()[gap.getRow() - 1][j] != BLACK) || (gap.getRow() < crossword.getSize() - 1 && crossword.getBoard()[gap.getRow() + 1][j] != BLACK)) {
				final Word interseccion = crossword.getBoardWords().parallelStream().filter(x -> x.isVertical() && x.getCol() == auxj && x.getRow() <= gap.getRow() && x.getRow() + x.getLength() >= gap.getRow()).findFirst().get();
				StringBuilder palabraParcial = new StringBuilder();
				for(int posicionInterseccion = interseccion.getRow(); posicionInterseccion < gap.getRow(); posicionInterseccion++) {
					palabraParcial.append(crossword.getBoard()[posicionInterseccion][interseccion.getCol()]);
				}
				
				palabraParcial.append(candidate.charAt(auxk));
				if(gap.getRow() < crossword.getSize() - 1 && crossword.getBoard()[gap.getRow() + 1][j] != BLACK) {
					palabraParcial.append(String.format(".{%d}", interseccion.getLength() - palabraParcial.length()));
				}

				
				final String parcial = palabraParcial.toString();
				if (!words.parallelStream().anyMatch(x -> !x.equals(candidate) && x.length() == interseccion.getLength() && x.matches(parcial)))
					return false;
			}
		}

		return true;
	}
	
	private void updateBoard(char[][] board, Position pos, String candidate) {
		Position auxPos = new Position(pos.getRow(), pos.getColumn());
		for(int i = 0; i < candidate.length(); i++) {
			board[auxPos.getRow()][auxPos.getColumn()] = candidate.charAt(i);
			auxPos = move(auxPos);
		}
	}
	
	private void deleteWord(char[][] board, Position pos, String word) {
		Position auxPos = new Position(pos.getRow(), pos.getColumn());
		for (int i = 0; i < word.length(); i++) {
			board[auxPos.getRow()][auxPos.getColumn()] = WHITE;
			auxPos = move(auxPos);
		}
	}
	
	private Position move(Position pos) {
		return new Position(pos.getRow(), pos.getColumn() + 1);
	}
	
	/**
	 * Devuelve el primer hueco libre horizontal en el tablero.
	 * @param words Lista de huecos de palabras del tablero
	 * @return Devuelve el primer hueco libre, es decir, el primer hueco horizontal que no tenga palabra asignada.
	 */
	private Word getFirstGap(List<Word> words) {
		return words.parallelStream().filter(x -> x.isHorizontal() && x.getWord() == null).findFirst().orElse(null);
	}
}
