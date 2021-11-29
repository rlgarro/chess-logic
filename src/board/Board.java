package board;

import java.util.List;
import java.util.ArrayList;
import utils.Move;

public class Board {
    private String[][] board;   

    public Board(String[][] board) {
        this.board = board;
    }

    public String getPieceAt(int x, int y) {
        if (argsOutOfBoundary(x, y))
            return "invalid";

        return this.board[x][y];
    }

    public void setPieceAt(int x, int y, String value) {
        if (argsOutOfBoundary(x, y))
            return;

        this.board[x][y] = value;
    }

    public char getPieceByString(String pieceString) {
        if (pieceString.length() == 2)
            return pieceString.charAt(1);

        return pieceString.charAt(0);
    }

    public Move findKing(Boolean isWhite) {
        int x = 0;
        int y = 0;
        Move kingPos = new Move("", x, y);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String piece = this.getPieceAt(i, j);
                if (piece.equals("K") && isWhite) {
                    x = i;
                    y = j;
                    break;
                } else if (piece.equals("bK")){
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        return kingPos;
    }

    public static Move findRightDiagonalBase(int x, int y) {
        while ((x > 0 && y > 0)) {
            x -= 1;
            y -= 1;
        }
        
        return new Move("", x, y);
    }

    public List<Move> findHorsesSurrounding(int x, int y) {
        List<Move> horsesPositions = new ArrayList<>();

        int p1x = x+2;
        int p1y = y+2;

        // above piece at (x, y)
        if (this.getPieceAt(p1x, p1y-1).contains("k"))
            horsesPositions.add(new Move("", p1x, p1y-1));
        if (this.getPieceAt(p1x, p1y-3).contains("k"))
            horsesPositions.add(new Move("", p1x, p1y-3));
        if (this.getPieceAt(p1x-1, p1y).contains("k"))
            horsesPositions.add(new Move("", p1x-1, p1y));
        if (this.getPieceAt(p1x-1, p1y-4).contains("k"))
            horsesPositions.add(new Move("", p1x-1, p1y-4));
        
        // below piece at (x, y)
        if (this.getPieceAt(p1x-4, p1y-1).contains("k"))
            horsesPositions.add(new Move("", p1x-4, p1y-1));
        if (this.getPieceAt(p1x-4, p1y-3).contains("k"))
            horsesPositions.add(new Move("", p1x-4, p1y-3));
        if (this.getPieceAt(p1x-3, p1y).contains("k"))
            horsesPositions.add(new Move("", p1x-3, p1y));
        if (this.getPieceAt(p1x-3, p1y-4).contains("k"))
            horsesPositions.add(new Move("", p1x-3, p1y-4));


        return horsesPositions;
    }

    public static Move findLeftDiagonalBase(int x, int y) {
        while ((x > 0 && y < 8)) {
            x -= 1;
            y += 1;
        }

        return new Move("", x, y);
    }

    public static Boolean possibleByEnemyLocation(String pieceAt, Boolean isWhite) {

        if (isWhite) {
            boolean enemyOrEmpty = pieceAt.length() == 2 || pieceAt.equals("");
            if (enemyOrEmpty)
                return true;
            return false;
        } else {
            boolean enemyOrEmpty = pieceAt.length() == 1 || pieceAt.equals("");
            if (enemyOrEmpty)
                return true;
            return false;
        }
    }

    private boolean argsOutOfBoundary(int x, int y) {
        if ((x > 7 || x < 0) || (y > 7 || y < 7))
            return true;

        return false;
    }
}