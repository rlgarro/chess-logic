package pieces;

import board.Board;
import utils.model.Move;

public class Bishop extends Piece {

    public Boolean moveIsValid() {
        
        return false;
    }   

    public Bishop(String name, Boolean isWhite, int x, int y) {
        super(name, isWhite, x, y);
        this.pieceNumber = isWhite ? 3 : -3;
    }

    @Override
    public Boolean isPossibleMove(Board board, Move moveTo) {
        // TODO Auto-generated method stub
        return null;
    }

}
