package edu.gatech.seclass;

/**
 * This is an interface for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 * @author Rufus
 */
public interface MyCustomStringInterface {

    /**
     * Returns the current string. If the string is null, it should return null.
     *
     * @return Current string
     */
    String getString();

    /**
     * Sets the value of the current string.
     *
     * @param string The value to be set
     */
    void setString(String string);

    /**
     * Returns the number of numbers in the current string, where a number is defined as a
     * contiguous sequence of digits.
     *
     * If the current string is null, empty, or unset, the method should return 0.
     *
     * Examples:
     * - countNumbers would return 2 for string "My numbers are 11 and 96".
     *
     * @return Number of numbers in the current string
     */
    int countNumbers();

    /**
     * Returns either a string that consists of all characters in the original string except for the characters
     * in positions n, 2n, 3n, and so on, or only the characters in positions n, 2n, 3n, and so on. The
     * characters in the resulting string should be in the same order and with the same case as in the
     * current string.
     * <p>
     * <p>
     * Examples:
     * - For n=2 and returnN=true, the method would return the 2nd, 4th,
     * 6th, and so on characters in the string.
     * - For n=3 and returnN=false, the method would return the string without the 3rd, 6th,
     * 9th, and so on characters in the string.
     * <p>
     * Values n and returnN are passed as parameters. The starting character is considered to be in Position 1.
     *
     * @param n       Determines the positions of the characters to be returned
     * @param returnN Determines whether to return the Nth characters, or the string without the Nth characters.
     * @return String with remaining characters from original string
     * @throws NullPointerException        If the current string is null.
     * @throws MyIndexOutOfBoundsException If n is greater than the string length, and the current string is not null.
     * @throws IllegalArgumentException    If "n" less than or equal to zero, and the current string is not null.
     */
    String everyNthCharacter(int n, boolean returnN);

    /**
     * Replace the individual digits in the current string, between startPosition and endPosition
     * (included), with the corresponding English names of those digits, with no letters
     * capitalized. The first character in the string is considered to be in Position 1.
     * Contiguous digits will be surrounded by asterisks and each digit will be converted individually.
     *
     * Examples:
     * - String 460 would be converted to *foursixzero*
     * - String 416 would be converted to *fouronesix*
     *
     * @param startPosition Position of the first character to consider
     * @param endPosition   Position of the last character to consider

     * @throws IllegalArgumentException    If "startPosition" < 1 or "startPosition" > "endPosition"
     * @throws MyIndexOutOfBoundsException If "endPosition" is out of bounds (greater than the length of the string)
     * and 1 <= "startPosition" < "endPosition"
     */
    void convertDigitsToNamesInSubstring(int startPosition, int endPosition);
}
