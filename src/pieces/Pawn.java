package pieces;

import board.Board;
import utils.model.Move;

public class Pawn extends Piece {

    public Boolean moveIsValid() {
        
        return false;
    }   

    @Override
    public boolean isPossibleMove(Board board, Move moveTo) {
        Boolean possible = false;

        int xTo = moveTo.getX();
        int yTo = moveTo.getY();

        if(Board.argsOutOfBoundary(xTo, yTo))
          return false;

        // comparing if it follows the same movement a pawn does, based on its color
        if (this.isWhite) {
            Boolean pieceAtIsBlack = board.getPieceAt(xTo, yTo).length() == 2;
            if (xTo == this.x+1 && yTo == this.y)
                return true;
            else if ((xTo == this.x+1 && yTo == this.y-1) && pieceAtIsBlack)
                return true;
            else if ((xTo == this.x+1 && yTo == this.y+1) && pieceAtIsBlack)
                return true;
        } else {
            Boolean pieceAtIsWhite = board.getPieceAt(xTo, yTo).length() == 1;
            if (xTo == this.x-1 && yTo == this.y)
                return true;
            else if ((xTo == this.x-1 && yTo == this.y-1) && pieceAtIsWhite)
                return true;
            else if ((xTo == this.x-1 && yTo == this.y+1) && pieceAtIsWhite)
                return true;
        }

        return possible;
    }

    public Pawn(String name, Boolean isWhite, int x, int y) {
        super(name, isWhite, x, y);
        this.pieceNumber = isWhite ? 6 : -6;
    }

    @Override
    public boolean isValidMovement(Board board, Move moveTo) {
        // wir werden hier nicht bei Farben validieren.
        int absX = x - moveTo.getX();
        int absY = y - moveTo.getY();
        return (absX == 2 || absX == 1 || absX == 0) && (absY == 1 || absY == 0);
    }

}
