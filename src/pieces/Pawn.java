package pieces;

import board.Board;
import utils.model.Move;

public class Pawn extends Piece {

    public Boolean moveIsValid() {
        
        return false;
    }   

    @Override
    public Boolean isPossibleMove(Board board, Move moveTo) {
        Boolean possible = false;

        int xTo = moveTo.getX();
        int yTo = moveTo.getY();

        if(Board.argsOutOfBoundary(xTo, yTo))
          return false;

        // comparing if it follows the same movement a pawn does, based on its color
        if (this.isWhite) {
            Boolean pieceAtIsBlack = board.getPieceAt(xTo, yTo).length() == 2;
            if (xTo == this.x+1 && yTo == this.y)
                possible = true;
            else if ((xTo == this.x+1 && yTo == this.y-1) && pieceAtIsBlack)
                possible = true;
            else if ((xTo == this.x+1 && yTo == this.y+1) && pieceAtIsBlack)
                possible = true;
        } else {
            Boolean pieceAtIsWhite = board.getPieceAt(xTo, yTo).length() == 1;
            if (xTo == this.x-1 && yTo == this.y)
                possible = true;
            else if ((xTo == this.x-1 && yTo == this.y-1) && pieceAtIsWhite)
                possible = true;
            else if ((xTo == this.x-1 && yTo == this.y+1) && pieceAtIsWhite)
                possible = true;
        }

        return possible;
    }

    public Pawn(String name, Boolean isWhite, int x, int y) {
        super(name, isWhite, x, y);
        this.pieceNumber = isWhite ? 6 : -6;
    }

}
