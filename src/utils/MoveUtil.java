package src.util;

import java.lang.Math.abs;

/*
 * Class to check for general movement type collisions.
*/
public class MoveUtil {

  private final String UP_RIGHT_DIAGONAL = "upRightDiagonal";
  private final String DOWN_RIGHT_DIAGONAL = "downRightDiagonal";

  private final String UP_LEFT_DIAGONAL = "upLeftDiagonal";
  private final String DOWN_LEFT_DIAGONAL = "downLeftDiagonal";

  private final String UP = "upHorizontal";
  private final String DOWN = "downHorizontal";

  private final String RIGHT = "rightHorizontal";
  private final String LEFT = "leftHorizontal";

  /*
   * DIAGONALS
  */

  public static boolean upRight(Piece piece, Board board, int upIn, int rightIn) {

    for (int i = piece.getX(); i < upIn; i++) {
      for (int j = piece.getY(); j < rightIn; j++) {
        if (piece.pieceIsAlly(board, new Move(null, i, j))) {
          return false;
        }
      }
    }

    return true;
  }

  public static boolean upLeft(Piece piece, Board board, int upIn, int rightIn) {

    for (int i = piece.getX(); i < upIn; i++) {
      for (int j = piece.getY(); j > rightIn; j--) {
        if (piece.pieceIsAlly(board, new Move(null, i, j))) {
          return false;
        }
      }
    }

    return true;
  }

  public static boolean downRight(Piece piece, Board board, int upIn, int rightIn) {

    for (int i = piece.getX(); i > upIn; i++) {
      for (int j = piece.getY(); j < rightIn; j++) {
        if (piece.pieceIsAlly(board, new Move(null, i, j))) {
          return false;
        }
      }
    }

    return true;
  }

  public static boolean downLeft(Piece piece, Board board, int upIn, int rightIn) {

    for (int i = piece.getX(); i > upIn; i++) {
      for (int j = piece.getY(); j > rightIn; j--) {
        if (piece.pieceIsAlly(board, new Move(null, i, j))) {
          return false;
        }
      }
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

  private static boolean right(Piece piece, Board board, int yIn) {

    for (int i = piece.getY(); i < yIn; i++) {
      if (piece.pieceIsAlly(board, new Move(null, piece.getX(), i))
            return false;
    }

    return true;
  }

  private static boolean left(Piece piece, Board board, int yIn) {

    for (int i = piece.getY(); i > yIn; i++) {
      if (piece.pieceIsAlly(board, new Move(null, piece.getX(), i))
            return false;
    }

    return true;
  }

  private static boolean up(Piece piece, Board board, int xIn) {

    for (int i = piece.getY(); i > xIn; i++) {
      if (piece.pieceIsAlly(board, new Move(null, i, piece.getY()))
            return false;
    }

    return true;
  }

  private static boolean down(Piece piece, Board board, int yIn) {

    for (int i = piece.getY(); i < yIn; i++) {
      if (piece.pieceIsAlly(board, new Move(null, i, piece.getY())
            return false;
    }

    return true;
  }

  public static boolean calculatePossible(Piece piece, Board board, Move move) {
    String direction = getDirection(piece, board, move);

    if (direction == null)
      return false;

    int xIn = abs(piece.getX() - move.getX());
    int yIn = abs(piece.getY() - move.getY());

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

    return false;
  }

}








