package com.epam.engx.cleancode.dry.task1;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InterestCalculator {

    private static final int AGE = 60;
    private static final double INTEREST_PERCENT = 4.5d;
    private static final double SENIOR_PERCENT = 5.5d;
    private static final int BONUS_AGE = 13;

    public BigDecimal calculateInterest(AccountDetails accountDetails) {
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            return interest(accountDetails);
        } else {
            return BigDecimal.ZERO;
        }
    }

    private boolean isAccountStartedAfterBonusAge(AccountDetails accountDetails) {
        return getDurationBetweenDatesInYears(accountDetails.getBirth(), accountDetails.getStartDate()) > BONUS_AGE;
    }

    private BigDecimal interest(AccountDetails accountDetails) {
        double interest = 0;
        if (isAccountStartedAfterBonusAge(accountDetails)) {
            if (AGE <= accountDetails.getAge()) {
                //interest = (PrincipalAmount * DurationInYears * AnnualInterestRate) / 100
                interest = accountDetails.getBalance().doubleValue()
                        * getDurationBetweenDatesInYears(accountDetails.getStartDate(), new Date()) * SENIOR_PERCENT / 100;
            } else {
                interest = accountDetails.getBalance().doubleValue()
                        * getDurationBetweenDatesInYears(accountDetails.getStartDate(), new Date()) * INTEREST_PERCENT / 100;
            }
        }
        return BigDecimal.valueOf(interest);
    }

    private int getDurationBetweenDatesInYears(Date startDate, Date endDate) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        if (endCalendar.get(Calendar.DAY_OF_YEAR) < startCalendar.get(Calendar.DAY_OF_YEAR))
            return diffYear - 1;
        return diffYear;
    }
}
