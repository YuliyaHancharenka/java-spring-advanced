package com.epam.engx.cleancode.comments.task1;

import com.epam.engx.cleancode.comments.task1.thirdpartyjar.InvalidInputException;

public class MortgageInstallmentCalculator {

    public static double calculateMonthlyPayment(int principalAmount, int mortgageTermInYears, double rateOfInterest) {

        checkParametersArePositiveNumbers(principalAmount, mortgageTermInYears, rateOfInterest);

        rateOfInterest = getDecimalRateOfInterest(rateOfInterest);
        double mortgageTermInMonth = getMortgageTermInMonth(mortgageTermInYears);

        return calculateMonthlyPayment(principalAmount, rateOfInterest, mortgageTermInMonth);
    }

    private static double calculateMonthlyPayment(int principalAmount, double rateOfInterest, double mortgageTermInMonth) {

        if (rateOfInterest == 0)
            return principalAmount / mortgageTermInMonth;

        double monthlyRate = rateOfInterest / 12.0;
        return (principalAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -mortgageTermInMonth));
    }

    private static double getMortgageTermInMonth(int mortgageTermInYears) {
        return (double) (mortgageTermInYears * 12);
    }

    private static double getDecimalRateOfInterest(double rateOfInterest) {
        rateOfInterest /= 100.0;
        return rateOfInterest;
    }

    private static void checkParametersArePositiveNumbers(int principalAmount, int mortgageTermInYears, double rateOfInterest) {
        if (principalAmount < 0 || mortgageTermInYears <= 0 || rateOfInterest < 0) {
            throw new InvalidInputException("Negative values are not allowed");
        }
    }
}
