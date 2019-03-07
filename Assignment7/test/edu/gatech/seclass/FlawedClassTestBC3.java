package edu.gatech.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlawedClassTestBC3 {

    /*achieve 100% branch coverage of flawedMethod3,
     and not reveal the fault therein.
      */

    FlawedClass fc = new FlawedClass();

    @Test
    public void testAEqualToZero() {

        assertEquals(1, fc.flawedMethod3(0, 1));
    }

    @Test
    public void testBEqualToZero() {

        assertEquals(1, fc.flawedMethod3(1, 0));
    }
}

