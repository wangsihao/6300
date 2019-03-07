package edu.gatech.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FlawedClassTestPC3 {


    FlawedClass fc = new FlawedClass();

    /* achieve 100% path coverage of flawedMethod3 and reveal
     * the fault therein.
     */
    @Test
    public void test1BothEqualToZero() {

        assertEquals(-1, fc.flawedMethod3(0,0));
    }
    @Test
    public void testBothNotEqualToZero() {

        assertEquals(1, fc.flawedMethod3(1,1));
    }
    @Test
    public void testAEqualToZero() {

        assertEquals(1, fc.flawedMethod3(0, 1));
    }

    @Test
    public void testBEqualToZero() {

        assertEquals(1, fc.flawedMethod3(1, 0));
    }
}
