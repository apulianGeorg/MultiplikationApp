package com.example.multiplicationapplication.model;

import java.util.Random;

public class MathService {
    public static final String DIVISION_SYMBOL = ":";
    public static final String MULTIPLICATION_SYMBOL = "x";
    private Double result;

    public Double getResult() {
        return result;
    }

    public String getQuestion(int min, int max) {
        int number1 = getRandomNumberBetween(min, max);
        int number2 = getRandomNumberBetween(min, max);
        String operator = getRandomOperator();
        if (operator.equals(DIVISION_SYMBOL)) {
            result = (double) number1;
            return (number1 * number2) + " " + DIVISION_SYMBOL + " " + number2 + " = ??? ";
        } else {
            result = (double) (number1 * number2);
            return number1 + " " + MULTIPLICATION_SYMBOL + " " + number2 + " = ??? ";
        }
    }

    private String getRandomOperator() {
        return (getRandomNumberBetween(1, 2) == 1) ? MULTIPLICATION_SYMBOL : DIVISION_SYMBOL;
    }

    private int getRandomNumberBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
