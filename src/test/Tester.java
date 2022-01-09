package test;


import board.Board;
import utils.model.Move;
import utils.MoveUtil;
import utils.BoardReader;
import pieces.Piece;
import pieces.*;

class Tester {

  private static String ID = "upRight";
  private static String BOARD = "1-start.board.csv";

  public static void main(String[] args) {
  }

  public static void assertTest(boolean expected, Piece p, Board b, Move m, String ID) {
    if (b == null || p == null || m == null) {
      System.out.printf("TEST CASE: %s VERSAGT BEI NULL OBJEKTE\n", ID);
      return;
    }

    boolean passed = p.moveIsValid(b, m);
    if (passed == expected) 
      System.out.printf("TEST CASE: %s PASSIERT\n", ID);
    else
      System.out.printf("TEST CASE: %s VERSAGT\n", ID);
    
    System.out.printf("ERWARTET: %s TATSÄCHLICH: %s\n\n", expected, passed);
  }

} 

