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
    
    private File createInputFile5() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);
        
        fileWriter.write("");
        
        fileWriter.close();
        return file;
    }
    
    private File createInputFile6() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);
        
        fileWriter.write("The goal here is to replace string \"-c\" with" + System.lineSeparator() +
                         "string \"-l\". Since we may also want to do multiple replacements," + System.lineSeparator() +
                         "we will repeat the two strings here: -c and -l");
        
        fileWriter.close();
        return file;
    }
    
    private File createInputFile7() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);
        
        fileWriter.write("-- -- -- --");
        
        fileWriter.close();
        return file;
    }
    
    private File createInputFile8() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Mary had a csv,csv,csv\n" +
                         "Mary had a csv,commas, in a csv.\n" +
                         "csv,csv,csv");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile9() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("There was a man with a bad reflex\n" +
                         "He made up tests that were too complex\n" +
                         "googolplexmetroplexmultiplex");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile10() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("test gatech, GATech and GATECH");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile11() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Howdy Billy,\n" +
                         "This is a test file for the replace utility\n" +
                         "Let's make sure it has at least a few lines\n" +
                         "so that we can create some interesting test cases...\n" +
                         "And let's say \"howdy\" to Bill again!");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile12() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Bill is,\n" +
                         "in my opinion,\n" +
                         "an easier name to spell than William.\n" +
                         "Bill is shorter,\n" +
                         "and Bill is\n" +
                         "first alphabetically.");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile13() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
                         "I know My Abc's.\n" +
                         "It is important to know your abc's and 123's,\n" +
                         "so repeat with me: abc! 123! Abc and 123!");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile14() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("x-w|y-x|i-f|z-k\r" +
                         "i-f|x-w|y-x|z-k\r" +
                         "z-r|x-w|z-k|i-f");
        
        fileWriter.close();
        return file1;
    }

    private File createInputFile15() throws Exception {
        File file1 =  createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);

        fileWriter.write("Gatech is,\n" +
                "in my opinion,\n" +
                "a better university than Caltech.\n" +
                "Gatech is much better,\n" +
                "and Caltech was\n" +
                "much better.");

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

    
    @Test
    public void replaceTest31() throws Exception {
        File inputFile1 = createInputFile4();
        
        String args[] = {"-w", null, "Alex", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "I was admitted by gatech and catech, I think Gatech is a good university.\n" +
                "Gatech, best university in the world.";
        
        String actual1 = getFileContent(inputFile1.getPath());
        
        assertEquals("The files differ!", expected1, actual1);
    }
    

    @Test
    public void replaceTest32() throws Exception {
        File inputFile2 = createInputFile10();
        String args[] = {"-c", "gat", "123", inputFile2.getPath()};
        Main.main(args);
        String expected2 = "test 123ech, 123ech and 123ECH";
        String actual2 = getFileContent(inputFile2.getPath());
        assertEquals("The files differ!", expected2, actual2);
    }

    // test cases
    
    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 1 from assignment directions
    @Test
    public void mainTest1() throws Exception {
        File inputFile1 = createInputFile11();
        
        String args[] = {"-c", "Howdy", "Hello", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Hello Billy,\n" +
        "This is a test file for the replace utility\n" +
        "Let's make sure it has at least a few lines\n" +
        "so that we can create some interesting test cases...\n" +
        "And let's say \"hello\" to Bill again!";
        
        String actual1 = getFileContent(inputFile1.getPath());
        
        assertEquals("The files differ!", expected1, actual1);
    }
    
    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 2 from assignment directions
    @Test
    public void mainTest2() throws Exception {
        File inputFile1 = createInputFile11();
        
        String args[] = {"-w", "-l", "Bill", "William", inputFile1.getPath()};
        Main.main(args);
        
        String expected1 = "Howdy Billy,\n" +
        "This is a test file for the replace utility\n" +
        "Let's make sure it has at least a few lines\n" +
        "so that we can create some interesting test cases...\n" +
        "And let's say \"howdy\" to William again!";
        
        String actual1 = getFileContent(inputFile1.getPath());
        
        assertEquals("The files differ!", expected1, actual1);
    }
    
    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 3 from assignment directions
    @Test
    public void mainTest3() throws Exception {
        File inputFile2 = createInputFile12();
        
        String args[] = {"-w", "Bill is", "William is", inputFile2.getPath()};
        Main.main(args);
        
        String expected2 = "Bill is,\n" +
        "in my opinion,\n" +
        "an easier name to spell than William.\n" +
        "William is shorter,\n" +
        "and William is\n" +
        "first alphabetically.";
        
        String actual2 = getFileContent(inputFile2.getPath());
        
        assertEquals("The files differ!", expected2, actual2);
    }
    
    // Purpose: To provide an example of a test case format
    // Frame #: Instructor example 4 from assignment directions
    @Test
    public void mainTest4() throws Exception {
        File inputFile3 = createInputFile13();
        
        String args[] = {"-e","-w", "abc", "xyz", inputFile3.getPath()};
        Main.main(args);
        
        String expected3 = "Howdy Bill, have you learned your abc and 123?\n" +
                "I know My Abc's.\n" +
                "It is important to know your abc's and 123's,\n" +
                "so repeat with me: abc! 123! Abc and 123!";
        
        String actual3 = getFileContent(inputFile3.getPath());
        
        assertEquals("The files differ!", expected3, actual3);
    }
    
    
    // Purpose: To provide an example of a test case format
    // Frame #: Instructor error example
    @Test
    public void mainTest5() {
        String args[] = null; //invalid argument
        Main.main(args);
        assertEquals("Usage: Replace [-l] [-c] [-w] [-e] [-o] <from> <to> <filename>", errStream.toString().trim());
    }
    
    
    @Test
    public void mainTest6() throws Exception {
        File inputFile9 = createInputFile9();
        String args[] = {"-c", "-x", "lex", "!", inputFile9.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-l] [-c] [-w] [-e] [-o] <from> <to> <filename>", errStream.toString().trim());
    }
    
    
    @Test
    public void mainTest7() throws Exception {
        File inputFile9 = createInputFile9();
        
        String args[] = {"-c", "lex", "!!!", inputFile9.getPath()};
        String expected9 = "There was a man with a bad ref!!!\n" +
        "He made up tests that were too comp!!!\n" +
        "googolp!!!metrop!!!multip!!!";
        
        Main.main(args);
        
        String actual9 = getFileContent(inputFile9.getPath());
        
        assertEquals("The files differ!", expected9, actual9);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest8() throws Exception {
        File inputFile8 = createInputFile8();
        
        String args[] = {"-e", "csv", "xml", inputFile8.getPath()};
        Main.main(args);
        
        String expected8 = "Mary had a csv,xml,csv\n" +
        "Mary had a xml,commas, in a csv.\n" +
        "xml,csv,xml";
        
        String actual8 = getFileContent(inputFile8.getPath());
        
        assertEquals("The files differ!", expected8, actual8);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest9() throws Exception {
        File inputFile7 = createInputFile7();
        
        String args[] = {"-c", "--", "-c", inputFile7.getPath()};
        Main.main(args);
        
        String expected7 = "-c -c -c -c";
        
        String actual7 = getFileContent(inputFile7.getPath());
        
        assertEquals("The files differ!", expected7, actual7);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest10() throws Exception {
        File inputFile6 = createInputFile6();
        
        String args[] = {"\"", "!", inputFile6.getPath()};
        Main.main(args);
        
        String expected6 = "The goal here is to replace string !-c! with" + System.lineSeparator() +
        "string !-l!. Since we may also want to do multiple replacements," + System.lineSeparator() +
        "we will repeat the two strings here: -c and -l";
        
        String actual6 = getFileContent(inputFile6.getPath());
        
        assertEquals("The files differ!", expected6, actual6);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest11() throws Exception {
        File inputFile4 = createInputFile14();
        
        String args[] = {"-c", "-x", "-w", inputFile4.getPath()};
        Main.main(args);
        
        String expected4 = "x-w|y-w|i-f|z-k\r" +
        "i-f|x-w|y-w|z-k\r" +
        "z-r|x-w|z-k|i-f";
        
        String actual4 = getFileContent(inputFile4.getPath());
        
        assertEquals("The files differ!", expected4, actual4);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest12() throws Exception {
        File inputFile5 = createInputFile5();
        
        String args[] = {"Bill", "Jill", inputFile5.getPath()};
        Main.main(args);
        
        String expected5 = "";
        
        String actual5 = getFileContent(inputFile5.getPath());
        
        assertEquals("The files differ!", expected5, actual5);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest13() throws Exception {
        File inputFile2 = createInputFile12();
        
        String args[] = {" ", "_", inputFile2.getPath()};
        Main.main(args);
        
        String expected2 = "Bill_is,\n" +
        "in_my_opinion,\n" +
        "an_easier_name_to_spell_than_William.\n" +
        "Bill_is_shorter,\n" +
        "and_Bill_is\n" +
        "first_alphabetically.";
        
        String actual2 = getFileContent(inputFile2.getPath());
        
        assertEquals("The files differ!", expected2, actual2);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest14() throws Exception {
        File inputFile4 = createInputFile14();
        
        String args[] = {"-l", "x-w", "w-x", inputFile4.getPath()};
        Main.main(args);
        
        String expected4 = "x-w|y-x|i-f|z-k\r" +
        "i-f|x-w|y-x|z-k\r" +
        "z-r|w-x|z-k|i-f";
        
        String actual4 = getFileContent(inputFile4.getPath());
        
        assertEquals("The files differ!", expected4, actual4);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest15() throws Exception {
        File inputFile6 = createInputFile6();
        
        String args[] = {"-c", "-c", "-c", "-C", "-k", inputFile6.getPath()};
        Main.main(args);
        
        String expected6 = "The goal here is to replace string \"-k\" with" + System.lineSeparator() +
        "string \"-l\". Since we may also want to do multiple replacements," + System.lineSeparator() +
        "we will repeat the two strings here: -k and -l";
        
        String actual6 = getFileContent(inputFile6.getPath());
        
        assertEquals("The files differ!", expected6, actual6);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest16() throws Exception {
        File inputFile2 = createInputFile12();
        
        String args[] = {"-y", "Bill", "Jill", inputFile2.getPath()};
        Main.main(args);
        
        String expected2 = "Bill is,\n" +
        "in my opinion,\n" +
        "an easier name to spell than William.\n" +
        "Bill is shorter,\n" +
        "and Bill is\n" +
        "first alphabetically.";
        
        String actual2 = getFileContent(inputFile2.getPath());
        
        assertEquals("The files differ!", expected2, actual2);
        assertEquals("Usage: Replace [-l] [-c] [-w] [-e] [-o] <from> <to> <filename>", errStream.toString().trim());
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest17() throws Exception {
        File inputFile2 = createInputFile12();
        
        String args[] = {"Bill", "Ted", "--", inputFile2.getPath()};
        Main.main(args);
        
        String expected2 = "Bill is,\n" +
        "in my opinion,\n" +
        "an easier name to spell than William.\n" +
        "Bill is shorter,\n" +
        "and Bill is\n" +
        "first alphabetically.";
        
        String actual2 = getFileContent(inputFile2.getPath());
        
        assertEquals("The files differ!", expected2, actual2);
        assertEquals("Usage: Replace [-l] [-c] [-w] [-e] [-o] <from> <to> <filename>", errStream.toString().trim());
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest18() throws Exception {
        File inputFile2 = createInputFile12();
        
        String args[] = {inputFile2.getPath()};
        Main.main(args);
        
        String expected2 = "Bill is,\n" +
        "in my opinion,\n" +
        "an easier name to spell than William.\n" +
        "Bill is shorter,\n" +
        "and Bill is\n" +
        "first alphabetically.";
        
        String actual2 = getFileContent(inputFile2.getPath());
        
        assertEquals("The files differ!", expected2, actual2);
        assertEquals("Usage: Replace [-l] [-c] [-w] [-e] [-o] <from> <to> <filename>", errStream.toString().trim());
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest19() throws Exception {
        File inputFile6 = createInputFile6();
        
        String args[] = {"-o", "-c", "-l", inputFile6.getPath()};
        Main.main(args);
        
        String expected6 = "The goal here is to replace string \"-l\" with" + System.lineSeparator() +
        "string \"-l\". Since we may also want to do multiple replacements," + System.lineSeparator() +
        "we will repeat the two strings here: -c and -l";
        
        String actual6 = getFileContent(inputFile6.getPath());
        
        assertEquals("The files differ!", expected6, actual6);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest20() throws Exception {
        File inputFile7 = createInputFile7();
        
        String args[] = {"-w", "--", "!!", inputFile7.getPath()};
        Main.main(args);
        
        String expected7 = "!! !! !! !!";
        
        String actual7 = getFileContent(inputFile7.getPath());
        
        assertEquals("The files differ!", expected7, actual7);
        
        String args2[] = {"-w", "!!", "%%", inputFile7.getPath()};
        Main.main(args2);
        
        expected7 = "%% %% %% %%";
        
        actual7 = getFileContent(inputFile7.getPath());
        
        assertEquals("The files differ!", expected7, actual7);
    }
    
    
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest21() throws Exception {
        File inputFile7 = createInputFile7();
        
        String args[] = {" --", "##", inputFile7.getPath()};
        Main.main(args);
        
        String expected7 = "--######";
        
        String actual7 = getFileContent(inputFile7.getPath());
        
        assertEquals("The files differ!", expected7, actual7);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest22() throws Exception {
        File inputFile4 = createInputFile14();
        
        String args[] = {"-o", "x-w", "w-x", inputFile4.getPath()};
        Main.main(args);
        
        String expected4 = "w-x|y-x|i-f|z-k\r" +
        "i-f|x-w|y-x|z-k\r" +
        "z-r|w-x|z-k|i-f";
        
        String actual4 = getFileContent(inputFile4.getPath());
        
        assertEquals("The files differ!", expected4, actual4);
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest23() throws Exception {
        File inputFile2 = createInputFile12();
        
        String args[] = {"-o", "-e", "-c", "-l", "LL", "LOLZ", inputFile2.getPath()};
        Main.main(args);
        
        String expected2 = "BiloLZ is,\n" +
        "in my opinion,\n" +
        "an easier name to speloLZ than WiloLZiam.\n" +
        "BiloLZ is shorter,\n" +
        "and BiloLZ is\n" +
        "first alphabeticaloLZy.";
        
        String actual2 = getFileContent(inputFile2.getPath());
        
        assertEquals("The files differ!", expected2, actual2);
        
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest24() throws Exception {
        
        String args[] = {"-w", "Bill", "Jill", "doesnotexist.txt"};
        Main.main(args);
        
        assertEquals("File Not Found", errStream.toString().trim());
    }
    
    // Purpose: To provide an example of a test case format
    // Instructor Example
    @Test
    public void mainTest25() throws Exception {
        File inputFile9 = createInputFile9();
        
        String args[] = {"-e", "-l", "lex", "Alex", inputFile9.getPath()};
        String expected9 = "There was a man with a bad reflex\n" +
        "He made up tests that were too compAlex\n" +
        "googolplexmetropAlexmultipAlex";
        
        Main.main(args);
        
        String actual9 = getFileContent(inputFile9.getPath());
        
        assertEquals("The files differ!", expected9, actual9);
    }
    // Type c: the replace code replace the from when it is not whitespace delimited when -o -l -e -w -c all exist, maybe because when
    // all the option selected, -w is not useful anymore.
    @Test
    public void mainTest26() throws Exception {
        File inputFile2 = createInputFile15();

        String args[] = {"-o", "-e", "-c", "-l","-w", "IS", "lo", inputFile2.getPath()};
        Main.main(args);

        String expected2 = "Gatech is,\n" +
                "in my opinion,\n" +
                "a better university than Caltech.\n" +
                "Gatech lo much better,\n" +
                "and Caltech was\n" +
                "much better.";

        String actual2 = getFileContent(inputFile2.getPath());

        assertEquals("The files differ!", expected2, actual2);

    }

    
}
