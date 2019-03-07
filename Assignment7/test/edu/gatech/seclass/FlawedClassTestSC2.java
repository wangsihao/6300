package edu.gatech.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FlawedClassTestSC2 {

    FlawedClass fc = new FlawedClass();
    /*achieve 100% statement coverage of flawedMethod2 and
     reveal the fault therein.
      */

    @Test
    public void testANotEqualToZero() {

        assertEquals(1, fc.flawedMethod2(1));
    }


}
