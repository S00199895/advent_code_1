package com.overstock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Integer result = getElfCodeSum();
    }

    //global iterator
    static int i = 0;

    private static Integer getElfCodeSum() throws FileNotFoundException {

        List<String> list = new ArrayList<>();

        //Read in the list
        Scanner s = new Scanner(new File("src/main/java/com/overstock/list.txt"));
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();

        //Set the sum to zero
        Integer sum = 0;

        Integer firstNumber;
        Integer currentLastNumber;

        //for each line of the file
        for (String line : list) {

            //Reset i per line
            i = 0;

            //Reset numbers
            firstNumber = null;
            currentLastNumber = null;

            //for each character in the list
            for (int j; i <  line.toCharArray().length; i++) {
                j = i;

                //get the line as chars
                char[] lineChars = line.toCharArray();

                try {
                    //try parsing if it's a number
                    Integer thisNumericChar = Integer.parseInt(String.valueOf(lineChars[j]));

                    if (firstNumber == null) {
                        firstNumber = thisNumericChar;
                        currentLastNumber = thisNumericChar;
                        continue;
                    }

                    currentLastNumber = thisNumericChar;


                } catch (NumberFormatException e) {

                    //if it's not a number
                    Integer thisBufferNumber;

                    //if the remainder of the line is less than the buffer of 5
                    //take the remaining chars
                    if (line.length() - i > i+5)
                        thisBufferNumber = checkBuffer(line.substring(i, i+5));
                    else
                        thisBufferNumber = checkBuffer(line.substring(i));

                    if (thisBufferNumber == null)
                        continue;

                    if (firstNumber == null) {
                        firstNumber = thisBufferNumber;
                        currentLastNumber = thisBufferNumber;
                        continue;
                    }

                    currentLastNumber = thisBufferNumber;
                }
            }

            System.out.println(String.format("%s%s", firstNumber, currentLastNumber));

            if (currentLastNumber != null) {
                sum += Integer.parseInt(String.format("%s%s", firstNumber, currentLastNumber));
                System.out.println(sum);
            }
        }

        return sum;
    }

    private static Integer checkBuffer(String numberString) {

        //Number string values
        final List<String> numberValues = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

        for (String thisNumber : numberValues) {
            //if the beginning of the buffer contains this number return the associated number
            //and have the iterator skip past this
            if (numberString.contains(thisNumber) && numberString.startsWith(thisNumber.substring(0,1))) {
                switch (thisNumber) {
                    case "one":
                        i += numberValues.get(0).length() - 1;
                        return 1;
                    case "two":
                        i += numberValues.get(1).length() - 1;
                        return 2;
                    case "three":
                        i += numberValues.get(2).length() - 1;
                        return 3;
                    case "four":
                        i += numberValues.get(3).length() - 1;
                        return 4;
                    case "five":
                        i += numberValues.get(4).length() - 1;
                        return 5;
                    case "six":
                        i += numberValues.get(5).length() - 1;
                        return 6;
                    case "seven":
                        i += numberValues.get(6).length() - 1;
                        return 7;
                    case "eight":
                        i += numberValues.get(7).length() - 1;
                        return 8;
                    case "nine":
                        i += numberValues.get(8).length() - 1;
                        return 9;
                    default:
                        return null;
                }
            }
        }

        return null;
    }
}