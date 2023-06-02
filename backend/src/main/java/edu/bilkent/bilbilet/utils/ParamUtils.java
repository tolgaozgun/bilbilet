package edu.bilkent.bilbilet.utils;

import java.util.HashMap;
import java.util.Map;

public class ParamUtils {
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
}
