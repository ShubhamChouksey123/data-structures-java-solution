package com.shubham.app;

public class MainClass {

    public static void main(String[] args) {
        System.out.println("started");

        printCombinations();
    }

    /**
     * 1. Implement an algorightm to print all valid combination of n pairs of parenthesis.
     * <p>
     * if n = 4
     * <p>
     * a) (((())))
     * b) (()()())
     * c) ((())())
     * d) (()(()))
     * e) ()()()()
     * f) ((()()))
     */


    private static void printCombinationsUtil(int n, int index, int countOpening, int countClosing, String str) {

        if (index == 2 * n) {
            if (countOpening == countClosing) {
                System.out.println("str : " + str);
            }
            return;
        }

        if (countOpening == countClosing) {
            printCombinationsUtil(n, index + 1, countOpening + 1, countClosing, str + "(");
            return;
        }
        printCombinationsUtil(n, index + 1, countOpening +1, countClosing, str + "(");
        printCombinationsUtil(n, index + 1, countOpening, countClosing + 1, str + ")");

    }

    private static void printCombinations() {
        printCombinationsUtil(4, 0, 0, 0, "");
    }
}
