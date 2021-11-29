package pieces;

import board.Board;
import utils.Move;

public class Queen extends Piece {

    @Override
    public Boolean isPossibleMove(Board board, Move moveTo) {
        
        boolean pieceAtLandingIsAlly = this.pieceIsAlly(board, moveTo);
        if (pieceAtLandingIsAlly)
            return false;
        
        if (this.isAtSamePosition(moveTo))
            return false;
        
        // calculate to the right
        if (moveTo.getX() > this.x) {
            // horizontally
            if (moveTo.getY() == this.y) {
                for (int i = this.x+1; i < 8; i++) {

                }
            }
        } else {

        }

        return false;
    }

    public Queen(String name, Boolean isWhite, int x, int y) {
        super(name, isWhite, x, y);
    }
}