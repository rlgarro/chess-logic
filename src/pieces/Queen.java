package pieces;

import board.Board;
import utils.model.Move;
import utils.MoveUtil;

public class Queen extends Piece {

    @Override
    public Boolean isPossibleMove(Board board, Move moveTo) {
        
        boolean pieceAtLandingIsAlly = this.pieceIsAlly(board, moveTo);
        if (pieceAtLandingIsAlly)
            return false;
        
        if (this.isAtSamePosition(moveTo))
            return false;

        return MoveUtil.calculatePossible(this, board, moveTo);
    }

    public Queen(String name, Boolean isWhite, int x, int y) {
        super(name, isWhite, x, y);
    }
}
