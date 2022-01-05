package utils.model;

import board.Board;

public class Response {
    private String message;
    private Board board;

    public Response(String message, Board board) {
        this.message = message;
        this.board = board;
    }

    public Response() {
    }

    public Board getBoard() {
        return this.board;
    }

    public String getMessage() {
        return this.message;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
