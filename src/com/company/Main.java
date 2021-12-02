package com.company;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        String compGenNum = Integer.toString(randGen(1,9)) + randGen(1,9) + randGen(1,9) + randGen(1,9); // Inits a 4 digit random number as a string, each digit is between 1 and 9
        String userNum;
        int amtInRightPlace = 0;
        int guesses = 0;
        for (int i = 12; i > 0; i--) { // The user has 12 guesses, therefore this loops 12 times
            System.out.println("You have " + i + " guesses left");
            userNum = askForNumber(); // Calls the method which allows for the input and validation of the users guess
            amtInRightPlace = numCompare(compGenNum, userNum); // Calls the method which compare the computer's and user's numbers
            if (amtInRightPlace == 4) {
                break; // If all digits are correct and are in the correct place it breaks from the loop
            }
            guesses = i; // Allows the system to use guesses outside of the loop
        }
        winLossCheck(amtInRightPlace, guesses, compGenNum); // Calls the method which checks whether the user has won or lost
    }
    public static String input() {
        Scanner input = new Scanner(System.in);;
        return input.nextLine(); // Returns user input
    }
    public static String askForNumberTryCatch() {
        try {
            String userInStr = input(); // Calls method to receive user input
            int userIn = Integer.parseInt(userInStr); // Any value that isn't an input would throw an error and would default return -1 which shows the value is invalid
            if (userInStr.length() == 4) {
                return userInStr; // If it passes validation user input returned
            }
            else {
                System.out.println("Please enter a valid input\n");
                return "-1"; // If the user's input is > || < 4 returns value to show validation failed
            }
        } catch (Exception e) {
            System.out.println("Please enter a valid input\n");
            return "-1"; // Returns value to show validation failed
        }
    }
    public static int randGen(int min, int max) {
        Random rand = new Random();
        return (rand.nextInt(max-min+1) + min); // Generates a random number between certain values and returns it
    }
    public static String askForNumber() {
        String userNum;
        while (true) { // Repeats until a valid inputted
            System.out.println("Enter a 4 digit number:");
            userNum = askForNumberTryCatch(); // Calls method that returns user input
            if (userNum != "-1") {
                break; // If "not" invalid (means input valid) then break
            }
        }
        return userNum;
    }
    public static int numCompare(String compGenNum, String userNum) {
        int amtInRightPlace = 0; // Variable that stores how many digits are in the right place
        for (int i = 0; i < 4; i++) { // There are four digits to check so it loops through 4 times
            if (compGenNum.charAt(i) == userNum.charAt(i)) { // Runs if current character is in the correct place
                System.out.println("Digit " + (i + 1) + " is in the right place");
                amtInRightPlace += 1; // 1 is added to total digits correct
            }
            else if (compGenNum.contains(String.valueOf(userNum.charAt(i)))) { // Runs if current value is in the computer's number but not in the right place
                System.out.println("Digit " + (i + 1) + " is not in the right position but is in the computer's number");
            }
            else { // Runs if it is incorrect
                System.out.println("Digit " + (i + 1) + " is not in the right place");
            }
        }
        System.out.print("\n");
        return amtInRightPlace; // Returns the amount of digits in the right place so it can be checked and the system can be stopped if the user guesses correctly
    }

    public static void winLossCheck(int amtInRightPlace, int guesses, String compGenNum) {
        if (amtInRightPlace < 4) { // If the user's guess has less than 4 correct digits they lose
            System.out.println("You lost!\nThe computer's number was " + compGenNum);
        }
        else { // If they get all digits correct they win!
            System.out.println("You got all digits correct!\nYou had " + (guesses - 1) + " guesses left");
        }
    }
}