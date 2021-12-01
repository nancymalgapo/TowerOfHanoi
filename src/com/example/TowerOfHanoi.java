package com.example;

import java.util.*;
import java.lang.Math;

class TowerOfHanoi {

    private static int diskNumber;
    private static String towerOne, towerTwo, towerThree;
    private final String[] towerNames = new String[3];
    private final Stack<String> tower1 = new Stack<>();
    private final Stack<String> tower2 = new Stack<>();
    private final Stack<String> tower3 = new Stack<>();

    private static Scanner createScanner() {
        return new Scanner(System.in);
    }

    private void getUserName() {
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

    private void validateInput() {
        do { getDiskInput(); } while (!isInRange(diskNumber));
        getTowerNames();
    }

    private void getDiskInput() {
        System.out.print("Enter the number of disks you want to play [from 2-15]: ");
        Scanner scanner = createScanner();

        try {
            diskNumber = Integer.parseInt(scanner.nextLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Disk number input is not an integer. Please try again.");
            getDiskInput();
        }
    }

    private boolean isInRange(int input) {
        if (input > 1 & input <= 15) {
            int availableSteps = ((int) Math.pow(2, input) - 1);
            System.out.println("Available number of steps: " + availableSteps);
            return true;
        }
        else {
            System.out.println("Valid input number of disks should be in range 2-15. " +
                    "You are out of range. Please try again.");
            return false;
        }
    }

    private void getTowerNames() {
        Scanner scanner = createScanner();
        System.out.print("Enter the name of the first tower: ");
        towerOne = scanner.next();
        towerNames[0] = towerOne;
        System.out.print("Enter the name of the second tower: ");
        towerTwo = scanner.next();
        while (Objects.equals(towerOne, towerTwo)) {
            System.out.print("Tower names can't be the same. Please enter a different name for the second tower: ");
            towerTwo = scanner.next();
        }
        towerNames[1] = towerTwo;
        System.out.print("Enter the name of the third tower: ");
        towerThree = scanner.next();
        while (Objects.equals(towerTwo, towerThree) || Objects.equals(towerOne, towerThree)) {
            System.out.print("Tower names can't be the same. Please enter a different name for the third tower: ");
            towerThree = scanner.next();
        }
        towerNames[2] = towerThree;
        System.out.println("First Tower is " + towerOne + ", " + "Second Tower is " + towerTwo + ", "
                + "and Third Tower is " + towerThree);
    }

    private void correctStep(int n, String startPole, String middlePole, String endPole) {
        if (n == 0) { return; }
        correctStep(n - 1, startPole, endPole, middlePole);
        Stack<String> source = towerDecisionMaker(startPole);
        Stack<String> end = towerDecisionMaker(endPole);
        if (source != null) {
            int disc = Integer.parseInt(source.pop());
            if (end != null) {
                end.push(Integer.toString(disc));
            }
        }
        System.out.println("Move " + n + " disk" + " from tower " + startPole + " to tower " + endPole);
        System.out.println(tower1 + " | " + tower2 + " | " + tower3);
        correctStep(n - 1, middlePole, startPole, endPole);
    }

    private void initialState() {
        for (int i=diskNumber; i>=1; i--) {
            tower1.push(Integer.toString(i));
        }
        System.out.println("Initial State --> Tower " + towerOne + " : " + tower1);
        System.out.println("                  Tower " + towerTwo + " : " + tower2);
        System.out.println("                  Tower " + towerThree + " : " + tower3);
    }

    private Stack<String> towerDecisionMaker(String inputPole) {
        if (Objects.equals(inputPole, towerNames[0])) {
            return tower1;
        }
        else if (Objects.equals(inputPole, towerNames[1])) {
            return tower2;
        }
        else if (Objects.equals(inputPole, towerNames[2])) {
            return tower3;
        }
        return null;
    }

    public static void main(String[] args) {
        TowerOfHanoi game = new TowerOfHanoi();
        game.getUserName();
        game.validateInput();
        game.initialState();
        game.correctStep(diskNumber, towerOne, towerTwo, towerThree);
    }
}
