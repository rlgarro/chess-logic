package pieces;

import board.Board;
import utils.model.Move;
import utils.MoveUtil;

public class Bishop extends Piece {

    public Boolean moveIsValid() {
        
        return false;
    }   

    public Bishop(String name, Boolean isWhite, int x, int y) {
        super(name, isWhite, x, y);
        this.pieceNumber = isWhite ? 3 : -3;
    }

    @Override
    public boolean isPossibleMove(Board board, Move moveTo) {
        return MoveUtil.calculatePossible(this, board, moveTo);
    }

    @Override
    public boolean isValidMovement(Board board, Move moveTo) {
        return MoveUtil.isDiagonal(this, moveTo);
    }

}
