import board.BoardManager;
import utils.model.Response;
import utils.model.Request;

public class ChessMoveValidator {
    public ChessMoveValidator() {}

    public static Response getResponse(Request req) {
        return BoardManager.updateBoard(req);
    }
}