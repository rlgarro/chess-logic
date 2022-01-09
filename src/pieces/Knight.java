package pieces;

import java.util.List;
import board.Board;
import utils.model.Move;

public class Knight extends Piece {


    @Override
    public boolean isPossibleMove(Board board, Move moveTo) {
        Boolean isPossible = false;

        // inverse check if the point has the actual position of the horse
        List<Move> horses = board.findHorsesSurrounding(moveTo.getX(), moveTo.getY()); 
        if (horses.isEmpty())
            return false;

        for (Move horse : horses) {
            boolean horsePositionMatches = horse.getX() == this.x && horse.getY() == this.y;

            if (horsePositionMatches) {
                String pieceAt = board.getPieceAt(moveTo.getX(), moveTo.getY());
                return Board.possibleByEnemyLocation(pieceAt, this.isWhite);
            }
        }

        return isPossible;
    }

    public Knight(String name, Boolean isWhite, int x, int y) {
        super(name, isWhite, x, y);
        this.pieceNumber = isWhite ? 4 : -4; 
    }

    @Override
    public boolean isValidMovement(Board board, Move moveTo) {
        int xTo = moveTo.getX();
        int yTo = moveTo.getY();

        if (Math.abs(x - xTo) == 2 && Math.abs(y - yTo) == 1)
            return true;
            
        else if (Math.abs(x - xTo) == 1 && Math.abs(y - yTo) == 2)
            return true;

        return false;
    }

}
