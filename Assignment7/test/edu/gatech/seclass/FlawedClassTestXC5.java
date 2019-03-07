package edu.gatech.seclass;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlawedClassTestXC5 {


    FlawedClass fc = new FlawedClass();


    @Test
    public void testTT(){

        assertEquals(false, fc.flawedMethod5(true,true));
    }

    @Test
    public void testTF(){

        assertEquals(false, fc.flawedMethod5(true,false));
    }

    @Test
    public void testFT(){

        assertEquals(false , fc.flawedMethod5(false,true));
    }
    @Test
    public void testFF(){

        assertEquals(false, fc.flawedMethod5(false,false));
    }
}
