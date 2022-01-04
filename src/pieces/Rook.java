package src.pieces;

import src.board.Board;
import src.utils.Move;

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
    public Boolean isPossibleMove(Board board, Move moveTo) {
        // TODO Auto-generated method stub
        return null;
    }
}
