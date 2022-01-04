package src.pieces;

public class LetterToNumberConversor {

    public LetterToNumberConversor() {
    }

    public static short convertLetterToNumber(char letter) {
        short asciiValueOfLetter = (short) letter;
        return (short) (asciiValueOfLetter-96);
    }
}
