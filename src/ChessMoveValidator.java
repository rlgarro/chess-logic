import utils.*;
import board.BoardManager;
import utils.Response;

public class ChessMoveValidator {
    public ChessMoveValidator() {}

    public static Response getResponse(Request req) {
        return BoardManager.updateBoard(req);
    }
}