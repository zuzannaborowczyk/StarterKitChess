package com.capgemini.chess.algorithms.data;

import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class KnightMovesValidator implements MoveValidation {
	Board board;
	Color nextMoveColor;

	public KnightMovesValidator(Board board) {
		this.board = board;
	}

	@Override
	public boolean isMovePosible(Coordinate from, Coordinate to) throws InvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		int xTo = to.getX();
		int yTo = to.getY();
		if (Math.abs(xTo - xFrom) == 1 && Math.abs(yTo - yFrom) == 2) {
			return true;
		} else if (Math.abs(xTo - xFrom) == 2 && Math.abs(yTo - yFrom) == 1) {
			return true;
		} else {
			throw new InvalidMoveException();
		}
	}

	@Override
	public Move returnLegalMove(Coordinate from, Coordinate to) throws InvalidMoveException {
		Piece pieceAtTo = this.board.getPieceAt(to);
		if (pieceAtTo == null) {
			return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
		} else {
			Color colorOfPieceAtTo = pieceAtTo.getColor();
			if (colorOfPieceAtTo.equals(nextMoveColor)) {

				throw new InvalidMoveException();
			} else {
				return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));

			}

		}
	
	}

	@Override
	public void setNextMoveColor(Color nextMoveColor) {
		this.nextMoveColor = nextMoveColor;

	}
}
