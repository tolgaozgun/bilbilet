package edu.bilkent.bilbilet.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.ValidationException;

public class ParamUtils {
    public static final SimpleDateFormat DATE_FORMAT_RENT_CAR = new SimpleDateFormat("yyyy-MM-dd");

    public static String camelToSnake(String camel) {
        StringBuilder snake = new StringBuilder();

        for (int i = 0; i < camel.length(); i++) {
            char current = camel.charAt(i);
            if (Character.isUpperCase(current)) {
                snake.append("_").append(Character.toLowerCase(current));
            } else {
                snake.append(current);
            }
        }

        return snake.toString();
    }

    public static Map<String, Object> camelToSnake(Map<String, Object> params) {
        Map<String, Object> updatedParams = new HashMap<>();

        for (String p : params.keySet()) {
            String snake = camelToSnake(p);
            updatedParams.put(snake, params.get(p));
        }

        return updatedParams;
    }

    // public static boolean isValidDate(String date) {
    //     try {               
    //         Date dateObj = DATE_FORMAT_RENT_CAR.parse(date);
    //     } catch (ParseException e) {
    //         e.printStackTrace();
    //         throw new ValidationException("Date is not valid");
    //     }
    // }
}
