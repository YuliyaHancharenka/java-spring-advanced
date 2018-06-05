package com.epam.engx.cleancode.naming.task3;

public class HarshadNumbersSearch {

    private static final int INITIAL_NUMBERS_RANGE = 1000;
    private static final int NUMBER_BASE = 10;

    public static void main(String[] args) {
        for (int i = 1; i <= INITIAL_NUMBERS_RANGE; i++) {
            printHarshadNumbers(i);
        }
    }

    private static void printHarshadNumbers(int i) {
        if (isHarshadNumber(i)) {
            System.out.println(i);
        }
    }

    private static boolean isHarshadNumber(int n) {
        int sum = 0;
        for (int temp = n; temp > 0; temp /= NUMBER_BASE) {
            sum += temp % NUMBER_BASE;
        }
        return n % sum == 0;
    }
}
