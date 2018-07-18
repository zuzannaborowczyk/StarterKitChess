package com.capgemini.chess.algorithms.data;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.BoardManager;

public class MoveValidator {
	BoardManager boardManager = new BoardManager();
	Board board = boardManager.getBoard();

	//
	public MoveValidator() {

	}

	public List<Coordinate> calculateRookLegalMoves(Coordinate from) {
		// ta metoda bedzie iterowac po kazdym polu na planszy
		List<Coordinate> rookPosibleMoves = new ArrayList<>();
		int x = from.getX();
		int y = from.getY();
		for (int i = x; i < 8; i++) {
			rookPosibleMoves.add(new Coordinate(i, y));
			}
		for (int j = y; j < 8; j++) { 
			rookPosibleMoves.add(new Coordinate(x, j));
		}
		for (int i = x; i>0; i--) {
			rookPosibleMoves.add(new Coordinate(i, y));
		}
		for (int j = y; j>0; j--) {
			rookPosibleMoves.add(new Coordinate(x, j));
		}
		rookPosibleMoves.remove(from);
		rookPosibleMoves.remove(from);		
	
		return rookPosibleMoves;
	}

	public List<Coordinate> calculateBishopLegalMoves(Coordinate from) {
		List<Coordinate> bishopPosibleMoves = new ArrayList<>();
		int x = from.getX();
		int y = from.getY();
		for (int i = 1; (x + i) > 0 && (x + i) < 8; i++) {
			bishopPosibleMoves.add(new Coordinate(x + i, y + i));
		}
		for (int i = 1; (x + i) > 0 && (x + i) <8; i++) {
			bishopPosibleMoves.add(new Coordinate(x + i, y - i));
		}
		for (int i = 1; (x + i) > 0 && (x + i)<8; i++) {
			bishopPosibleMoves.add(new Coordinate(x - i, y + i));
		}
		for (int i = 1; (x + i) > 0 && (x + i)<8; i++) {
			bishopPosibleMoves.add(new Coordinate(x - i, y - i));
		}
		return bishopPosibleMoves;
	}

	public List<Coordinate> calculateQueenLegalMoves(Coordinate from) {
		List<Coordinate> queenPosibleMoves = new ArrayList<>();
		List<Coordinate> rookPosibleMoves = calculateBishopLegalMoves(from);
		List<Coordinate> bishopPosibleMoves = calculateRookLegalMoves(from);
		rookPosibleMoves.addAll(bishopPosibleMoves);
		return queenPosibleMoves = rookPosibleMoves;

	}

	public List<Coordinate> calculateKingLegalMoves(Coordinate from) {
		List<Coordinate> kingPosibleMoves = new ArrayList<>();
		int x = from.getX();
		int y = from.getY();
		
		kingPosibleMoves.add(new Coordinate(x, y + 1));
		kingPosibleMoves.add(new Coordinate(x, y - 1));
		kingPosibleMoves.add(new Coordinate(x + 1, y));
		kingPosibleMoves.add(new Coordinate(x - 1, y));
		kingPosibleMoves.add(new Coordinate(x - 1, y - 1));
		kingPosibleMoves.add(new Coordinate(x + 1, y - 1));
		kingPosibleMoves.add(new Coordinate(x + 1, y + 1));
		kingPosibleMoves.add(new Coordinate(x - 1, y + 1));
		return kingPosibleMoves;
	}

	public List<Coordinate> calculateKnightLegalMoves(Coordinate coordinate) {
		List<Coordinate> knightPosibleMoves = new ArrayList<>();
		int x = coordinate.getX();
		int y = coordinate.getY();
		knightPosibleMoves.add(new Coordinate(x - 1, y + 2));
		knightPosibleMoves.add(new Coordinate(x - 2, y + 1));
		knightPosibleMoves.add(new Coordinate(x - 2, y - 1));
		knightPosibleMoves.add(new Coordinate(x - 1, y - 2));
		knightPosibleMoves.add(new Coordinate(x + 1, y - 2));
		knightPosibleMoves.add(new Coordinate(x + 2, y - 1));
		knightPosibleMoves.add(new Coordinate(x + 2, y + 1));
		knightPosibleMoves.add(new Coordinate(x + 1, y + 2));
		return knightPosibleMoves;
	}

	public List<Coordinate> calculatePawnLegalMoves(Coordinate coordinate) {
		List<Coordinate> pawnPosibleMoves = new ArrayList<>();
		int x = coordinate.getX();
		int y = coordinate.getY();
		Piece piece = this.board.getPieceAt(coordinate);
		Color pieceColor = piece.getColor();
		if (pieceColor == Color.WHITE) {
			if (y == 1) {
				pawnPosibleMoves.add(new Coordinate(x, y + 1));
				pawnPosibleMoves.add(new Coordinate(x, y + 2));
				pawnPosibleMoves.add(new Coordinate(x - 1, y + 1));
				pawnPosibleMoves.add(new Coordinate(x + 1, y + 1));

			} else {
				pawnPosibleMoves.add(new Coordinate(x, y + 1));
				pawnPosibleMoves.add(new Coordinate(x - 1, y + 1));
				pawnPosibleMoves.add(new Coordinate(x + 1, y + 1));
			}
			return pawnPosibleMoves;
		} else if (pieceColor == Color.BLACK) {
			if (y == 6) {
				pawnPosibleMoves.add(new Coordinate(x, y - 1));
				pawnPosibleMoves.add(new Coordinate(x, y - 2));
				pawnPosibleMoves.add(new Coordinate(x - 1, y - 1));
				pawnPosibleMoves.add(new Coordinate(x + 1, y - 1));

			} else {
				pawnPosibleMoves.add(new Coordinate(x, y - 1));
				pawnPosibleMoves.add(new Coordinate(x - 1, y - 1));
				pawnPosibleMoves.add(new Coordinate(x + 1, y - 1));
				return pawnPosibleMoves;
			}
		}
		return pawnPosibleMoves;
	}
}