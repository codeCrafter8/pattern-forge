package com.example.patternforge.service;

import org.springframework.stereotype.Component;

@Component
public class VariableFormatter {

    String format(String variable) {
        if (variable == null || variable.isEmpty()) {
            return variable;
        }

        StringBuilder result = new StringBuilder();
        result.append(Character.toUpperCase(variable.charAt(0)));

        for (int i = 1; i < variable.length(); i++) {
            char currentChar = variable.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                result.append(' ');
            }
            result.append(currentChar);
        }

        return result.toString();
    }

}
