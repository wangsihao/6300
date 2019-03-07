package edu.gatech.seclass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class MyCustomStringTest {

    private MyCustomStringInterface mycustomstring;

    @Before
    public void setUp() {
        mycustomstring = new MyCustomString();
    }

    @After
    public void tearDown() {
        mycustomstring = null;
    }

    /*Test whether the method countNumbers returns 7 for a string has 7 numbers
    */
    @Test
    public void testCountNumbers1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals(7, mycustomstring.countNumbers());
    }

    /*Test whether the method countNumbers returns 1 for a string that has 1 number and is 1 character long
    */
    @Test
    public void testCountNumbers2() {
        mycustomstring.setString("8");
        assertEquals(1, mycustomstring.countNumbers());
    }

    /*Test whether the method countNumbers returns 2 for a string that has 2 numbers and ends with a set of digits
    */
    @Test
    public void testCountNumbers3() {
        mycustomstring.setString("I break up with my ex in 2014 and meet my wife in 2015");
        assertEquals(2, mycustomstring.countNumbers());
    }

    /*Test whether the method countNumbers returns 0 for a string that is empty
    */
    @Test
    public void testCountNumbers4() {
        mycustomstring.setString("");
        assertEquals(0, mycustomstring.countNumbers());
    }

    /*Test whether the method countNumbers returns 0 for a string that is null
     */
    @Test
    public void testCountNumbers5() {
        mycustomstring.setString(null);
        assertEquals(0, mycustomstring.countNumbers());
    }

    /*Test whether the method countNumbers returns 0 for a string that has no numbers
    */
    @Test
    public void testCountNumbers6() {
        mycustomstring.setString("Zero numbers");
        assertEquals(0, mycustomstring.countNumbers());
    }

    /*Test whether the method everyNthCharacter returns a string that had every 3rd character removed
    */
    @Test
    public void testeveryNthCharacter1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("I' bttr uts0e 16tsinths trn6 rgh?", mycustomstring.everyNthCharacter(3, false));
    }

    /*Test whether the method everyNthCharacter returns a string that had every 3rd character
    */
    @Test
    public void testeveryNthCharacter2() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        assertEquals("d33p md1  i51,it", mycustomstring.everyNthCharacter(3, true));
    }

    /*Test whether the method everyNthCharacter returns a string that had every 2nd character removed
    */
    @Test
    public void testeveryNthCharacter3() {
        mycustomstring.setString("Gatech");
        assertEquals("Gtc", mycustomstring.everyNthCharacter(2, false));
    }

    /*Test whether the method everyNthCharacter returns a string that had every 5th character replaced with a space
     */
    @Test
    public void testeveryNthCharacter4() {
        mycustomstring.setString("This string maintains spacing");
        assertEquals("Thisstrig mantais spcing", mycustomstring.everyNthCharacter(5, false));
    }

    /*Test whether the method everyNthCharacter throws a MyIndexOutOfBoundsException if n is greater than the length of
      the string
     */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testeveryNthCharacter5() {
        mycustomstring.setString("Test out of bounds");
        assertEquals("", mycustomstring.everyNthCharacter(50, false));
    }

    /*Test whether the method eeryNthCharacter throws an IllegalArgumentException if n is less than 1
    */
    @Test(expected = IllegalArgumentException.class)
    public void testeveryNthCharacter6() {
        mycustomstring.setString("Test negative n");
        assertEquals("", mycustomstring.everyNthCharacter(-2, false));
    }

    /*Test whether the method everyNthCharacter throws an IllegalArgumentException if n is less than 1
    */
    @Test(expected = IllegalArgumentException.class)
    public void testeveryNthCharacter7() {
        mycustomstring.setString("Test n == 0");
        assertEquals("", mycustomstring.everyNthCharacter(0, true));
    }

    /*Test whether the method everyNthCharacter sets the string to empty if it is null and then throws an
      NullPointerException.
    */
    @Test(expected = NullPointerException.class)
    public void testeveryNthCharacter8() {
        mycustomstring.setString(null);
        assertEquals("", mycustomstring.everyNthCharacter(0, true));
    }

    /*Test whether the method everyNthCharacter removes every character.
    */
    @Test
    public void testeveryNthCharacter9() {
        mycustomstring.setString("gatech");
        assertEquals("", mycustomstring.everyNthCharacter(1, false));
    }

    /*Test whether the method everyNthCharacter returns every character.
    */
    @Test
    public void testeveryNthCharacter10() {
        mycustomstring.setString("gatech");
        assertEquals("gatech", mycustomstring.everyNthCharacter(1, true));
    }

    /*Test whether the method everyNthCharacter sets the string to a single word
    */
    @Test
    public void testeveryNthCharacter11() {
        mycustomstring.setString("gatech");
        assertEquals("c", mycustomstring.everyNthCharacter(5, true));    }

    /*Test whether the method everyNthCharacter removes every 5th character in a string with 5 spaces and replaces
      thoses spaces with a space.
    */
    @Test
    public void testeveryNthCharacter12() {
        mycustomstring.setString("       ");
        assertEquals(" ", mycustomstring.everyNthCharacter(5, true));
    }

    /*Test whether the method convertDigitsToNamesInSubstring returns a string that had the digits between the 17 and 23
      index replaced with the corresponding English names.
    */
    @Test
    public void testConvertDigitsToNamesInSubstring1() {
        mycustomstring.setString("I'd b3tt3r put s0me d161ts in this 5tr1n6, right?");
        mycustomstring.convertDigitsToNamesInSubstring(17, 23);
        assertEquals("I'd b3tt3r put s*zero*me d*onesix*1ts in this 5tr1n6, right?", mycustomstring.getString());
    }

    /*Test whether the method convertDigitsToNamesInSubstring returns a string that had the digits between the 1 and 4
      index replaced with the corresponding English names. This also tests the methods behavior when the string ends in
      a number.
    */
    @Test
    public void testConvertDigitsToNamesInSubstring2() {
        mycustomstring.setString("j123");
        mycustomstring.convertDigitsToNamesInSubstring(1, 4);
        assertEquals("j*onetwothree*", mycustomstring.getString());
    }

    /*Test whether the method convertDigitsToNamesInSubstring return a NullPointerException when the string is
      null.
    */
    @Test(expected = NullPointerException.class)
    public void testConvertDigitsToNamesInSubstring3() {
        mycustomstring.setString(null);
        mycustomstring.convertDigitsToNamesInSubstring(3, 4);
        assertEquals(null, mycustomstring.getString());
    }

    /*Test whether the method convertDigitsToNamesInSubstring throws an IllegalArgumentException when the startPosition
      is less than 1.
    */
    @Test(expected = IllegalArgumentException.class)
    public void testConvertDigitsToNamesInSubstring4() {
        mycustomstring.setString("Gatech2017");
        mycustomstring.convertDigitsToNamesInSubstring(0, 3);
    }

    /*Test whether the method convertDigitsToNamesInSubstring throws a MyIndexOutOfBoundsException when the endPosition
      is greater than the length of the string.
    */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring5() {
        mycustomstring.setString("Gatech2017");
        mycustomstring.convertDigitsToNamesInSubstring(2, 15);
    }

    /*Test whether the method convertDigitsToNamesInSubstring throws a MyIndexOutOfBoundsException when the
      startPosition is greater than the endPosition.
    */
    @Test(expected = MyIndexOutOfBoundsException.class)
    public void testConvertDigitsToNamesInSubstring6() {
        mycustomstring.setString("Gatech2017");
        mycustomstring.convertDigitsToNamesInSubstring(6, 4);
    }

    /*Test whether the method convertDigitsToNamesInSubstring returns the correct string when the start and end position
      are the same.
    */
    @Test
    public void testConvertDigitsToNamesInSubstring7() {
        mycustomstring.setString("2017Gatech");
        mycustomstring.convertDigitsToNamesInSubstring(1, 1);
        assertEquals("*two*017Gatech", mycustomstring.getString());
    }

    /*Test whether the method convertDigitsToNamesInSubstring return all the correct english names for all the digits.
    */
    @Test
    public void testConvertDigitsToNamesInSubstring8() {
        mycustomstring.setString("1234567890a");
        mycustomstring.convertDigitsToNamesInSubstring(1, 10);
        assertEquals("*onetwothreefourfivesixseveneightninezero*a",
                mycustomstring.getString());
    }

    /*Test whether the method convertDigitsToNamesInSubstring returns the correct string when the start and end position
      are the same in the middle of the string.
    */
    @Test
    public void testConvertDigitsToNamesInSubstring9() {
        mycustomstring.setString("Ga8tech");
        mycustomstring.convertDigitsToNamesInSubstring(3, 3);
        assertEquals("Ga*eight*tech", mycustomstring.getString());
    }

    /*Test whether the method convertDigitsToNamesInSubstring returns the correct string when the start and end position
      are numbers and the start and end of the string.
    */
    @Test
    public void testConvertDigitsToNamesInSubstring10() {

        mycustomstring.setString("8gatech8");
        mycustomstring.convertDigitsToNamesInSubstring(1, 8);
        assertEquals("*eight*gatech*eight*", mycustomstring.getString());
    }


    /*Test whether the method convertDigitsToNamesInSubstring returns the correct string when the start string is all
      spaces.
    */
    @Test
    public void testConvertDigitsToNamesInSubstring11() {

        mycustomstring.setString("     ");
        mycustomstring.convertDigitsToNamesInSubstring(2, 4);
        assertEquals("     ", mycustomstring.getString());
    }
}
