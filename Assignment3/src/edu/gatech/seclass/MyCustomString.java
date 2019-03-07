package edu.gatech.seclass;


public class MyCustomString implements MyCustomStringInterface {

    private String myCustomString;

    @Override
    public String getString() {
        return myCustomString;
    }

    @Override
    public void setString(String string) {
        this.myCustomString = string;
    }

    @Override
    public int countNumbers() {
        String string = getString();
        if (string == null) {
            return 0;
        } else if (string.isEmpty()) {
            return 0;
        } else {
            int count = 0;
            boolean currentDigit = false;
            for (int i = 0; i < string.length(); i++) {
                char character = string.charAt(i);
                if (Character.isDigit(character)) {
                    currentDigit = true;
                } else {
                    if (currentDigit) {
                        count++;
                        currentDigit = false;
                    }
                }
            }
            if (currentDigit) {
                count++;
            }
            return count;
        }
    }



    @Override
    public String everyNthCharacter(int n, boolean returnN) {
        String string = getString();

        if (string == null) {
            throw new NullPointerException("String is null");
        }

        if (n > string.length()) {
            throw new MyIndexOutOfBoundsException("n is greater than string length");
        }

        if (n <= 0) {
            throw new IllegalArgumentException("n is less than or equal to 0");
        }
        char[] chars = string.toCharArray();
        String newString = "";
        if (returnN) {
            for (int i = 0; i < chars.length; i++) {
                if (i + 1 < n) {
                    newString = newString;
                } else if (((i + 1) % n) == 0) {
                    newString = newString + chars[i];
                } else {
                    newString = newString;
                }
            }
        } else {
            for (int i = 0; i < chars.length; i++) {
                if (i + 1 < n) {
                    newString = newString + chars[i];
                } else if (((i + 1) % n) == 0) {
                    newString = newString;
                } else {
                    newString = newString + chars[i];
                }
            }
        }
        return newString;
    }



    private boolean isNum(char c) {
        boolean retval = false;
        if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9') {
            retval = true;
        }
        return retval;
    }

    private String getNum(int i) {

        String retval = "";

        switch (i) {
            case 0:
                retval = "zero";
                break;
            case 1:
                retval = "one";
                break;
            case 2:
                retval = "two";
                break;
            case 3:
                retval = "three";
                break;
            case 4:
                retval = "four";
                break;
            case 5:
                retval = "five";
                break;
            case 6:
                retval = "six";
                break;
            case 7:
                retval = "seven";
                break;
            case 8:
                retval = "eight";
                break;
            case 9:
                retval = "nine";
                break;
            default:
                break;

        }

        return retval;
    }

    @Override
    public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {

        int start = startPosition - 1;
        int end = endPosition - 1;


        String s = getString();

        if (startPosition > endPosition || endPosition > s.length()) {
            throw new MyIndexOutOfBoundsException("Start position is less than or equal to end position.");

        }

        if ((startPosition <= endPosition) && (startPosition < 1)) {
            throw new IllegalArgumentException("Start position is less than 1");
        }
        char[] chars = s.toCharArray();

        String retval = "";

        for (int i = 0; i < chars.length; i++) {
            if (i < start) {
                retval = retval + chars[i];
            } else if (i > end) {
                retval = retval + chars[i];
            } else {
                if (isNum(chars[i])) {
                    if (i == start){
                        retval =  retval + "*" + getNum(Character.getNumericValue(chars[i]));
                        if (i == end){
                            retval =  retval + "*";
                        } else if (!isNum(chars[i+1])){
                            retval =  retval + "*";
                        }
                    }else if (!isNum(chars[i-1])){
                        retval =  retval + "*" + getNum(Character.getNumericValue(chars[i]));
                        if (i == end){
                            retval =  retval + "*";
                        } else if (!isNum(chars[i+1])){
                            retval =  retval + "*";
                        }
                    }else {
                        retval = retval + getNum(Character.getNumericValue(chars[i]));
                        if (i == end){
                            retval =  retval + "*";
                        } else if (!isNum(chars[i+1])){
                            retval =  retval + "*";
                        }
                    }
                }else {
                    retval = retval + chars[i];
                }
            }
        }
        setString(retval);
    }
}
