package com.capgemini.chess.algorithms.data.enums;

/**
 * Definition of player's levels
 * 
 * @author Michal Bejm
 *
 */
public enum Level {
	
	NEWBIE(1, 0, 0, 0.0),
	WEAKLING(2, 301, 5, 0.08),
	BEGINNER(3, 601, 20, 0.16),
	EXPERIENCED_BEGINNER(4, 1201, 45, 0.24),
	MIDDLEBROW(5, 2401, 80, 0.32),
	EXPERIENCED_MIDDLEBORW(6, 4801, 125, 0.4),
	ADVANCED(7, 9601, 180, 0.48),
	PROFESSIONAL(8, 19201, 245, 0.56),
	MASTER(9, 38401, 320, 0.64),
	CHUCK_NORRIS_OF_CHESS(10, 76801, 405, 0.72);
	
	private final int value;
	private final int pointsRequired;
    private final int gamesRequired;
    private final double winsRequired;

    Level(int value, int pointsRequired, int gamesRequired,
    		double winsRequired) {
    	this.value = value;
        this.pointsRequired = pointsRequired;
        this.gamesRequired = gamesRequired;
        this.winsRequired = winsRequired;
    }
    
	public int getValue() {
		return value;
	}

	public int getPointsRequired() {
		return pointsRequired;
	}

	public int getGamesRequired() {
		return gamesRequired;
	}

	public double getWinsRequired() {
		return winsRequired;
	}
	
	public static Level getLevelByValue(int value) {
		for(Level level : Level.values())
	    {
	        if(level.getValue() == value) {
	        	return level;
	        }
	    }
	    return null;
	}
}
