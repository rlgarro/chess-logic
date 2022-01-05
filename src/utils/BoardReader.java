package utils;

import java.io.FileReader;
import java.io.BufferedReader;

import board.Board;

public class BoardReader {

  public static Board getBoardFromFile(String fileName) {

    String[][] boardArr = new String[8][8];
    
    // file format must be an eight liner (8x8) separated by commas.
    try {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String line = "";
      String[] linePieces = new String[8];
      for (int i = 7; i >= 0; i--) {
        line = reader.readLine(); 
        linePieces = line.split(",");
        for (int j = 0; j < 8; j++) {
          boardArr[i][j] = linePieces[j];
          System.out.printf("i: %s - j: %s \n", i, j);
        }
      }

      reader.close();

    } catch (Exception e) {
        System.out.println("####### ERROR WHILE GETTING BOARD FROM FILE #######");
        return null;
    }
     
    return new Board(boardArr);
  }

}
