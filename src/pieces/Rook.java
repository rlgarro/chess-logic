package pieces;

import board.Board;
import utils.model.Move;
import utils.MoveUtil;

public class Rook extends Piece {
    private Boolean isWhite;
    private int xPosition;
    private int yPosition;
    private String name;

    public Boolean moveIsValid() {
        
        return false;
    }   

    public Rook(String name, Boolean isWhite, int x, int y) {
        super(name, isWhite, x, y);
        this.pieceNumber = isWhite ? 5 : -5;
    }

    @Override
    public boolean isPossibleMove(Board board, Move moveTo) {
        return MoveUtil.calculatePossible(this, board, moveTo);
    }

    @Override
    public boolean isValidMovement(Board board, Move moveTo) {
        return MoveUtil.isHorizontal(this, moveTo) || MoveUtil.isVertical(this, moveTo);
    }
}
