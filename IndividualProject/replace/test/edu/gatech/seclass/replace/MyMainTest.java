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

    private File createInputFile4() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("I was admitted by gatech and catech, I think Gatech is a good university.\n"
                +"Gatech, best university in the world.");

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
    // Type c: the replace code can not deal with the situation when the length of from is larger than the length of to
    // Purpose: test whether -c will replace it when the length of from and to are not equal
    // Frame #: 21
 

    @Test
    public void replaceTest1() throws Exception {
        File inputFile1 = createInputFile1();

        String args[] = {"-c", "Gatech", "MIT", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "Mit, a top university in US!";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: To test whether -w can replace the spring when the length of from is not equal to the length of to
    // Frame #: 27


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
    // Frame #: 18


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
    // Frame #:33


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
    // Frame #:25


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
    // Frame #:15


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
    // Frame #:41


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
    // Frame #:35


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
    // Frame #: 4


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
    // Frame #: 17

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
    // Frame #:13

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
    // Frame #: 39


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
    // Frame #:48

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
    // Frame #:47

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
    // Frame #:55


    @Test
    public void replaceTest15() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"-c", "-w", "-l", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "The professor in catech is awesome.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: Test whether it -c -w -l can replace the string
    // Frame #:25

    @Test
    public void replaceTest16() throws Exception {
        File inputFile1 = createInputFile3();

        String args[] = {"-c", "GAtech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "The professor in catech is awesome.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: Test whether -c can replace multiple word when some keyword are whitespace delimited
    // Frame #:28
    @Test
    public void replaceTest17() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-c", "GAtech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "I was admitted by catech and catech, I think Catech is a good university.\n"
                +"Catech, best university in the world.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: Test whether -l -c is case insensitive
    // Frame #:45
    @Test
    public void replaceTest18() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-c", "-l", "Gatech", "Catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "I was admitted by gatech and catech, I think Gatech is a good university.\n"
                +"Catech, best university in the world.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: Test whether -l is case insensitive
    // Frame #:15


    @Test
    public void replaceTest19() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-l", "GAtech", "catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "I was admitted by gatech and catech, I think Gatech is a good university.\n"
                +"Gatech, best university in the world.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: Test whether -l -w is case insensitive
    // Frame #:49

    @Test
    public void replaceTest20() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-l","-w", "GAtech", "catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "I was admitted by gatech and catech, I think Gatech is a good university.\n"
                +"Gatech, best university in the world.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: Test whether -l -w is case insensitive
    // Frame #:29

    @Test
    public void replaceTest21() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-w", "GAtech", "catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "I was admitted by gatech and catech, I think Gatech is a good university.\n"
                +"Gatech, best university in the world.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: Test whether replace is working when the option is wrong
    // Frame #: 8
    @Test
    public void replaceTest22() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-k", "GAtech", "catech", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-l] [-c] [-w] [-e] [-o] <from> <to> <filename>", errStream.toString().trim());
    }
    // Purpose: Test whether replace is working when the option is wrong
    // Frame #: 8
    @Test
    public void replaceTest23() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"", "GAtech", "catech", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-l] [-c] [-w] [-e] [-o] <from> <to> <filename>", errStream.toString().trim());
    }
    // Type c: The replace code can not deal with the situation when from and to are not entered
    // Purpose: Test whether replace is working when lack from/to
    // Frame #: 18
    @Test
    public void replaceTest24() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = { "-l", inputFile1.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-l] [-c] [-w] [-e] [-o] <from> <to> <filename>", errStream.toString().trim());
    }
    // Purpose: Test whether replace is working when the text file is missing
    // Frame #: 16
    @Test
    public void replaceTest25() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = { "-l", "GAtech", "catech"};
        Main.main(args);
        assertEquals("File Not Found", errStream.toString().trim());
    }
    // Purpose: Test whether -w -c will change the whitespace delimited word and can recognize the word case insensitively
    // Frame #:45
    @Test
    public void replaceTest26() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-w","-c", "GAtech", "catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "I was admitted by catech and catech, I think Catech is a good university.\n"
                +"Gatech, best university in the world.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: Test whether -l -w -c will change the last whitespace delimited word and can recognize the word
    //          case insensitively
    // Frame #:56
    @Test
    public void replaceTest27() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-w","-c","-l", "GAtech", "catech", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "I was admitted by gatech and catech, I think Catech is a good university.\n"
                +"Gatech, best university in the world.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }
    // Purpose: Test whether -l -w  will change the last whitespace delimited word and can recognize the word
    //          case insensitively when the length of from and to are different
    // Frame #:43
    @Test
    public void replaceTest28() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-w","-l", "Gatech", "MIT", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "I was admitted by gatech and catech, I think MIT is a good university.\n"
                +"Gatech, best university in the world.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

    // Purpose: Test whether -c will change the string if from is not find in the string
    // Frame #:36
    @Test
    public void replaceTest29() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-c", "Gatechh", "MIT", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "I was admitted by gatech and catech, I think Gatech is a good university.\n"
                +"Gatech, best university in the world.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }
    // Type c: the replace code can not deal with the situation when the length of from is larger than the length of to
    // Purpose: Test whether -c will change the original if from is in the middle of words in original text
    // Frame #:24
    @Test
    public void replaceTest30() throws Exception {
        File inputFile1 = createInputFile4();

        String args[] = {"-c", "atec", "MIT", inputFile1.getPath()};
        Main.main(args);

        String expected1 = "I was admitted by gmith and cmith, I think Gmith is a good university.\n"
                +"Gmith, best university in the world.";

        String actual1 = getFileContent(inputFile1.getPath());

        assertEquals("The files differ!", expected1, actual1);
    }

 

    
}
