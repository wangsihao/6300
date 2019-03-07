package edu.gatech.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FlawedClassTestBC2 {
    FlawedClass fc = new FlawedClass();
    /*achieve 100% branch coverage of flawedMethod2 and
     reveal the fault therein.
      */

    @Test
    public void testANotEqualToZero() {

        assertEquals(0, fc.flawedMethod2(0));
    }


}
