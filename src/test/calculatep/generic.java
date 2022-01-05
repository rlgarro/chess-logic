// import stuff.

class Test {

  private final String ID = "01-upRight.java";

  public static void main(String[] args) {
    // test variables
    Board b = null;
    Move  m = null;
    Piece p = null;

    System.out.println(assertTest(p, b, m));

  }

  public static void assertTest(Piece p, Board b, Move m) {
    boolean passed = calculatePossibleMove(p, b, m);
    if (passed)
      System.out.printf("TEST CASE: %s PASSED\n", ID);
    else
      System.out.printf("TEST CASE: %s FAILED\n", ID);
  }

} 

