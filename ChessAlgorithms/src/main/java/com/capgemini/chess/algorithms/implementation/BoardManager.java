package com.capgemini.chess.algorithms.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.capgemini.chess.algorithms.data.BishopMovesValidator;
import com.capgemini.chess.algorithms.data.Coordinate;
import com.capgemini.chess.algorithms.data.KingMovesValidator;
import com.capgemini.chess.algorithms.data.KnightMovesValidator;
import com.capgemini.chess.algorithms.data.Move;
import com.capgemini.chess.algorithms.data.MoveValidation;
import com.capgemini.chess.algorithms.data.PawnMovesValidator;
import com.capgemini.chess.algorithms.data.PieceCoordinate;
import com.capgemini.chess.algorithms.data.QueenMovesValidator;
import com.capgemini.chess.algorithms.data.RookMovesValidator;
import com.capgemini.chess.algorithms.data.enums.BoardState;
import com.capgemini.chess.algorithms.data.enums.Color;
import com.capgemini.chess.algorithms.data.enums.MoveType;
import com.capgemini.chess.algorithms.data.enums.Piece;
import com.capgemini.chess.algorithms.data.enums.PieceType;
import com.capgemini.chess.algorithms.data.generated.Board;
import com.capgemini.chess.algorithms.implementation.exceptions.InvalidMoveException;
import com.capgemini.chess.algorithms.implementation.exceptions.KingInCheckException;

/**
 * Class for managing of basic operations on the Chess Board.
 *
 * @author Michal Bejm
 *
 */
public class BoardManager {

	private Board board = new Board();
	private Piece Piece;

	public BoardManager() {
		initBoard();
	}

	public BoardManager(List<Move> moves) {
		initBoard();
		for (Move move : moves) {
			addMove(move);
		}
	}

	public BoardManager(Board board) {
		this.board = board;
	}

	/**
	 * Getter for generated board
	 *
	 * @return board
	 */
	public Board getBoard() {
		return this.board;
	}

	/**
	 * Performs move of the chess piece on the chess board from one field to
	 * another.
	 *
	 * @param from
	 *            coordinates of 'from' field
	 * @param to
	 *            coordinates of 'to' field
	 * @return move object which includes moved piece and move type
	 * @throws InvalidMoveException
	 *             in case move is not valid
	 */
	public Move performMove(Coordinate from, Coordinate to) throws InvalidMoveException {

		Move move = validateMove(from, to);

		addMove(move);

		return move;
	}

	/**
	 * Calculates state of the chess board.
	 *
	 * @return state of the chess board
	 */
	public BoardState updateBoardState() {

		Color nextMoveColor = calculateNextMoveColor();

		boolean isKingInCheck = isKingInCheck(nextMoveColor);
		boolean isAnyMoveValid = isAnyMoveValid(nextMoveColor);

		BoardState boardState;
		if (isKingInCheck) {
			if (isAnyMoveValid) {
				boardState = BoardState.CHECK;
			} else {
				boardState = BoardState.CHECK_MATE;
			}
		} else {
			if (isAnyMoveValid) {
				boardState = BoardState.REGULAR;
			} else {
				boardState = BoardState.STALE_MATE;
			}
		}
		this.board.setState(boardState);
		return boardState;
	}

	/**
	 * Checks threefold repetition rule (one of the conditions to end the chess
	 * game with a draw).
	 *
	 * @return true if current state repeated at list two times, false otherwise
	 */
	public boolean checkThreefoldRepetitionRule() {

		// there is no need to check moves that where before last capture/en
		// passant/castling
		int lastNonAttackMoveIndex = findLastNonAttackMoveIndex();
		List<Move> omittedMoves = this.board.getMoveHistory().subList(0, lastNonAttackMoveIndex);
		BoardManager simulatedBoardManager = new BoardManager(omittedMoves);

		int counter = 0;
		for (int i = lastNonAttackMoveIndex; i < this.board.getMoveHistory().size(); i++) {
			Move moveToAdd = this.board.getMoveHistory().get(i);
			simulatedBoardManager.addMove(moveToAdd);
			boolean areBoardsEqual = Arrays.deepEquals(this.board.getPieces(),
					simulatedBoardManager.getBoard().getPieces());
			if (areBoardsEqual) {
				counter++;
			}
		}

		return counter >= 2;
	}

	/**
	 * Checks 50-move rule (one of the conditions to end the chess game with a
	 * draw).
	 *
	 * @return true if no pawn was moved or not capture was performed during
	 *         last 50 moves, false otherwise
	 */
	public boolean checkFiftyMoveRule() {

		// for this purpose a "move" consists of a player completing his turn
		// followed by his opponent completing his turn
		if (this.board.getMoveHistory().size() < 100) {
			return false;
		}

		for (int i = this.board.getMoveHistory().size() - 1; i >= this.board.getMoveHistory().size() - 100; i--) {
			Move currentMove = this.board.getMoveHistory().get(i);
			PieceType currentPieceType = currentMove.getMovedPiece().getType();
			if (currentMove.getType() != MoveType.ATTACK || currentPieceType == PieceType.PAWN) {
				return false;
			}
		}

		return true;
	}

	// PRIVATE

	private void initBoard() {

		this.board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(0, 7));
		this.board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(1, 7));
		this.board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(2, 7));
		this.board.setPieceAt(Piece.BLACK_QUEEN, new Coordinate(3, 7));
		this.board.setPieceAt(Piece.BLACK_KING, new Coordinate(4, 7));
		this.board.setPieceAt(Piece.BLACK_BISHOP, new Coordinate(5, 7));
		this.board.setPieceAt(Piece.BLACK_KNIGHT, new Coordinate(6, 7));
		this.board.setPieceAt(Piece.BLACK_ROOK, new Coordinate(7, 7));

		for (int x = 0; x < Board.SIZE; x++) {
			this.board.setPieceAt(Piece.BLACK_PAWN, new Coordinate(x, 6));
		}

		this.board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(0, 0));
		this.board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(1, 0));
		this.board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(2, 0));
		this.board.setPieceAt(Piece.WHITE_QUEEN, new Coordinate(3, 0));
		this.board.setPieceAt(Piece.WHITE_KING, new Coordinate(4, 0));
		this.board.setPieceAt(Piece.WHITE_BISHOP, new Coordinate(5, 0));
		this.board.setPieceAt(Piece.WHITE_KNIGHT, new Coordinate(6, 0));
		this.board.setPieceAt(Piece.WHITE_ROOK, new Coordinate(7, 0));

		for (int x = 0; x < Board.SIZE; x++) {
			this.board.setPieceAt(Piece.WHITE_PAWN, new Coordinate(x, 1));
		}
	}

	private void addMove(Move move) {

		addRegularMove(move);

		if (move.getType() == MoveType.CASTLING) {
			addCastling(move);
		} else if (move.getType() == MoveType.EN_PASSANT) {
			addEnPassant(move);
		}

		this.board.getMoveHistory().add(move);
	}

	private void addRegularMove(Move move) {
		Piece movedPiece = this.board.getPieceAt(move.getFrom());
		this.board.setPieceAt(null, move.getFrom());
		this.board.setPieceAt(movedPiece, move.getTo());

		performPromotion(move, movedPiece);
	}

	private void performPromotion(Move move, Piece movedPiece) {
		if (movedPiece == Piece.WHITE_PAWN && move.getTo().getY() == (Board.SIZE - 1)) {
			this.board.setPieceAt(Piece.WHITE_QUEEN, move.getTo());
		}
		if (movedPiece == Piece.BLACK_PAWN && move.getTo().getY() == 0) {
			this.board.setPieceAt(Piece.BLACK_QUEEN, move.getTo());
		}
	}

	private void addCastling(Move move) {
		if (move.getFrom().getX() > move.getTo().getX()) {
			Piece rook = this.board.getPieceAt(new Coordinate(0, move.getFrom().getY()));
			this.board.setPieceAt(null, new Coordinate(0, move.getFrom().getY()));
			this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() + 1, move.getTo().getY()));
		} else {
			Piece rook = this.board.getPieceAt(new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
			this.board.setPieceAt(null, new Coordinate(Board.SIZE - 1, move.getFrom().getY()));
			this.board.setPieceAt(rook, new Coordinate(move.getTo().getX() - 1, move.getTo().getY()));
		}
	}

	private void addEnPassant(Move move) {
		Move lastMove = this.board.getMoveHistory().get(this.board.getMoveHistory().size() - 1);
		this.board.setPieceAt(null, lastMove.getTo());
	}

	private Move validateMove(Coordinate from, Coordinate to) throws InvalidMoveException, KingInCheckException {
		int xFrom = from.getX();
		int yFrom = from.getY();
		int xTo = to.getX();
		int yTo = to.getY();

		MoveValidation posibleMoves = null;
		checkBoardRange(xFrom, yFrom, xTo, yTo);
		isCoordinateFromOccupied(from);
		IsPieceMine(from);
		// w switchu wg piece filtrowac a nie piecetype
		switch (returnPieceType(from)) {
		case ROOK:
			posibleMoves = new RookMovesValidator(board);
			break;
		case BISHOP:
			posibleMoves = new BishopMovesValidator(board);
			break;
		case QUEEN:
			posibleMoves = new QueenMovesValidator(board);
			break;
		case KNIGHT:
			posibleMoves = new KnightMovesValidator(board);
			break;
		case KING:
			posibleMoves = new KingMovesValidator(board);
			break;
		case PAWN:
			posibleMoves = new PawnMovesValidator(board);
			break;
		default:
			break;
		}
		posibleMoves.setNextMoveColor(calculateNextMoveColor());
		posibleMoves.isMovePosible(from, to);
		
		Move finalMoveValidation = posibleMoves.returnLegalMove(from, to);

		
		
		Board boardSymulation = new Board(board);
		this.board.setPieceAt(board.getPieceAt(from), to);
		this.board.setPieceAt(null, from);

		if (isKingInCheck(calculateNextMoveColor())) {

			this.board = boardSymulation;
			throw new KingInCheckException();

		} else {
			this.board = boardSymulation;
			return finalMoveValidation;
		}
	}

	public PieceType returnPieceType(Coordinate from) {
		Piece piece = this.board.getPieceAt(from);
		PieceType typeOfPiece = piece.getType();
		return typeOfPiece;
	}

	public boolean IsPieceMine(Coordinate from) throws InvalidMoveException {
		Color nextMoveColor = calculateNextMoveColor();
		Piece piece = this.board.getPieceAt(from);
		Color colorOfPiece = piece.getColor();
		if (colorOfPiece.equals(nextMoveColor)) {
			return true;
		}
		throw new InvalidMoveException();
	}

	public boolean isCoordinateFromOccupied(Coordinate from) throws InvalidMoveException {
		Piece piece = this.board.getPieceAt(from);
		if (piece != null) {
			return true;
		}
		throw new InvalidMoveException();

	}

	public boolean checkBoardRange(int xFrom, int yFrom, int xTo, int yTo) throws InvalidMoveException {
		if (xFrom >= 0 && xFrom <= 7 && yFrom >= 0 && yFrom <= 7 && xTo >= 0 && xTo <= 7 && yTo >= 0 && yTo <= 7) {
			return true;
		}
		throw new InvalidMoveException();

	}

	private boolean isKingInCheck(Color kingColor) {

		Coordinate myKingCoordinate = getKingCoordinate(kingColor);
		if (myKingCoordinate == null) {
			return false;
		}
		List<PieceCoordinate> listOfAllPiecesOfEnemyColor = getAllPiecesOfTheColor(getEnemyColor(kingColor));
		for (int i = 0; i < listOfAllPiecesOfEnemyColor.size(); i++) {
			Coordinate potentialAttackerCoordinate = listOfAllPiecesOfEnemyColor.get(i).getPosition();
			PieceType potentialAttackerPieceType = listOfAllPiecesOfEnemyColor.get(i).getPiece().getType();
			MoveValidation posibleMoves = null;
			switch (potentialAttackerPieceType) {
			case ROOK:
				posibleMoves = new RookMovesValidator(board);
				break;
			case BISHOP:
				posibleMoves = new BishopMovesValidator(board);
				break;
			case QUEEN:
				posibleMoves = new QueenMovesValidator(board);
				break;
			case KNIGHT:
				posibleMoves = new KnightMovesValidator(board);
				break;
			case KING:
				posibleMoves = new KingMovesValidator(board);
				break;
			case PAWN:
				posibleMoves = new PawnMovesValidator(board);
				break;
			default:
				break;
			}
			try {
				posibleMoves.setNextMoveColor(getEnemyColor(kingColor));
				if (posibleMoves.isMovePosible(potentialAttackerCoordinate, myKingCoordinate)) {
					return true;
				}
			} catch (InvalidMoveException e) {
				continue;
			}
		
		}

		return false;
	}

	public Color getEnemyColor(Color kingColor) {
		if (kingColor == Color.WHITE) {
			return Color.BLACK;
		} else {
			return Color.WHITE;
		}
	}

	public Coordinate getKingCoordinate(Color color) {
		Coordinate kingCoordinate = null;

		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				Coordinate currentCoordinate = new Coordinate(i, j);
				Piece currentPiece = board.getPieceAt(currentCoordinate);
				if (currentPiece != null && currentPiece.getType() == PieceType.KING
						&& currentPiece.getColor() == color) {
					kingCoordinate = currentCoordinate;
				}
			}
		}
		return kingCoordinate;
	}

	private boolean isAnyMoveValid(Color nextMoveColor) {

		ArrayList<PieceCoordinate> piecesWithCoordinates = getAllPiecesOfTheColor(nextMoveColor);

		for (int i = 0; i < piecesWithCoordinates.size(); i++) {

			for (int w = 0; w < Board.SIZE; w++) {
				for (int j = 0; j < Board.SIZE; j++) {
					try {
						validateMove(piecesWithCoordinates.get(i).getPosition(), new Coordinate(w, j));
						return true;
					} catch (KingInCheckException e) {
						continue;
					} catch (InvalidMoveException e) {
						continue;
					}
				}
			}
		}
		return false;
	}

	public ArrayList<PieceCoordinate> getAllPiecesOfTheColor(Color color) {
		ArrayList<PieceCoordinate> piecesWithItsCoordinates = new ArrayList<PieceCoordinate>();

		for (int i = 0; i < Board.SIZE; i++) {
			for (int j = 0; j < Board.SIZE; j++) {
				Coordinate currentCoordinate = new Coordinate(i, j);
				Piece currentPiece = board.getPieceAt(currentCoordinate);
				if (currentPiece != null && currentPiece.getColor() == color) {
					piecesWithItsCoordinates.add(new PieceCoordinate(currentPiece, currentCoordinate));
				}
			}
		}
		return piecesWithItsCoordinates;
	}

	public Color getPieceColor() {
		Color color = calculateNextMoveColor();
		return color;
	}

	private Color calculateNextMoveColor() {
		if (this.board.getMoveHistory().size() % 2 == 0) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}

	private int findLastNonAttackMoveIndex() {
		int counter = 0;
		int lastNonAttackMoveIndex = 0;

		for (Move move : this.board.getMoveHistory()) {
			if (move.getType() != MoveType.ATTACK) {
				lastNonAttackMoveIndex = counter;
			}
			counter++;
		}

		return lastNonAttackMoveIndex;
	}

}
