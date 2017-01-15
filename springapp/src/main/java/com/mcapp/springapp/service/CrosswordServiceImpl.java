 package com.mcapp.springapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.mcapp.springapp.common.dto.Crossword;
import com.mcapp.springapp.common.dto.Direction;
import com.mcapp.springapp.common.dto.Position;
import com.mcapp.springapp.common.dto.WordDto;
import com.mcapp.springapp.repository.WordRepository;
import com.mcapp.springapp.service.interfaces.CrosswordService;
import com.mcapp.springapp.websocket.WSServer;

@Service
public class CrosswordServiceImpl implements CrosswordService {

	@Resource
	private WordRepository repWord;
	
	public final char WHITE = '.';
	public final char BLACK = '-';
	
	private int count = 0;
	
	/**
	 * Se encarga de realizar las operaciones necesarias para generar el crucigrama dado un tamaño y un porcentaje de espacios en negro.
	 * @param size Tamaño de los lados del crucigrama.
	 * @param blacks Porcentaje de espacios en negro.
	 * 
	 */
	public Crossword generateCrossword(int sizeSide, int blackPercentage, String sessionId) {
		Crossword crossword = new Crossword(sizeSide);
		crossword.setBoard(this.initializeBoard(sizeSide, blackPercentage));
		crossword.setBoardWords(this.initializeGaps(crossword.getBoard()));
		Map<Integer, List<String>> allWords = this.repWord.getWordsOrderByLength();

		if(this.backtracking(crossword, allWords, sessionId)){
			// Después de generar el crucigrama, se establecen las palabras verticales ya que no han sido
			// establecidas en el algoritmo (simplemente comprobadas).
			for(WordDto w : crossword.getBoardWords().stream().filter(x -> x.getDirection() == Direction.S).collect(Collectors.toList())) {
				StringBuilder str = new StringBuilder();
				for(int i = w.getRow(); i < w.getRow() + w.getLength(); i++){
					str.append(crossword.getBoard()[i][w.getCol()]);
				}
				
				w.setWord(str.toString());
			}
			
			// Se establecen las definiciones de las palabras.
			for(WordDto w : crossword.getBoardWords()) {
				w.setDefinition(this.repWord.getDefinition(w.getWord()));
			}
			
			return crossword;
		}
		
		return null;
	}
	
	/**
	 * Inicializa el tablero con el tamaño y el porcentaje de espacios en negro dados.
	 **/
	private char[][] initializeBoard(int sizeSide, int blackPercentage) {
		final char[][] board = new char[sizeSide][sizeSide];
		for (int i = 0; i < sizeSide; i++) {
			for (int j = 0; j < sizeSide; j++) {
				board[i][j] = WHITE;
			}
		}

		final int blacks = sizeSide * sizeSide * blackPercentage / 100;
		for (int i = 0; i < blacks; i++) {
			int row, column;
			do {
				row = (int) (Math.random() * sizeSide);
				column = (int) (Math.random() * sizeSide);
			} while (board[row][column] == BLACK);

			board[row][column] = BLACK;
		}
		
		for (int i = 0; i < sizeSide; i++) {
			for(int j = 0; j < sizeSide; j++) {
				if((i + 1 == sizeSide || board[i+1][j] == BLACK) && (j + 1 == sizeSide || board[i][j+1] == BLACK) && (j == 0 || board[i][j-1] == BLACK) && (i == 0 || board[i-1][j] == BLACK)) {
					board[i][j] = BLACK;
				}
			}
		}

		return board;
	}
	
	private ArrayList<WordDto> initializeGaps(char[][] board) {
		final ArrayList<WordDto> words = new ArrayList<>();

		// Horizontal words
		for (int row = 0; row < board.length; row++) {
			int length = 0;
			for (int col = 0; col < board.length; col++) {
				if (board[row][col] == WHITE) {
					length++;
					if (col == board.length - 1) {
						words.add(new WordDto(row, col - (length - 1), Direction.E, length));
						length = 0;
					}
					
				} else if (board[row][col] == BLACK && length > 0) {
					words.add(new WordDto(row, col - length, Direction.E, length));
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
							words.add(new WordDto(row - (length - 1), col, Direction.S, length));
							length = 0;
						}
						
					}
				} else if (board[row][col] == BLACK && length > 0) {
					words.add(new WordDto(row - length, col, Direction.S, length));
					length = 0;
				}
			}
		}

		return words;
	}
	
	/**
	 * Método que realiza la búsqueda mediante backtracking de las palabras que rellenarán los huecos del crucigrama.
	 **/
	private boolean backtracking(Crossword crossword, Map<Integer, List<String>> allWords, String sessionId) {
		WordDto gap = this.getFirstGap(crossword.getBoardWords());
		if (gap == null)
			return true;
		
		for (final String word : allWords.get(gap.getLength())) {
			if (this.isSolution(gap, word, allWords, crossword)) {
				this.updateBoard(crossword.getBoard(), gap, word);
				this.sendCrossword(crossword, sessionId);
				
				if (this.backtracking(crossword, allWords, sessionId)) {
					return true;
				}
				
				this.downdateBoard(crossword.getBoard(), gap, word);
			}
		}

		return false;
	}
	
	private void sendCrossword(Crossword crossword, String sessionId) {
		if (this.count++ == 0){
			JSONObject jso = new JSONObject();
			jso.put("type",  "CROSSWORD");
			jso.put("puzzle", new JSONObject(crossword));
			WSServer.send(sessionId, jso);
		} else if (this.count == 10) {
			this.count = 0;
		}
	}
	
	/**
	 * Comprueba si puede añadirse la palabra al hueco estudiado.
	 * @return Devuelve 'true' si puede añadirse la palabra candidata al hueco, en caso contrario devuelve false.
	 **/
	private boolean isSolution(WordDto gap, String candidate, Map<Integer, List<String>> words, Crossword crossword) {
		// Si hay alguna palabra en el tablero igual que la palabra candidata esta no es solución
		if(crossword.getBoardWords().stream().anyMatch(x -> x.getWord() != null && x.getWord().equals(candidate))) {
			return false;
		}
		
		for (int j = gap.getPos().getColumn(), k = 0; j < gap.getPos().getColumn() + gap.getLength(); j++, k++) {
			final int auxj = j;
			final int auxk = k;
			
			// Si no está libre y no coincide
			if (crossword.getBoard()[gap.getPos().getRow()][j] != WHITE && candidate.charAt(k) != crossword.getBoard()[gap.getPos().getRow()][j]) {
				return false;
			}

			// Si hay intersección
			if ((gap.getRow() > 0 && crossword.getBoard()[gap.getRow() - 1][j] != BLACK) || (gap.getRow() < crossword.getSize() - 1 && crossword.getBoard()[gap.getRow() + 1][j] != BLACK)) {
				final WordDto intersection = crossword.getBoardWords().parallelStream().filter(x -> x.getDirection() == Direction.S && x.getCol() == auxj && x.getRow() <= gap.getRow() && x.getRow() + x.getLength() >= gap.getRow()).findFirst().get();
				StringBuilder partialWord = new StringBuilder();
				for(int positionIntersection = intersection.getRow(); positionIntersection < gap.getRow(); positionIntersection++) {
					partialWord.append(crossword.getBoard()[positionIntersection][intersection.getCol()]);
				}
				
				partialWord.append(candidate.charAt(auxk));
				if(gap.getRow() < crossword.getSize() - 1 && crossword.getBoard()[gap.getRow() + 1][j] != BLACK) {
					partialWord.append(String.format(".{%d}", intersection.getLength() - partialWord.length()));
				}

				
				final String partial = partialWord.toString();
				if (!words.get(intersection.getLength()).stream().anyMatch(x -> !x.equals(candidate) && x.matches(partial))) {
					return false;
				}
				
				if(partial.length() == intersection.getLength() && crossword.getBoardWords().stream().anyMatch(x -> x.getWord() != null && x.getWord().equals(partial))){
					return false;
				}
			}
		}

		return true;
	}
	
	private void updateBoard(char[][] board, WordDto word, String candidate) {
		word.setWord(candidate);
		Position auxPos = new Position(word.getRow(), word.getCol());
		for(int i = 0; i < candidate.length(); i++) {
			board[auxPos.getRow()][auxPos.getColumn()] = candidate.charAt(i);
			auxPos = move(auxPos);
		}
	}
	
	private void downdateBoard(char[][] board, WordDto word, String candidate) {
		word.setWord(null);
		Position auxPos = new Position(word.getRow(), word.getCol());
		for (int i = 0; i < candidate.length(); i++) {
			board[auxPos.getRow()][auxPos.getColumn()] = WHITE;
			auxPos = move(auxPos);
		}
	}
	
	private Position move(Position pos) {
		return new Position(pos.getRow(), pos.getColumn() + 1);
	}
	
	/**
	 * Devuelve el primer hueco libre horizontal en el tablero.
	 */
	private WordDto getFirstGap(List<WordDto> wordGaps) {
		// TODO cambiar orElse(null) para evitar nulos
		return wordGaps.parallelStream().filter(x -> x.getDirection() == Direction.E && x.getWord() == null).findFirst().orElse(null);
	}
}
