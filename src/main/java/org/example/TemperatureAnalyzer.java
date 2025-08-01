package org.example;

/*
Expected Output:

Enter Day 1 High Temperature (e.g., 28.5): 25.7
Enter Day 2 High Temperature (e.g., 28.5): 28.0
Enter Day 3 High Temperature (e.g., 28.5): 25.7

--- Temperature Analysis ---

Parsed Day 1 Temp (primitive double): 25.7
Parsed Day 2 Temp (primitive double): 28.0

Day 1 (25.7°C) was cooler than Day 2 (28.0°C).

Extracted Day 1 Temp (primitive float from Double wrapper): 25.7
Extracted Day 2 Temp (primitive int from Double wrapper, truncated): 28

The highest temperature recorded was: 28.0°C
 */

import java.util.Objects;
import java.util.Scanner;

public class TemperatureAnalyzer {
    static int dayInput = 1, counterInt = 1;

    static String getInputTemp(Scanner scanner){
        System.out.print("Enter Day " + dayInput++ + " High Temperature (e.g., 28.5): ");
        return scanner.next();
    }

    static void printParsedPrimitiveDouble(double parsedDouble){
        System.out.println("Parsed Day " + counterInt++ + " Temp (primitive double): " + parsedDouble);
    }

    static void printHighestTemp(Double wrapper){
        System.out.println(
                "\nThe highest temperature recorded was: " +
                        wrapper +
                        "°C"
        );
    }

    static void printCustomErrorMessage(){
        System.out.println("\n=============== Invalid Input! ================\n");
        System.out.println("You are inputting an invalid temperature. \nDo not use non numeric characters \nand limit the decimal point to 1.");
        System.out.println("\n===============================================\n");
        System.out.println("Exiting the program...");
        System.out.println("...gracefully");
    }
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String day1TempStr, day2TempStr, day3TempStr;
        double day1TempPrimitive, day2TempPrimitive, day3TempPrimitive;
        Double day1TempWrapper, day2TempWrapper, hotterDayBetween1and2 = null, day3TempWrapper;
        float day1TempFloat;
        int day2TempInt;

        day1TempStr =  getInputTemp(scanner);
        day2TempStr = getInputTemp(scanner);
        day3TempStr = getInputTemp(scanner);
        try {
            day1TempPrimitive = Double.parseDouble(day1TempStr);
            day2TempPrimitive = Double.parseDouble(day2TempStr);
            day3TempPrimitive = Double.parseDouble(day3TempStr);
        } catch (NumberFormatException e) {
            printCustomErrorMessage();
            return;
        }


        System.out.println("\n--- Temperature Analysis ---\n");
        printParsedPrimitiveDouble(day1TempPrimitive);
        printParsedPrimitiveDouble(day2TempPrimitive);

        day1TempWrapper = Double.valueOf(day1TempPrimitive);
        day2TempWrapper = Double.valueOf(day2TempPrimitive);

        if (day1TempWrapper.compareTo(day2TempWrapper) < 0.0) {
            System.out.println(
                    "\nDay 1 ("
                            + day1TempWrapper + "°C) was cooler than Day 2 ("
                            + day2TempWrapper + "°C).\n"
            );
            hotterDayBetween1and2 = day2TempWrapper;
        }
        else if (day1TempWrapper.compareTo(day2TempWrapper) > 0.0) {
            System.out.println(
                    "\nDay 2 ("
                            + day2TempWrapper + "°C) was cooler than Day 1 ("
                            + day1TempWrapper + "°C).\n"
            );
            hotterDayBetween1and2 = day1TempWrapper; // Stores the reference value of the hotter day between 1 and 2.
        }
        else System.out.println("\nIt is " + day1TempWrapper + "°C on both day 1 and 2.\n");

        day1TempFloat = day1TempWrapper.floatValue();
        day2TempInt = day2TempWrapper.intValue();

        System.out.println("Extracted Day 1 Temp (primitive float from Double wrapper): " + day1TempFloat);
        System.out.println("Extracted Day 2 Temp (primitive int from Double wrapper, truncated): " + day2TempInt);

        day3TempWrapper = Double.valueOf(day3TempPrimitive);

        if (hotterDayBetween1and2 == null){ // If day 1 and 2 has the same temperature
            if (day3TempWrapper.compareTo(day2TempWrapper) > 0) printHighestTemp(day3TempWrapper);
            else printHighestTemp(day2TempWrapper);
        } else { // Compares the hotter day between 1 and 2 to the temperature of day 3
            if (day3TempWrapper.compareTo(hotterDayBetween1and2) > 0) printHighestTemp(day3TempWrapper);
            else printHighestTemp(hotterDayBetween1and2);
        }
    }
}
