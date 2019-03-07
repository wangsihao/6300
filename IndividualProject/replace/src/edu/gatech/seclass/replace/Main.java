
package edu.gatech.seclass.replace;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

 /*
Do not alter this class or implement it.
 */


    public static void main(String[] args) {
        // Empty Skeleton Method

        replace(args);

    }


    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }

    public static String replaceCase(String text, String to) {

        for (int i = 0; i < Math.min(text.length(), to.length()); i++){
            char c = text.charAt(i);
            int p = (int) c;

            if(p >= 65 && p < 65 + 26) {
                to = to.substring(0,i) + to.substring(i,i+1).toUpperCase() + to.substring(i+1,to.length());
            } else if(p >= 97 && p < 97 + 26){
                to = to.substring(0,i) + to.substring(i,i+1).toLowerCase() + to.substring(i+1,to.length());
            }
        }
        text = text.replace(text, to);
        return text;
    }



    public static String replaceLastCase(String string, String toReplace, String replacement) {
        String s1 = string.toLowerCase();
        String s2 = toReplace.toLowerCase();

        int pos = s1.lastIndexOf(s2);

        if (pos > -1) {
            String text = string.substring(pos, pos + toReplace.length());
            replacement = replaceCase(text, replacement);
            toReplace = replaceCase(text, toReplace);
            return replaceLast(string, toReplace, replacement);
        } else {
            return string;
        }
    }

    public static String replaceOdd(String string, String toReplace, String replacement) {
        String s1 = string.toLowerCase();
        String s2 = toReplace.toLowerCase();

        int pos = s1.indexOf(s2);
        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
        if (pos > -1) {
            String text = string.substring(pos, pos + toReplace.length());
            replacement = replaceCase(text, replacement);
            toReplace = replaceCase(text, toReplace);
            text = string.replaceFirst(toReplace, replacement);
            if (pos2 > -1){
                return text.substring(0, pos2+replacement.length());
            }else {
                return text;
            }
        } else {
            return string;
        }
    }

    public static String replaceEven(String string, String toReplace, String replacement) {
        String s1 = string.toLowerCase();
        String s2 = toReplace.toLowerCase();

        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());

        if (pos2 > -1) {
            String text = string.substring(pos2, pos2 + toReplace.length());
            replacement = replaceCase(text, replacement);
            return string.substring(0,pos2) + replacement;
        } else {
            return string;
        }
    }

    public static String replaceOdd2(String string, String toReplace, String replacement) {
        String s1 = string;
        String s2 = toReplace;

        int pos = s1.indexOf(s2);
        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
        if (pos > -1) {
            String text = string.substring(pos, pos + toReplace.length());
            text = string.replaceFirst(toReplace, replacement);
            if (pos2 > -1){
                return text.substring(0, pos2+replacement.length());
            }else {
                return text;
            }
        } else {
            return string;
        }
    }

    public static String replaceEven2(String string, String toReplace, String replacement) {
        String s1 = string;
        String s2 = toReplace;

        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());

        if (pos2 > -1) {
            String text = string.substring(pos2, pos2 + toReplace.length());
            return string.substring(0,pos2) + replacement;
        } else {
            return string;
        }
    }


    private synchronized static void replace(String[] args) {

        String TO = "";
        String FROM = "";
        String FILENAME = "";
        boolean Select_L = false;
        boolean Select_C = false;
        boolean Select_W = false;
        boolean Select_O = false;
        boolean Select_E = false;
        String ignore_w = "";
        String ignore_x = "";


        String arg;
        int i = 0;

        try {

            if (args != null) {

                while (i + 3 < args.length && args[i].startsWith("-")) {
                    arg = args[i];

                    switch (arg) {
                        case "-l":
                            Select_L = true;
                            break;
                        case "-c":
                            Select_C = true;
                            break;
                        case "-w":
                            Select_W = true;
                            break;
                        case "-o":
                            Select_O = true;
                            break;
                        case "-e":
                            Select_E = true;
                            break;
                        default:
                            throw new IllegalArgumentException(arg);

                    }
                    i++;
                }
                if ((args.length - i) != 3) {
                    usage();
                    return;
                }else{
                    FROM = args[args.length - 3];
                    TO = args[args.length - 2];
                    FILENAME = args[args.length - 1];
                }

            } else {
                usage();
                return;
            }
        } catch (IllegalArgumentException iae) {

            usage();
            return;

        }

        if (Select_E && Select_O){
            Select_E = false;
            Select_O = false;
            Select_L = false;
        }
        String oldstring = "";

        try {
            FileReader FR = new FileReader(FILENAME);
            BufferedReader reader = new BufferedReader(FR);


            int c ;
            StringBuilder buildText = new StringBuilder();
            while ((c = reader.read()) != -1) {
                char character = (char) c;
                buildText.append(character);
            }

            oldstring = buildText.toString();
            FR.close();
            reader.close();

        } catch (IOException ioe) {
            if (ioe instanceof FileNotFoundException) {
                System.err.println("File Not Found");
            } else{
                usage();
            }
            return;
        }


        String newstring = "";



        if (Select_W) {
            if (Select_C) {
                if (Select_L) {
                    FROM = FROM + " ";
                    TO = TO + " ";
                    newstring = replaceLastCase(oldstring, FROM, TO);
                    if(Select_O){
                        oldstring = newstring;
                        newstring = "";
                        String s1 = oldstring.toLowerCase();
                        String s2 = FROM.toLowerCase();
                        int pos = s1.indexOf(s2);
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos > -1) {
                            newstring = newstring + replaceOdd(oldstring, FROM, TO);
                            if (pos2 > -1){
                                oldstring = oldstring.substring(pos2 + FROM.length(), oldstring.length());
                            }else{
                                oldstring = "";
                            }
                            s1 = oldstring;
                            s2 = FROM;
                            pos = s1.indexOf(s2);
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else if(Select_E){
                        oldstring = newstring;
                        newstring = "";
                        String s1 = oldstring.toLowerCase();
                        String s2 = FROM.toLowerCase();
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos2 > -1) {
                            newstring = newstring + replaceEven(oldstring, FROM, TO);
                            oldstring = oldstring.substring(pos2 + FROM.length());
                            s1 = oldstring;
                            s2 = FROM;
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }
                }
                else {
                    if(Select_O){
                        FROM = FROM + " ";
                        TO = TO + " ";
                        String s1 = oldstring.toLowerCase();
                        String s2 = FROM.toLowerCase();
                        int pos = s1.indexOf(s2);
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos > -1) {
                            newstring = newstring + replaceOdd(oldstring, FROM, TO);
                            if (pos2 > -1){
                                oldstring = oldstring.substring(pos2 + FROM.length(), oldstring.length());
                            }else{
                                oldstring = "";
                            }
                            s1 = oldstring;
                            s2 = FROM;
                            pos = s1.indexOf(s2);
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else if(Select_E){
                        FROM = FROM + " ";
                        TO = TO + " ";
                        String s1 = oldstring.toLowerCase();
                        String s2 = FROM.toLowerCase();
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos2 > -1) {
                            newstring = newstring + replaceEven(oldstring, FROM, TO);
                            oldstring = oldstring.substring(pos2 + FROM.length());
                            s1 = oldstring;
                            s2 = FROM;
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else{
                        FROM = FROM + " ";
                        TO = TO + " ";
                        String s1 = oldstring.toLowerCase();
                        String s2 = FROM.toLowerCase();
                        int pos = s1.indexOf(s2);
                        while (pos > -1) {
                            newstring = replaceLastCase(oldstring, FROM, TO);
                            s1 = newstring.toLowerCase();
                            s2 = FROM.toLowerCase();
                            pos = s1.indexOf(s2);
                            oldstring = newstring;
                        }
                        newstring = oldstring;
                    }
                }
            }else{
                FROM = "(?<!\\S)" + FROM + "(?!\\S)";
                if (Select_L) {
                    newstring = replaceLast(oldstring, FROM, TO);
                    if(Select_O){
                        oldstring = newstring;
                        newstring = "";
                        String s1 = oldstring;
                        String s2 = FROM;
                        int pos = s1.indexOf(s2);
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos > -1) {
                            newstring = newstring + replaceOdd2(oldstring, FROM, TO);
                            if (pos2 > -1){
                                oldstring = oldstring.substring(pos2 + FROM.length(), oldstring.length());
                            }else{
                                oldstring = "";
                            }
                            s1 = oldstring;
                            s2 = FROM;
                            pos = s1.indexOf(s2);
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else if(Select_E){
                        oldstring = newstring;
                        newstring = "";
                        String s1 = oldstring;
                        String s2 = FROM;
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos2 > -1) {
                            newstring = newstring + replaceEven2(oldstring, FROM, TO);
                            oldstring = oldstring.substring(pos2 + FROM.length());
                            s1 = oldstring;
                            s2 = FROM;
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }
                } else {
                    if(Select_O){
                        String s1 = oldstring;
                        String s2 = FROM;
                        int pos = s1.indexOf(s2);
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos > -1) {
                            newstring = newstring + replaceOdd2(oldstring, FROM, TO);
                            if (pos2 > -1){
                                oldstring = oldstring.substring(pos2 + FROM.length(), oldstring.length());
                            }else{
                                oldstring = "";
                            }
                            s1 = oldstring;
                            s2 = FROM;
                            pos = s1.indexOf(s2);
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else if(Select_E){
                        String s1 = oldstring;
                        String s2 = FROM;
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos2 > -1) {
                            newstring = newstring + replaceEven2(oldstring, FROM, TO);
                            oldstring = oldstring.substring(pos2 + FROM.length());
                            s1 = oldstring;
                            s2 = FROM;
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else{
                        newstring = oldstring.replaceAll(FROM, TO);
                    }
                }
            }
        }else{
            if (Select_C) {
                if (Select_L) {
                    newstring = replaceLastCase(oldstring, FROM, TO);
                    if(Select_O){
                        oldstring = newstring;
                        newstring = "";
                        String s1 = oldstring.toLowerCase();
                        String s2 = FROM.toLowerCase();
                        int pos = s1.indexOf(s2);
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos > -1) {
                            newstring = newstring + replaceOdd(oldstring, FROM, TO);
                            if (pos2 > -1){
                                oldstring = oldstring.substring(pos2 + FROM.length(), oldstring.length());
                            }else{
                                oldstring = "";
                            }
                            s1 = oldstring.toLowerCase();
                            s2 = FROM.toLowerCase();
                            pos = s1.indexOf(s2);
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else if(Select_E){
                        oldstring = newstring;
                        newstring = "";
                        String s1 = oldstring.toLowerCase();
                        String s2 = FROM.toLowerCase();
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos2 > -1) {
                            newstring = newstring + replaceEven(oldstring, FROM, TO);
                            oldstring = oldstring.substring(pos2 + FROM.length());
                            s1 = oldstring.toLowerCase();
                            s2 = FROM.toLowerCase();
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }
                }else {
                    if(Select_O){
                        String s1 = oldstring.toLowerCase();
                        String s2 = FROM.toLowerCase();
                        int pos = s1.indexOf(s2);
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos > -1) {
                            newstring = newstring + replaceOdd(oldstring, FROM, TO);
                            if (pos2 > -1){
                                oldstring = oldstring.substring(pos2 + FROM.length(), oldstring.length());
                            }else{
                                oldstring = "";
                            }
                            s1 = oldstring.toLowerCase();
                            s2 = FROM.toLowerCase();
                            pos = s1.indexOf(s2);
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else if(Select_E){
                        String s1 = oldstring.toLowerCase();
                        String s2 = FROM.toLowerCase();
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos2 > -1) {
                            newstring = newstring + replaceEven(oldstring, FROM, TO);
                            oldstring = oldstring.substring(pos2 + FROM.length());
                            s1 = oldstring.toLowerCase();
                            s2 = FROM.toLowerCase();
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else{
                        String s1 = oldstring.toLowerCase();
                        String s2 = FROM.toLowerCase();
                        int pos = s1.indexOf(s2);
                        while (pos > -1) {
                            newstring = replaceLastCase(oldstring, FROM, TO);
                            s1 = newstring.toLowerCase();
                            s2 = FROM.toLowerCase();
                            pos = s1.indexOf(s2);
                            oldstring = newstring;
                        }
                        newstring = oldstring;
                    }

                }
            }else{
                if (Select_L) {
                    newstring = replaceLast(oldstring, FROM, TO);
                    if(Select_O){
                        oldstring = newstring;
                        newstring = "";
                        String s1 = oldstring;
                        String s2 = FROM;
                        int pos = s1.indexOf(s2);
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos > -1) {
                            newstring = newstring + replaceOdd2(oldstring, FROM, TO);
                            if (pos2 > -1){
                                oldstring = oldstring.substring(pos2 + FROM.length(), oldstring.length());
                            }else{
                                oldstring = "";
                            }
                            s1 = oldstring;
                            s2 = FROM;
                            pos = s1.indexOf(s2);
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else if(Select_E){
                        oldstring = newstring;
                        newstring = "";
                        String s1 = oldstring;
                        String s2 = FROM;
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos2 > -1) {
                            newstring = newstring + replaceEven2(oldstring, FROM, TO);
                            oldstring = oldstring.substring(pos2 + FROM.length());
                            s1 = oldstring;
                            s2 = FROM;
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }
                } else {
                    if(Select_O){
                        String s1 = oldstring;
                        String s2 = FROM;
                        int pos = s1.indexOf(s2);
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos > -1) {
                            newstring = newstring + replaceOdd2(oldstring, FROM, TO);
                            if (pos2 > -1){
                                oldstring = oldstring.substring(pos2 + FROM.length(), oldstring.length());
                            }else{
                                oldstring = "";
                            }
                            s1 = oldstring;
                            s2 = FROM;
                            pos = s1.indexOf(s2);
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else if(Select_E){
                        String s1 = oldstring;
                        String s2 = FROM;
                        int pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        while (pos2 > -1) {
                            newstring = newstring + replaceEven2(oldstring, FROM, TO);
                            oldstring = oldstring.substring(pos2 + FROM.length());
                            s1 = oldstring;
                            s2 = FROM;
                            pos2 = s1.indexOf(s2, s1.indexOf(s2)+s2.length());
                        }
                        newstring = newstring + oldstring;
                    }else{
                        newstring = oldstring.replaceAll(FROM, TO);
                    }
                }
            }
        }

        try {

            FileWriter FW = new FileWriter(FILENAME);

            FW.write(newstring);

            FW.close();

        } catch (IOException ioe) {
            System.err.println("File Not Found");
        }

    }

    private static void usage() {
        System.err.println("Usage: Replace [-l] [-c] [-w] [-e] [-o] <from> <to> <filename>");
    }

}