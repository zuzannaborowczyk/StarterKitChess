package com.capgemini.chess.algorithms.data.enums;

/**
 * Types of moves
 * 
 * @author Michal Bejm
 *
 */
public enum MoveType {
	ATTACK, //ruch
	CAPTURE, //bicie figury przeciwnika
	CASTLING,
	EN_PASSANT;
}
