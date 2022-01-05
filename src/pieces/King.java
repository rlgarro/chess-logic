package pieces;

import java.util.List;

import board.Board;
import utils.model.Move;

public class King extends Piece {

    public Boolean moveIsValid() {
        
        return false;
    }   

    public King(String name, Boolean isWhite, int x, int y) {
        super(name, isWhite, x, y);
        this.pieceNumber = isWhite ? 1 : -1;
    }

    public static Boolean kingIsUnprotected(Board board, Move kingPosition) throws IllegalMoveException {
        if (!(pieceIsKing(board, kingPosition))) {
            throw new IllegalMoveException("King is not present at position suggested.");
        }

        return kingBeingAttacked(board, kingPosition);
    }

    private static Boolean kingBeingAttacked(Board board, Move kingPos) {
        int kingX = kingPos.getX();
        int kingY = kingPos.getX();

        boolean isKingWhite = (board.getPieceAt(kingX, kingY)).length() == 1;

        //
        // check in same row
        if (attackedByXorY(board, kingPos, isKingWhite))
            return true;
        
        // check left diagonal
        else if (attackedByLeftDiagonal(board, kingPos, isKingWhite))
            return true;

        // check right diagonal
        else if (attackedByRightDiagonal(board, kingPos, isKingWhite))
            return true;
        
        // by horses
        else if (attackedByHorses(board, kingPos, isKingWhite))
            return true;

        /* for each color
        //
        if (board.getPieceAt(kingX, kingY).isWhite == false) {

            // check in same row
            if (attackedByXorY(board, kingPos, false))
                return true;
            
            // check left diagonal
            else if (attackedByLeftDiagonal(board, kingPos, false))
                return true;

            // check right diagonal
            else if (attackedByRightDiagonal(board, kingPos, false))
                return true;
            
            // by horses
            else if (attackedByHorses(board, kingPos, false))
                return true;

        } else {

            // check in same row
            if (attackedByXorY(board, kingPos, true))
                return true;
            
            // check left diagonal
            else if (attackedByLeftDiagonal(board, kingPos, true))
                return true;

            // check right diagonal
            else if (attackedByRightDiagonal(board, kingPos, true))
                return true;
            
            // by horses
            else if (attackedByHorses(board, kingPos, true))
                return true;
        }
        */

        return false;
    }

    private static Boolean attackedByXorY(Board board, Move kingPos, Boolean isWhite) {
        Boolean attackedLeft = false;

        Boolean attackedRight = false;
        Boolean foundRight = false;

        Boolean attackedUp = false;
        Boolean foundUp = false;

        Boolean attackedDown = false;

        // to know if being attacked horizontally or vertically
        for (int i = 0; i < 8; i++) {
            String pieceHor = board.getPieceAt(kingPos.getX(), i);
            String pieceVer = board.getPieceAt(i, kingPos.getY());

            if (pieceHor == null || pieceVer == null)
              return false;

            // before king's position (vertically)
            if (i < kingPos.getX()) {
                attackedDown = attackedByPiece(isWhite, pieceVer, "r");
            }

            // after king's position (vertically)
            else if (i > kingPos.getX() && !pieceVer.equals("")) {
                if (!foundUp) {
                    attackedUp = attackedByPiece(isWhite, pieceVer, "r");
                    foundUp = true;
                }
            }

            Boolean isBelow = i < kingPos.getY();
            // before king's position (horizontally)
            if (isBelow) {
                attackedLeft = attackedByPiece(isWhite, pieceHor, "r");
            }

            // after king's position (horizontally)
            else if (!isBelow && !pieceHor.equals("") && !foundRight) {
                attackedRight = attackedByPiece(isWhite, pieceHor, "r");
                foundRight = true;
            }
        } 

        Boolean beingAttacked = attackedUp || attackedDown ||
                                  attackedLeft || attackedRight;

        return beingAttacked;
    }

    private static Boolean attackedByRightDiagonal(Board board, Move kingPos, Boolean isWhite) {

        int kingX = kingPos.getX();
        int kingY = kingPos.getY();

        if (attackedByPawn(board, isWhite, kingX, kingY))
            return true;

        Move diagonalBase = Board.findRightDiagonalBase(kingX, kingY);

        int x = diagonalBase.getX();
        int y = diagonalBase.getY();

        Boolean attackedAbove = false;
        Boolean foundAbove = false;

        Boolean attackedBelow = false;

        for (int i = 0; i < 8; i++) {
            if (x == 8 || y == 8)
                break;
            
            if (x == kingX)
                continue;

            String piece = board.getPieceAt(x, y);

            if (piece == null)
              return false;

            Boolean isBelow = x < kingX;

            // last piece decides whether attacked or not
            if (isBelow) {
                attackedBelow = attackedByPiece(isWhite, piece, "b");
            }

            // after king, first piece decide whether attacked or not
            else if (!isBelow && !piece.equals("") && !foundAbove) {
                attackedAbove = attackedByPiece(isWhite, piece, "b");
                foundAbove = true;
            }
            x++;
            y++;
        }
        return (attackedBelow || attackedAbove);
    }

    private static Boolean attackedByLeftDiagonal(Board board, Move kingPos, Boolean isWhite) {

        int kingX = kingPos.getX();
        int kingY = kingPos.getY();

        if (attackedByPawn(board, isWhite, kingX, kingY))
            return true;

        Move diagonalBase = Board.findLeftDiagonalBase(kingX, kingY);

        int x = diagonalBase.getX();
        int y = diagonalBase.getY();

        Boolean attackedAbove = false;
        Boolean foundAbove = false;

        Boolean attackedBelow = false;

        for (int i = 0; i < 8; i++) {
            if (x == 8 || y == -1)
                break;

            if (x == kingX)
                continue;

            String piece = board.getPieceAt(x, y);

            if (piece == null)
              return false;

            Boolean isBelow = x < kingX;

            if (isBelow) {
                attackedBelow = attackedByPiece(isWhite, piece, "b");
            } else if (!isBelow && !piece.equals("") && !foundAbove) {
                attackedAbove = attackedByPiece(isWhite, piece, "b");
                foundAbove = true;
            }
            x++;
            y--;
        }

        return (attackedAbove || attackedBelow);
    }

    private static Boolean attackedByHorses(Board board, Move kingPos, Boolean isWhite) {
        Boolean attackedByHorses = false;
        int x = kingPos.getX();
        int y = kingPos.getY();

        List<Move> horsesPositions = board.findHorsesSurrounding(x, y);
        if (horsesPositions.isEmpty())
            return false;
        
        for (Move horsePos : horsesPositions) {
            if (isWhite) {
                if (board.isBlackKnightAt(horsePos.getX(), horsePos.getY())) {
                    attackedByHorses = true;
                    break;
                }
            } else {
                if (board.isKnightAt(horsePos.getX(), horsePos.getY())) {
                    attackedByHorses = true;
                    break;
                }
            }
        }

        return attackedByHorses;
    }

    private static Boolean attackedByPawn(Board board, Boolean isWhite, int x, int y) {

        // check whether there's a pawn below or above king
        String pieceAbove = board.getPieceAt((int)(x+1), (int)(y+1));
        String pieceBelow = board.getPieceAt((int)(x-1), (int)(y-1));

        if (pieceAbove == null || pieceBelow == null)
          return false;

        if (isWhite && pieceAbove.equals("bp")) {
            return true;
        } else if (!isWhite && pieceBelow.equals("p"))
            return true;

        return false;
    }

    private static Boolean attackedByPiece(Boolean isWhite, String piece, String threatPiece) {
        Boolean attacked = false;
        if (isWhite) {
            if (piece.equals("bq") || piece.equals("b"+threatPiece))
                attacked = true;
            else
                attacked = false;
        } else {
            if (piece.equals("q") || piece.equals(threatPiece))
                attacked = true;
            else
                attacked = false;
        }
        return attacked;
    }

    public static Boolean pieceIsKing(Board board, Move kingPosition) {
        String piece = board.getPieceAt(kingPosition.getX(), kingPosition.getY());

        if (piece == null)
          return false;

        if (!(piece.equals("K")) || !(piece.equals("bK")) || piece.equals("invalid")) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean isPossibleMove(Board board, Move moveTo) {
        int xTo = moveTo.getX();
        int yTo = moveTo.getY();
        String pieceAt = board.getPieceAt(xTo, yTo);

        if (pieceAt == null)
          return false;

        if (xTo == this.x+1 || xTo == this.x-1) {
            if (yTo == this.y)
                return Board.possibleByEnemyLocation(pieceAt, isWhite);

            else if (yTo == this.y+1)
                return Board.possibleByEnemyLocation(pieceAt, isWhite);

            else if (yTo == this.y-1)
                return Board.possibleByEnemyLocation(pieceAt, isWhite);

            return false;
        } else if (xTo == this.x) {

            if (yTo == this.y+1 || yTo == this.y-1) {
                return Board.possibleByEnemyLocation(pieceAt, isWhite);
            }
            return false;
        }
        return false;
    }
}
