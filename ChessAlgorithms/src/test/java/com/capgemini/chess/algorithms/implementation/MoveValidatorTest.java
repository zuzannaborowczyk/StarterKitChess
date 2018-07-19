package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class MoveValidatorTest {

	
	@Test
	public void testUpdateBoardStateCheckWhiteKing() throws InvalidMoveException {
		// given
		Board board = new Board();
		
		board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(1, 3));
		board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		
		// when
		BoardManager boardManager = new BoardManager(board);
		BoardState boardState = boardManager.updateBoardState();
		
		// then
		assertEquals(BoardState.CHECK, boardState);
	}
	
	
	
	
	
	@Test
	public void shouldBeRegularBoardState() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(7, 7));
		board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 0));
		
		// when
		BoardManager boardManager = new BoardManager(board);
		BoardState boardState = boardManager.updateBoardState();
		
		// then
		assertEquals(BoardState.REGULAR, boardState);
	}
	
	
	
	@Test
	public void testPerformCaptureByRook() throws InvalidMoveException {
		// given
		Board board = new Board();
		board.getMoveHistory().add(createDummyMove(board));
		board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(5, 3));
		board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(5, 2));
		
		// when
		BoardManager boardManager = new BoardManager(board);		
		
		Move move = boardManager.performMove(new Coordinate(5, 3), new Coordinate(5, 2));
		
				
		// then 
		assertEquals(MoveType.CAPTURE, move.getType());
		assertEquals(Piece.BLACK_ROOK, move.getMovedPiece());
	}
private Move createDummyMove(Board board) {
		
		Move move = new Move();
		
		if (board.getMoveHistory().size() % 2 == 0) {
			board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
			move.setMovedPiece(Piece.WHITE_ROOK);
		}
		else {
			board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 0));
			move.setMovedPiece(Piece.BLACK_ROOK);
		}
		move.setFrom(new Coordinate(0, 0));
		move.setTo(new Coordinate(0, 0));
		move.setType(MoveType.ATTACK);
		board.setPieceAt(null, new Coordinate(0, 0));
		return move;
	}
}
