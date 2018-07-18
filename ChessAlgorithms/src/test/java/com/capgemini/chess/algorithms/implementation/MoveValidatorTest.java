package com.capgemini.chess.algorithms.implementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.MoveValidator;

public class MoveValidatorTest {

	@Test
	public void shouldReturnFalseIfOutOfRange() {
		//given
		BoardManager moveValidator = new BoardManager();
		//when
		Coordinate from = new Coordinate(-2, 9);
		Coordinate to = new Coordinate(-5, 10);
		//then
		assertFalse()
	}
//Jak to zrobic dla wyjatku?
}
