package edu.gatech.seclass;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlawedClassTestBC4 {

    FlawedClass fc = new FlawedClass();

    /* Achieve 100% branch coverage of flawedMethod4
     and not reveal the fault therein.
      */
    @Test
    public void testALargerThanZero(){

        assertEquals(1, fc.flawedMethod4(1));
    }

    @Test
    public void testASmallerThanZero(){

        assertEquals(1, fc.flawedMethod4(-1));
    }
}
