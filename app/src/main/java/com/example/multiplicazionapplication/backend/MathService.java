package com.example.multiplicazionapplication.backend;

import com.example.multiplicazionapplication.MainActivity;

import java.util.Random;

public class MathService {
    private Double result;

    public Double getResult() {
        return result;
    }

    public String getQuestion(int min, int max) {
        int number1 = getRandomNumberBetween(min, max);
        int number2 = getRandomNumberBetween(min, max);
        String operator = getRandomOperator();
        if (operator.equals("/")) {
            result = (double) number1;
            return (number1 * number2) + " / " + number2 + " = ??? ";
        } else {
            result = (double) (number1 * number2);
            return number1 + " x " + number2 + " = ??? ";
        }
    }

    private String getRandomOperator() {
        return (getRandomNumberBetween(1, 2) == 1) ? "x" : "/";
    }

    private int getRandomNumberBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
