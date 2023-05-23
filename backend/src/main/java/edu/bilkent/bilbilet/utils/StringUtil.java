package edu.bilkent.bilbilet.utils;

import java.util.regex.Pattern;

public class StringUtil {
    public static boolean isNumeric(String str) {
        if (str.isBlank()) {
            return false; 
        }

        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(str).matches();
    }
}
