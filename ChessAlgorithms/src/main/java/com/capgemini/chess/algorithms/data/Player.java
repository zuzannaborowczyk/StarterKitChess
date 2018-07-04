package com.capgemini.chess.algorithms.data;

import com.capgemini.chess.algorithms.data.enums.Level;

/**
 * Information on player's stats.
 * 
 * @author Michal Bejm
 *
 */
public class Player {

	private Level level;
	private int points;
	private int gamesPlayed;
	private int gamesWon;
	private int gamesDrawn;
	private int gamesLost;

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	public int getGamesDrawn() {
		return gamesDrawn;
	}

	public void setGamesDrawn(int gamesDrawn) {
		this.gamesDrawn = gamesDrawn;
	}

	public int getGamesLost() {
		return gamesLost;
	}

	public void setGamesLost(int gamesLost) {
		this.gamesLost = gamesLost;
	}
}
