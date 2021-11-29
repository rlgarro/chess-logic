package pieces;

import board.Board;
import utils.Move;

public abstract class Piece {
    protected Boolean isWhite;
    protected int x;
    protected int y;
    protected String name;

    public Boolean moveIsValid(Board board, Move move) {

        Move kingPos = board.findKing(this.isWhite);
        Boolean kingIsUnprotected = false;
        try {
            kingIsUnprotected = King.kingIsUnprotected(board, kingPos);
        } catch(IllegalMoveException e) {
            return false;
        }

        if (this.isPossibleMove(board, move) && !kingIsUnprotected)
            return true;

        return false;
    }

    public abstract Boolean isPossibleMove(Board board, Move moveTo);

    /*
    public static Boolean pieceIsAlly(Boolean isWhite, String piece) {
        if (isWhite && piece.length() == 2)
            return false;

        return true;
    }
    */

    public Boolean isAtSamePosition(Move moveTo) {
        if (moveTo.getX() == this.x && moveTo.getY() == this.y) {
            return true;
        }
        
        return false;
    }

    public Boolean pieceIsAlly(Board board, Move pieceLocation) {
        String piece = board.getPieceAt(pieceLocation.getX(), pieceLocation.getY());

        if (this.isWhite && piece.length() == 1)
            return true;
        return false;
    }

    public Piece(String name, Boolean isWhite, int x, int y) {
        this.name = name;
        this.isWhite = isWhite;
        this.x = x;
        this.y = y;
    }
}