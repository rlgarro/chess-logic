package board;

import pieces.IllegalMoveException;
import pieces.*;
import utils.*;

public class BoardManager {
    public BoardManager() {}

    public static Response updateBoard(Request request) {
        Board board = request.getBoard();
        Move move = request.getMove();
        String pawnReplacement = request.getPawnReplacement();

        return createResponseFromMove(move, board, pawnReplacement);
    }

    private static Response createResponseFromMove(Move move, Board board, String pawnReplacement) {
        Response response = new Response();
        try {
            Piece piece = getPieceFromMove(move, board);

            if (piece.moveIsValid(board, move)) {

                // whether or not a coronation
                if (pawnReplacement.equals(""))
                    board.setPieceAt(move.getX(), move.getY(), move.getMoveName());

                else
                    board.setPieceAt(move.getX(), move.getY(), pawnReplacement);

                return new Response("Board updated", board);
            } 

            return new Response("Invalid move", board);

        } catch (IllegalMoveException exc) {
            String message = "Invalid request";
            response.setMessage(message);
            response.setBoard(board);
        }

        return response;
    }

    private static Piece getPieceFromMove(Move move, Board board) throws IllegalMoveException {
        int x = move.getX();
        int y = move.getY();
        String piece = board.getPieceAt(x, y);
        Boolean isWhite = false; 

        if (piece.equals("invalid"))
            throw new IllegalMoveException("invalid move");
        
        else {
            isWhite = piece.charAt(0) != 'b' ? true : false;
            switch (piece.charAt(1)) {
                case 'b':
                    return new Bishop("", isWhite, x, y);
                case 'k':
                    return new Knight("", isWhite, x, y);
                case 'q':
                    return new Queen("", isWhite, x, y);
                case 'K':
                    return new King("", isWhite, x, y);
                case 'r':
                    return new Rook("", isWhite, x, y);
            }
        }

        return new Pawn("", isWhite, x, y);
    }
}
