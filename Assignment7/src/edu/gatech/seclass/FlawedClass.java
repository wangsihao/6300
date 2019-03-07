package edu.gatech.seclass;


public class FlawedClass {

    public void flawedMethod1() {

        /*
         * 100 % branch coverage includes 100% statement coverage. If every
         * test suite that achieves 100% statement coverage reveals the
         * fault, then the fault must be revealed when 100% branch
         * coverage is achieved. Since the task requires the fault is not
         * supposed to be revealed with 100% branch coverage, this task is
         * not possible.
         */
    }



    public int flawedMethod2(int a){

        return 1/a;

    }



    public int flawedMethod3(int a, int b){

        int c = 0;
        if (a != 0)
            c = a;
        if (b != 0)
            c = b;
        return 1/c;
    }


    public int flawedMethod4(int a){

        int b = 1;

        if(a >= 0){
            b = a;
        }

        return 1/b;

    }

    public boolean flawedMethod5 (boolean a, boolean b) {
        int x = -1;
        int y = 2;
        if(a)
            x = 2;
        else
            y = 0;
        if(b)
            y -= x;
        else
            y -= 2;
        return (x/y > 0);
    }
// | a | b |output|
// ================
// | T | T | E    |
// | T | F | E    |
// | F | T | F    |
// | F | F | F    |
// ================
// Coverage required: Statement Coverage.

}



