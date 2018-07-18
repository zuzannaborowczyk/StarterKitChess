package com.capgemini.chess.algorithms.data;

import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;

public class QueenMovesValidator implements MoveValidation {
	Board board;
	Color nextMoveColor;
	RookMovesValidator firstValidator;
	BishopMovesValidator secondValidator;
	Move moveToBeReturn;
	public QueenMovesValidator(Board board) {
		this.board = board;
		this.firstValidator = new RookMovesValidator(this.board);
		this.secondValidator = new BishopMovesValidator(this.board);
	}
	
	@Override
	public boolean isMovePosible(Coordinate from, Coordinate to) throws InvalidMoveException {
		boolean rookMove;
		try{
		 rookMove = firstValidator.isMovePosible(from, to);
		} catch (InvalidMoveException e) {
			rookMove = false;
		}
		
		
		if (rookMove) {
			moveToBeReturn = firstValidator.returnLegalMove(from, to);
			return true;
		} else if (secondValidator.isMovePosible(from, to)) {
			moveToBeReturn = secondValidator.returnLegalMove(from, to);
		return true;
		} throw new InvalidMoveException();
	} 

	@Override
	public Move returnLegalMove(Coordinate from, Coordinate to) throws InvalidMoveException {
		// TODO Auto-generated method stub
		return moveToBeReturn;
	}

	@Override
	public void setNextMoveColor(Color nextMoveColor) {
		this.nextMoveColor = nextMoveColor;
		this.firstValidator.setNextMoveColor(nextMoveColor);
		this.secondValidator.setNextMoveColor(nextMoveColor);
	}
}
