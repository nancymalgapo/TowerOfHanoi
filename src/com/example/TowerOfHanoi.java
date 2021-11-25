package com.example;

import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.lang.Math;
import java.util.Stack;

public class TowerOfHanoi {

    private static int diskNumber, availableSteps;
    private static String towerOne, towerTwo, towerThree;
    private static final String[] diskNames = {"1", "2", "3"};
    private static final String[] towerNames = new String[3];

    private static Scanner createScanner() {
        return new Scanner(System.in);
    }

    private static void getUserName() {
        System.out.print("Enter your username: ");
        Scanner scanner = createScanner();
        String playerName = scanner.next();
        System.out.println("Hello Player " + playerName + "!");
        System.out.println("Welcome to Tower of Hanoi Game.");
        System.out.println("     |      |      |");
        System.out.println("     +      |      |");
        System.out.println("    -+-     |      |");
        System.out.println("   --+--    |      |");
        System.out.println("  ---+---   |      |");
        System.out.println("=======================");
        System.out.println("The Rule: Move all disks over to the last tower, " +
                "but you cannot place a larger disk onto a smaller disk. " +
                "Good luck, " + playerName + "!");
    }

    private static void validateInput() {
        do { getDiskInput(); } while (!isInRange(diskNumber));
        getTowerNames();
    }

    private static void getDiskInput() {
        System.out.print("Enter the number of disks you want to play [from 3-10]: ");
        Scanner scanner = createScanner();

        try {
            diskNumber = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Disk number input is not an integer. Please try again.");
            getDiskInput();
        }
    }

    private static boolean isInRange(int input) {
        if (input > 2 & input <= 10) {
            availableSteps = ((int) Math.pow(2, input) - 1);
            System.out.println("Available number of steps: " + availableSteps);
            return true;
        }
        else {
            System.out.println("Valid input number of disks should be in range 3-10. " +
                    "You are out of range. Please try again.");
            return false;
        }
    }

    private static void getTowerNames() {
        Scanner scanner = createScanner();
        System.out.print("Enter the name of the first tower: ");
        towerOne = scanner.next();
        towerNames[0] = towerOne;
        System.out.print("Enter the name of the second tower: ");
        towerTwo = scanner.next();
        towerNames[1] = towerTwo;
        System.out.print("Enter the name of the third tower: ");
        towerThree = scanner.next();
        towerNames[2] = towerThree;
        System.out.println("First Tower is " + towerOne + ", " + "Second Tower is " + towerTwo + ", "
                + "and Third Tower is " + towerThree);
    }

    private static boolean isValidInput(String input, String[] inputList) {
        for (String item : inputList) {
            if (Objects.equals(input, item)) {
                return true;
            }
        }
        return false;
    }

    private static void correctStep(int n, String startPole, String middlePole, String endPole) {
        if (n == 0) { return; }
        correctStep(n - 1, startPole, endPole, middlePole);
        System.out.println("Move " + n + " disk" + " from tower " + startPole + " to tower " + endPole);
        correctStep(n - 1, middlePole, startPole, endPole);
    }

    public static void main(String[] args) {
        getUserName();
        validateInput();
        correctStep(diskNumber, towerOne, towerTwo, towerThree);
    }
}
