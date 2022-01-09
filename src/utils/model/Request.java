package utils.model;

import board.Board;

public class Request {
    private Board board;
    private Move move;
    private String pawnReplacementPiece;

    public Request(Board board, Move move) {
        this.board = board;
        this.move = move;
        this.pawnReplacementPiece = null;
    }

    public Request(Board board, Move move, String pawnRep) {
        this.board = board;
        this.move = move;
        this.pawnReplacementPiece = pawnRep;
    }

    public String getPawnReplacement() {
        return this.pawnReplacementPiece;
    }

    public Board getBoard() {
        return this.board;
    }

    public Move getMove() {
        return this.move;
    }
    
}