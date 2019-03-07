package edu.gatech.seclass.replace;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import edu.gatech.seclass.replace.Main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;


public class MyMainTest {

    private ByteArrayOutputStream outStream;
    private ByteArrayOutputStream errStream;
    private PrintStream outOrig;
    private PrintStream errOrig;
    private Charset charset = StandardCharsets.UTF_8;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        outStream = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outStream);
        errStream = new ByteArrayOutputStream();
        PrintStream err = new PrintStream(errStream);
        outOrig = System.out;
        errOrig = System.err;
        System.setOut(out);
        System.setErr(err);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(outOrig);
        System.setErr(errOrig);
    }

    // Some utilities

    private File createTmpFile() throws IOException {
        File tmpfile = temporaryFolder.newFile();
        tmpfile.deleteOnExit();
        return tmpfile;
    }


    private File createInputFile1() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Gatech, a top university in US!");

        fileWriter.close();
        return file1;
    }

    private File createInputFile2() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Among Gatech, Caltech, MIT, I love Gatech best.");

        fileWriter.close();
        return file1;
    }

    private File createInputFile3() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("The professor in gatech is awesome.");

        fileWriter.close();
        return file1;
    }

    private String getFileContent(String filename) {
        String content = null;
        try {
            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    // test cases

    // Purpose: test whether -c will replace it when the length of from and to are not equal
    // Test Case: 21
    // Frame #: 1

    @Test
    public void replaceTest1() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-c", "Gatech", "MIT", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Gatech, a top university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -w can replace the spring when the length of from is not equal to the length of to
    // Test Case: 27
    // Frame #: 2

    @Test
    public void replaceTest2() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-w", "a top", "a good", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Gatech, a good university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: test whether -c will replace it when the length of from and to are equal, but from is not whitespace
    //          delimited
    // Test Case: 18
    // Frame #: 3

    @Test
    public void replaceTest3() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-c", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Catech, a top university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -w -l can replace the spring when from is not whitespace delimited
    // Test Case:33
    // Frame #: 4

    @Test
    public void replaceTest4() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-w", "-l", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Gatech, a top university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -w can replace the spring when from is not whitespace delimited
    // Test Case:25
    // Frame #: 5

    @Test
    public void replaceTest5() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-w", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Gatech, a top university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: Test whether -c can replace the spring in from to the spring in to when from is whitespace delimited
    // Test Case:15
    // Frame #: 6


    @Test
    public void replaceTest6() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"-c","Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "The professor in catech is awesome.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: Test whether -c -l can replace the spring in from to the spring in to when from is not whitespace delimited
    // Test Case:41
    // Frame #: 7
    @Test
    public void replaceTest7() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-c", "-l", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Catech, a top university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -w -l can replace the string when the length of from is not equal to the length of to
    // Test Case:35
    // Frame #: 8

    @Test
    public void replaceTest8() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-w", "-l", "a top", "a good", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Gatech, a good university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -l can replace the string in from when from is not whitespace delimited
    // Test Case: 9
    // Frame #: 9

    @Test
    public void replaceTest9() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-l", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Catech, a top university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -l can replace all the string in from when from occured several times
    // Test Case:10
    // Frame #: 10

    @Test
    public void replaceTest10() throws Exception {
        File inputFile1 = createInputFile2();

        String args[] = {"-l", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Among Gatech, Caltech, MIT, I love Catech best.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -l can replace the string in from when the length of from is not equal to length of to
    // Test Case:13
    // Frame #: 11

    @Test
    public void replaceTest11() throws Exception{
        File inputFile1 = createInputFile1();

        String args[] = {"-l", "Gatech", "MIT", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "MIT, a top university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -c -l can replace the string when the string occured multiple times
    // Test Case: 39
    // Frame #: 12


    @Test
    public void replaceTest12() throws Exception {
        File inputFile1 = createInputFile2();

        String args[] = {"-c", "-l", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Among Gatech, Caltech, MIT, I love Catech best.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -w -c can replace the string when from is not whitespace delimited
    // Test Case:48
    // Frame #: 13

    @Test
    public void replaceTest13() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-w", "-c", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Gatech, a top university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -w -c can replace the string when from is whitespace delimited
    // Test Case:47
    // Frame #: 14

    @Test
    public void replaceTest14() throws Exception{
        File inputFile1 = createInputFile3();

        String args[] = {"-w", "-c", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "The professor in catech is awesome.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: Test whether it -c -w -l can replace the string
    // Test Case:55
    // Frame #: 15


    @Test
    public void replaceTest15() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"-c", "-w", "-l", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "The professor in catech is awesome.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }


}