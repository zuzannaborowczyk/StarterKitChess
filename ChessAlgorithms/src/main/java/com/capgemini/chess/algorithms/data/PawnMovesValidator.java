package com.capgemini.chess.algorithms.data;

import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class PawnMovesValidator implements MoveValidation {
	Board board;
	Color nextMoveColor;

	public PawnMovesValidator(Board board) {
		this.board = board;
	}

	// WHITEPAWN
	@Override
	public boolean isMovePosible(Coordinate from, Coordinate to) throws InvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		int xTo = to.getX();
		int yTo = to.getY();
		switch (this.nextMoveColor) {
		case WHITE:
			if (yFrom == 1) {
				return firstWhiteMoveCase(from, to);
			} else {
				return restOfTheWhiteMoveCases(from, to);
			}

		case BLACK:
			if (yFrom == 6) {
				return firstBlackMoveCase(from, to);
			} else {
				return restOfTheBlackMoveCases(from, to);
			}

		default:
			throw new InvalidMoveException();
		}
	}

	public boolean restOfTheWhiteMoveCases(Coordinate from, Coordinate to) throws InvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		int xTo = to.getX();
		int yTo = to.getY();
		if (yTo - yFrom == 1 && xTo == xFrom) {
			Coordinate coordinateTo = new Coordinate(xFrom, yFrom + 1);
			Piece pieceOnTo = this.board.getPieceAt(coordinateTo);
			if (pieceOnTo == null) {
				return true;
			}
			throw new InvalidMoveException();
		} else if (yTo - yFrom == 1 && xTo - xFrom == 1) {
			Coordinate coordinateOnRight = new Coordinate(xFrom + 1, yFrom + 1);
			Piece pieceOnRight = this.board.getPieceAt(coordinateOnRight);
			if (pieceOnRight == null) {
				return false;
			}
			Color colorOfPieceOnRight = pieceOnRight.getColor();
			if (colorOfPieceOnRight.equals(nextMoveColor)) {
				throw new InvalidMoveException();
			} else if (!colorOfPieceOnRight.equals(nextMoveColor)) {
				return true;
			}
			throw new InvalidMoveException();
		} else if (yTo - yFrom == 1 && xTo - xFrom == -1) {
			Coordinate coordinateOnLeft = new Coordinate(xFrom - 1, yFrom + 1);
			Piece pieceOnLeft = this.board.getPieceAt(coordinateOnLeft);
			if (pieceOnLeft == null) {
				return false;
			}
			Color colorOfPieceOnLeft = pieceOnLeft.getColor();
			if (colorOfPieceOnLeft.equals(nextMoveColor)) {
				throw new InvalidMoveException();
			} else if (!colorOfPieceOnLeft.equals(nextMoveColor)) {
				return true;
			}

		}
		throw new InvalidMoveException();
	}

	public boolean restOfTheBlackMoveCases(Coordinate from, Coordinate to) throws InvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		int xTo = to.getX();
		int yTo = to.getY();
		if (yFrom - yTo == 1 && xTo == xFrom) {
			Coordinate coordinateTo = new Coordinate(xFrom, yFrom - 1);
			Piece pieceOnTo = this.board.getPieceAt(coordinateTo);
			if (pieceOnTo == null) {

			}
			return true;
		} else if (yFrom - yTo == 1 && xTo - xFrom == 1) {
			Coordinate coordinateOnRight = new Coordinate(xFrom + 1, yFrom - 1);
			Piece pieceOnRight = this.board.getPieceAt(coordinateOnRight);
			if (pieceOnRight == null) {
				return false;
			}
			Color colorOfPieceOnRight = pieceOnRight.getColor();
			if (colorOfPieceOnRight.equals(nextMoveColor)) {
				throw new InvalidMoveException();
			} else if (!colorOfPieceOnRight.equals(nextMoveColor)) {

			}
			return true;
		} else if (yFrom - yTo == 1 && xTo - xFrom == -1) {
			Coordinate coordinateOnLeft = new Coordinate(xFrom - 1, yFrom - 1);
			Piece pieceOnLeft = this.board.getPieceAt(coordinateOnLeft);
			if (pieceOnLeft == null) {
				return false;
			}
			Color colorOfPieceOnLeft = pieceOnLeft.getColor();
			if (colorOfPieceOnLeft.equals(nextMoveColor)) {
				throw new InvalidMoveException();
			} else if (!colorOfPieceOnLeft.equals(nextMoveColor)) {

			}
			return true;
		} else
			throw new InvalidMoveException();
	}

	public boolean firstWhiteMoveCase(Coordinate from, Coordinate to) throws InvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		int xTo = to.getX();
		int yTo = to.getY();
		if (Math.abs(yTo - yFrom) <= 2 && xTo == xFrom) {
			Coordinate coordinateInBetween = new Coordinate(xFrom, yFrom + 1);
			Coordinate coordinateTo = new Coordinate(xFrom, yFrom + 2);
			Piece pieceInBetween = this.board.getPieceAt(coordinateInBetween);
			Piece pieceOnTo = this.board.getPieceAt(coordinateTo);
			if (pieceInBetween == null && pieceOnTo == null) {

			}
			return true; // tu musi byc wyjatek
		} else if (yTo - yFrom == 1 && xTo - xFrom == 1) {
			Coordinate coordinateOnRight = new Coordinate(xFrom + 1, yFrom + 1);
			Piece pieceOnRight = this.board.getPieceAt(coordinateOnRight);
			if (pieceOnRight == null) {
				return false;
			}
			Color colorOfPieceOnRight = pieceOnRight.getColor();
			if (colorOfPieceOnRight.equals(nextMoveColor)) {
				throw new InvalidMoveException();
			} else if (!colorOfPieceOnRight.equals(nextMoveColor)) {

			}
			return true;
		} else if (yTo - yFrom == 1 && xTo - xFrom == -1) {
			Coordinate coordinateOnLeft = new Coordinate(xFrom - 1, yFrom + 1);
			Piece pieceOnLeft = this.board.getPieceAt(coordinateOnLeft);
			if (pieceOnLeft == null) {
				return false;
			}
			Color colorOfPieceOnLeft = pieceOnLeft.getColor();
			if (colorOfPieceOnLeft.equals(nextMoveColor)) {
				throw new InvalidMoveException();
			} else if (!colorOfPieceOnLeft.equals(nextMoveColor)) {

			}
			return true;
		} else
			throw new InvalidMoveException();
	}

	public boolean firstBlackMoveCase(Coordinate from, Coordinate to) throws InvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		int xTo = to.getX();
		int yTo = to.getY();
		if (Math.abs(yFrom - yTo) <= 2 && xTo == xFrom) {
			Coordinate coordinateInBetween = new Coordinate(xFrom, yFrom - 1);
			Coordinate coordinateTo = new Coordinate(xFrom, yFrom - 2);
			Piece pieceInBetween = this.board.getPieceAt(coordinateInBetween);
			Piece pieceOnTo = this.board.getPieceAt(coordinateTo);
			if (pieceInBetween != null && pieceOnTo != null) {
				throw new InvalidMoveException();
			}
			return true;

		} else if (yFrom - yTo == 1 && xTo - xFrom == 1) {
			Coordinate coordinateOnRight = new Coordinate(xFrom + 1, yFrom - 1);
			Piece pieceOnRight = this.board.getPieceAt(coordinateOnRight);
			if (pieceOnRight == null) {
				return false;
			}
			Color colorOfPieceOnRight = pieceOnRight.getColor();
			if (colorOfPieceOnRight.equals(nextMoveColor)) {
				throw new InvalidMoveException();
			} else if (!colorOfPieceOnRight.equals(nextMoveColor)) {
				return true;
			}
			throw new InvalidMoveException();
		} else if (yFrom - yTo == 1 && xTo - xFrom == -1) {
			Coordinate coordinateOnLeft = new Coordinate(xFrom - 1, yFrom - 1);
			Piece pieceOnLeft = this.board.getPieceAt(coordinateOnLeft);
			if (pieceOnLeft == null) {
				return false;
			}
			Color colorOfPieceOnLeft = pieceOnLeft.getColor();
			if (colorOfPieceOnLeft.equals(nextMoveColor)) {
				throw new InvalidMoveException();
			} else if (!colorOfPieceOnLeft.equals(nextMoveColor)) {

			}
			return true;
		} else
			throw new InvalidMoveException();
	}

	@Override
	public Move returnLegalMove(Coordinate from, Coordinate to) throws InvalidMoveException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		int xTo = to.getX();
		int yTo = to.getY();

		switch (this.nextMoveColor) {
		case WHITE:
			Piece pieceAtToW = this.board.getPieceAt(to);

			boolean attackCondition = Math.abs(yTo - yFrom) <= 2 && xTo == xFrom;
			boolean captureConditionRight = yTo - yFrom == 1 && xTo - xFrom == 1;
			boolean captureConditionLeft = yTo - yFrom == 1 && xTo - xFrom == -1;
			if (pieceAtToW == null && attackCondition) {
				return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
			} else if (pieceAtToW != null && this.board.getPieceAt(to).getColor() == nextMoveColor
					&& captureConditionRight) {
				throw new InvalidMoveException();
			} else if (pieceAtToW != null && this.board.getPieceAt(to).getColor() == nextMoveColor
					&& captureConditionLeft) {
				throw new InvalidMoveException();
			} else if (pieceAtToW != null && this.board.getPieceAt(to).getColor() != nextMoveColor
					&& captureConditionRight) {
				return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));
			} else if (pieceAtToW != null && this.board.getPieceAt(to).getColor() != nextMoveColor
					&& captureConditionRight) {
				return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));
			} else {
				throw new InvalidMoveException();
			}

		case BLACK:
			Piece pieceAtToB = this.board.getPieceAt(to);

			boolean attackConditionB = Math.abs(yTo - yFrom) <= 2 && xTo == xFrom;
			boolean captureConditionRightB = yTo - yFrom == -1 && xTo - xFrom == -1;
			boolean captureConditionLeftB = yTo - yFrom == -1 && xTo - xFrom == 1;
			if (pieceAtToB == null && attackConditionB) {
				return new Move(from, to, MoveType.ATTACK, board.getPieceAt(from));
			} else if (pieceAtToB != null && this.board.getPieceAt(to).getColor() == nextMoveColor
					&& captureConditionRightB) {
				throw new InvalidMoveException();
			} else if (pieceAtToB != null && this.board.getPieceAt(to).getColor() == nextMoveColor
					&& captureConditionLeftB) {
				throw new InvalidMoveException();
			} else if (pieceAtToB != null && this.board.getPieceAt(to).getColor() != nextMoveColor
					&& captureConditionRightB) {
				return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));
			} else if (pieceAtToB != null && this.board.getPieceAt(to).getColor() != nextMoveColor
					&& captureConditionRightB) {
				return new Move(from, to, MoveType.CAPTURE, board.getPieceAt(from));
			} else {
				throw new InvalidMoveException();
			}
		default:
			throw new InvalidMoveException();
		}
	}

	@Override
	public void setNextMoveColor(Color nextMoveColor) {
		this.nextMoveColor = nextMoveColor;

	}

}
