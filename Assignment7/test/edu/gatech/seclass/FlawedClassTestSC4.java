package edu.gatech.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FlawedClassTestSC4 {


    FlawedClass fc = new FlawedClass();

    /* achieve less than 100% statement coverage
     of flawedMethod4 and reveal the fault therein.
      */

    @Test
    public void testAEqualToZero(){

        assertEquals(1, fc.flawedMethod4(0));
    }

}
