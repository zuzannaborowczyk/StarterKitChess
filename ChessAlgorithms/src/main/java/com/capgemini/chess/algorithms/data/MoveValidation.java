package com.capgemini.chess.algorithms.data;

import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public interface MoveValidation {

	boolean isMovePosible(Coordinate from, Coordinate to) throws InvalidMoveException;

	Move returnLegalMove(Coordinate from, Coordinate to) throws InvalidMoveException;

	void setNextMoveColor(Color nextMoveColor);

}
