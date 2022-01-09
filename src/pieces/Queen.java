package pieces;

import board.Board;
import utils.model.Move;
import utils.MoveUtil;

public class Queen extends Piece {

    @Override
    public boolean isPossibleMove(Board board, Move moveTo) {
        
        return MoveUtil.calculatePossible(this, board, moveTo);
    }

    public Queen(String name, Boolean isWhite, int x, int y) {
        super(name, isWhite, x, y);
        this.pieceNumber = isWhite ? 2 : -2;
    }

    @Override
    public boolean isValidMovement(Board board, Move moveTo) {
        return (MoveUtil.isDiagonal(this, moveTo)
                || MoveUtil.isHorizontal(this, moveTo)
                || MoveUtil.isVertical(this, moveTo));
    }
}
