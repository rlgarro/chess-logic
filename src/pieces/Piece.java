package pieces;

import board.Board;
import utils.model.Move;

public abstract class Piece {
    protected Boolean isWhite;
    protected int x;
    protected int y;
    protected String name;
    protected int pieceNumber = -1;

    public Boolean moveIsValid(Board board, Move move) {

        Move kingPos = board.findKing(this.isWhite);
        Boolean kingIsUnprotected = false;
        try {
            kingIsUnprotected = King.kingIsUnprotected(board, kingPos);
        } catch(IllegalMoveException e) {
            return false;
        }
        
        boolean pieceAtLandingIsAlly = this.pieceIsAlly(board, move);
        if (pieceAtLandingIsAlly)
            return false;
        
        if (this.isAtSamePosition(move))
            return false;

        if (!this.isValidMovement(board, move))
            return false;

        if (this.isPossibleMove(board, move) && !kingIsUnprotected)
            return true;

        return false;
    }

    public abstract boolean isValidMovement(Board board, Move moveTo);

    public abstract boolean isPossibleMove(Board board, Move moveTo);

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

    public boolean equals(int n) {
      return n == this.pieceNumber;
    }

    public boolean isWhite() {
      return isWhite;
    }

    public int getPieceNumber() {
        return pieceNumber;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
