package test.calculatep;

import board.Board;
import utils.model.Move;
import utils.BoardReader;
import pieces.Piece;
import pieces.*;

class Test {

  private static final String ID = "01-upRight.java";
  private static final String BOARDS_DIR = "";

  public static void main(String[] args) {
    // test variables
    Board b = BoardReader.getBoardFromFile("1-start.board.csv");
    Move  m = new Move(null, 5, 5);
    Piece p = new Queen(null, true, 0, 3);

    System.out.println(assertTest(p, b, m));

  }

  public static void assertTest(Piece p, Board b, Move m) {
    boolean passed = MoveUtil.calculatePossibleMove(p, b, m);
    if (passed)
      System.out.printf("TEST CASE: %s PASSED\n", Test.ID);
    else
      System.out.printf("TEST CASE: %s FAILED\n", Test.ID);
  }

} 

