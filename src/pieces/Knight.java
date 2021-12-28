package pieces;

import java.util.List;
import board.Board;
import utils.Move;

public class Knight extends Piece {


    @Override
    public Boolean isPossibleMove(Board board, Move moveTo) {
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
        this.pieceNumber = 4; 
    }

}
