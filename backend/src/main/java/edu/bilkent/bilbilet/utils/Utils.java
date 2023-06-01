package edu.bilkent.bilbilet.utils;

import java.util.Map;

public class Utils {
    public static String camelToSnake(String camel) {
        StringBuilder snake = new StringBuilder();

        for (int i = 0; i < camel.length(); i++) {
            char current = camel.charAt(i);
            if (Character.isUpperCase(current)) {
                snake.append("-").append(Character.toLowerCase(current));
            } else {
                snake.append(current);
            }
        }

        return snake.toString();
    }

    public static Map<String, Object> camelToSnake(Map<String, Object> params) {
        for (String p : params.keySet()) {
            p = camelToSnake(p);
        }

        return params;
    }
}
