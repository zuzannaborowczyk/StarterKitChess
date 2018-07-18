package com.capgemini.chess.algorithms.data;

import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class BishopMovesValidator implements MoveValidation {
	Board board;
	Color nextMoveColor;

	public BishopMovesValidator(Board board) {
		this.board = board;
	}

	@Override
	public boolean isMovePosible(Coordinate from, Coordinate to) throws InvalidMoveException {
		// if (Math.abs(xTo - xFrom) == Math.abs(yTo - yFrom)) {
		int xFrom = from.getX();
		int yFrom = from.getY();
		int xTo = to.getX();
		int yTo = to.getY();
		if (xTo > xFrom && yTo > yFrom) {
			int currentX = xFrom+1;
			int currentY = yFrom+1;
			for (int i = xFrom+1; i < xTo; i++) {
				currentX = i;
				for (int j = yFrom+1; j < yTo; j++) {

					currentY = j;
					Coordinate currentCoordinate = new Coordinate(currentX, currentY);
					Piece piece = this.board.getPieceAt(currentCoordinate);
					if (piece != null) {

					}
					throw new InvalidMoveException();
				}
			}

			return true;
		} else if (xTo > xFrom && yTo < yFrom) {
			int currentX = xFrom+1;
			int currentY = yFrom-1;
			for (int i = xFrom+1; i < xTo; i++) {
				currentX = i;
				for (int j = yFrom-1; j > yTo; j--) {

					currentY = j;
					Coordinate currentCoordinate = new Coordinate(currentX, currentY);
					Piece piece = this.board.getPieceAt(currentCoordinate);
					if (piece != null) {

					}
					throw new InvalidMoveException();
				}
			}

			return true;
		} else if (xTo < xFrom && yTo < yFrom) {
			int currentX = xFrom-1;
			int currentY = yFrom-1;
			for (int i = xFrom-1; i > xTo; i--) {
				currentX = i;
				for (int j = yFrom-1; j > yTo; j--) {

					currentY = j;
					Coordinate currentCoordinate = new Coordinate(currentX, currentY);
					Piece piece = this.board.getPieceAt(currentCoordinate);
					if (piece != null) {

					}
					throw new InvalidMoveException();
				}
			}

			return true;
		} else if (xTo < xFrom && yTo > yFrom) {
			int currentX = xFrom-1;
			int currentY = yFrom+1;
			for (int i = xFrom-1; i > xTo; i--) {
				currentX = i;
				for (int j = yFrom+1; j < yTo; j++) {

					currentY = j;
					Coordinate currentCoordinate = new Coordinate(currentX, currentY);
					Piece piece = this.board.getPieceAt(currentCoordinate);
					if (piece != null) {

					}
					throw new InvalidMoveException();
				}
			}

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
