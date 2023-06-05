package edu.bilkent.bilbilet.utils;

import java.time.LocalDate;

public class CreditCardChecker {

    public static boolean isValidCreditCardNumber(String cardNumber){
        // Remove any non-digit characters
        cardNumber = cardNumber.replaceAll("\\D", "");

        // Check if the card number is the correct length
        if (cardNumber.length() != 16) {
            return false;
        }

        // Reverse the card number for the Luhn algorithm
        StringBuilder reversedCardNumber = new StringBuilder(cardNumber).reverse();

        int sum = 0;
        for (int i = 0; i < reversedCardNumber.length(); i++) {
            int digit = Character.getNumericValue(reversedCardNumber.charAt(i));

            // Double every second digit
            if (i % 2 == 1) {
                digit *= 2;
                if (digit > 9) {
                    digit = digit - 9;
                }
            }

            sum += digit;
        }

        // Check if the sum is divisible by 10
        if (sum % 10 != 0) {
            return false;
        }

        return true;
    }

    public static boolean isValidExpiration(int month, int year){
        // Check if the card has expired
        LocalDate currentDate = LocalDate.now();

        // If only the last two digits are needed, use % 100 at the end.
        int currentYear = currentDate.getYear();

        if (year < currentYear || (year == currentYear
                && month < currentDate.getMonthValue())) {
            return true;
        }

        return false;

    }

}
