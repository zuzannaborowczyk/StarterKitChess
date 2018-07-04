package com.capgemini.chess.algorithms.implementation.exceptions;

/**
 * Exception thrown in case the invalid move is about to be performed
 * 
 * @author Michal Bejm
 *
 */
public class InvalidMoveException extends Exception {

	private static final long serialVersionUID = -3078327974919142439L;

	public InvalidMoveException() {
		super("Invalid move!");
	}
	
	public InvalidMoveException(String message) {
		super("Invalid move! " + message);
	}
}
