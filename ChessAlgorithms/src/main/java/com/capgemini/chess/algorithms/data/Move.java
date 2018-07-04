package com.capgemini.chess.algorithms.data;

import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;

/**
 * Chess move definition.
 * 
 * @author Michal Bejm
 *
 */
public class Move {

	private Coordinate from;
	private Coordinate to;
	private MoveType type;
	private Piece movedPiece;

	public Coordinate getFrom() {
		return from;
	}

	public void setFrom(Coordinate from) {
		this.from = from;
	}

	public Coordinate getTo() {
		return to;
	}

	public void setTo(Coordinate to) {
		this.to = to;
	}

	public MoveType getType() {
		return type;
	}

	public void setType(MoveType type) {
		this.type = type;
	}

	public Piece getMovedPiece() {
		return movedPiece;
	}

	public void setMovedPiece(Piece movedPiece) {
		this.movedPiece = movedPiece;
	}
}
