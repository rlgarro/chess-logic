package utils.model;

public class Move {
    private String move;   
    private int x;
    private int y;
    private Boolean isWhite;

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Boolean isWhite() {
        return this.isWhite;
    }

    public String getMoveName() {
        return move;
    }

    public Move(String move, int x, int y) {
        this.move = move;
        this.x = x;
        this.y = y;
    }
}
