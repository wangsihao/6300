#!/bin/sh
\rm -f result.xml report.txt testclasses/edu/gatech/seclass/replace/MyMainTest.class
javac -cp lib/replace.jar:lib/tests.jar:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar -d testclasses testsrc/edu/gatech/seclass/replace/*
java -cp lib/tests.jar:testclasses:lib/replace.jar:lib/junit-4.12.jar:lib/hamcrest-core-1.3.jar:lib/jcov_file_saver.jar org.junit.runner.JUnitCore edu.gatech.seclass.replace.MainTestSuite
