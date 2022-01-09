package utils;

import utils.model.Move;
import java.lang.Math;
import pieces.Piece;
import pieces.Queen;
import board.Board;


/*
 * Class to check for general movement type collisions.
*/
public class MoveUtil {

  private static final String UP_RIGHT_DIAGONAL = "upRightDiagonal";
  private static final String DOWN_RIGHT_DIAGONAL = "downRightDiagonal";

  private static final String UP_LEFT_DIAGONAL = "upLeftDiagonal";
  private static final String DOWN_LEFT_DIAGONAL = "downLeftDiagonal";

  private static final String UP = "upHorizontal";
  private static final String DOWN = "downHorizontal";

  private static final String RIGHT = "rightHorizontal";
  private static final String LEFT = "leftHorizontal";

  /*
   * DIAGONALS
  */

  public static boolean upRight(Piece piece, Board board, int upIn, int rightIn) {

    int y = piece.getY() + 1;
    for (int i = piece.getX() + 1; i < upIn; i++) {
      if (piece.pieceIsAlly(board, new Move(null, i, y))) {
        return false;
      }
      y++;
    }

    return true;
  }

  public static boolean upLeft(Piece piece, Board board, int upIn, int rightIn) {

    int y = piece.getY() - 1;
    for (int i = piece.getX() + 1; i < upIn; i++) {
      if (piece.pieceIsAlly(board, new Move(null, i, y))) {
        return false;
      }
      y--;
    }

    return true;
  }

  public static boolean downRight(Piece piece, Board board, int upIn, int rightIn) {

    int y = piece.getY() + 1;
    for (int i = piece.getX() - 1; i < upIn; i++) {
      if (piece.pieceIsAlly(board, new Move(null, i, y))) {
        return false;
      }
      y++;
    }

    return true;
  }

  public static boolean downLeft(Piece piece, Board board, int upIn, int rightIn) {

    int y = piece.getY() - 1;
    for (int i = piece.getX() - 1; i < upIn; i++) {
      if (piece.pieceIsAlly(board, new Move(null, i, y))) {
        return false;
      }
      y--;
    }

    return true;
  }
  
  public static boolean isDiagonal(Piece piece, Move move) {
    return Math.abs(piece.getX() - move.getX()) == Math.abs(piece.getY() - move.getY());
  }

  private static boolean right(Piece piece, Board board, int yIn) {

    for (int i = piece.getY() + 1; i < yIn; i++) {
      if (piece.pieceIsAlly(board, new Move(null, piece.getX(), i)))
            return false;
    }

    return true;
  }

  private static boolean left(Piece piece, Board board, int yIn) {

    for (int i = piece.getY() - 1; i > yIn; i--) {
      if (piece.pieceIsAlly(board, new Move(null, piece.getX(), i)))
            return false;
    }

    return true;
  }

  private static boolean up(Piece piece, Board board, int xIn) {

    for (int i = piece.getX() + 1; i < xIn; i++) {
      if (piece.pieceIsAlly(board, new Move(null, i, piece.getY())))
            return false;
    }

    return true;
  }

  private static boolean down(Piece piece, Board board, int yIn) {

    for (int i = piece.getY() - 1; i > yIn; i--) {
      if (piece.pieceIsAlly(board, new Move(null, i, piece.getY())))
            return false;
    }

    return true;
  }

  
  private static String getDirection(Piece piece, Board board, Move move) {

    int vertic = move.getY() - piece.getY();
    int horiz = move.getX() - piece.getX() ;

    if (vertic == 0 && horiz == 0)
      return null;

    if (vertic == 0) {
        if (horiz > 0)
          return RIGHT;
        else
          return LEFT;
    } 

    else if (vertic > 0) {
      if (horiz == 0)
        return UP;
      else if (horiz > 0)
        return UP_RIGHT_DIAGONAL;
      else
        return DOWN_RIGHT_DIAGONAL;
    }

    else if (vertic < 0) {
      if (horiz == 0)
        return DOWN;
      else if (horiz > 0)
        return DOWN_RIGHT_DIAGONAL;
      else
        return DOWN_LEFT_DIAGONAL;
    }

    return null;

  }

  public static boolean calculatePossible(Piece piece, Board board, Move move) {
    String direction = getDirection(piece, board, move);

    if (direction == null)
      return false;

    int xIn = Math.abs(piece.getX() - move.getX());
    int yIn = Math.abs(piece.getY() - move.getY());

    System.out.println("Calculating possible move");
    System.out.printf("From x: %s y: %s To x: %s y: %s", piece.getX(), piece.getY(), move.getX(), move.getY());
    System.out.println("Direction: " + direction);
    
    System.out.printf("xIn: %s yIn: %s\n", xIn, yIn);
    System.out.printf("xP: %s yP: %s\n", piece.getX(), piece.getY());
    System.out.println("DIRECTION: " + direction);
    if (direction.equals(UP_RIGHT_DIAGONAL))
      return upRight(piece, board, xIn, yIn);

    else if (direction.equals(DOWN_RIGHT_DIAGONAL))
      return downRight(piece, board, xIn, yIn);

    else if (direction.equals(UP_LEFT_DIAGONAL))
      return upLeft(piece, board, xIn, yIn);

    else if (direction.equals(DOWN_LEFT_DIAGONAL))
      return downLeft(piece, board, xIn, yIn);

    else if (direction.equals(RIGHT))
      return right(piece, board, yIn);

    else if (direction.equals(LEFT))
      return left(piece, board, yIn);

    else if (direction.equals(UP))
      return up(piece, board, xIn);

    else
      return down(piece, board, xIn);

  }

  public static boolean isHorizontal(Piece piece, Move moveTo) {
    return piece.getX() == moveTo.getX();
  }

  public static boolean isVertical(Piece piece, Move moveTo) {
    return piece.getY() == moveTo.getY();
  }

}
