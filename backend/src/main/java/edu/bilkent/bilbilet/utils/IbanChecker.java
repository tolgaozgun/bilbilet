package edu.bilkent.bilbilet.utils;

public class IbanChecker {

    public static boolean isValidIBAN(String iban) {
        // Remove any non-alphanumeric characters and convert to uppercase
        iban = iban.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        // Check if the IBAN length is valid for the country
        if (iban.length() < 4 || iban.length() > 34) {
            return false;
        }

        // Move the first four characters to the end and convert letters to digits
        iban = iban.substring(4) + iban.substring(0, 4);
        StringBuilder digits = new StringBuilder();
        for (char c : iban.toCharArray()) {
            if (Character.isLetter(c)) {
                digits.append(Character.getNumericValue(c - 55));
            } else {
                digits.append(c);
            }
        }

        // Calculate the remainder using the MOD-97 algorithm
        long remainder = Long.parseLong(digits.toString()) % 97;

        // If the remainder is 1, the IBAN is valid
        return remainder == 1;
    }

}
