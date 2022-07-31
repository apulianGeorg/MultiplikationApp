package com.example.multiplicazionapplication.backend;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.multiplicazionapplication.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

public class MathServiceTest {
    private final MathService mathService = new MathService();

    @Test
    public void testWithMinMaxOne() {
        String textMultiplication = "1 x 1 = ??? ";
        String textDivision = "1 / 1 = ??? ";
        String result = mathService.getQuestion(1, 1);
        assertTrue("Expected: " + textMultiplication + '\n' +
                        "Expected: " + textDivision + '\n' +
                        "Actual:   " + result,
                result.equals(textMultiplication) || result.equals(textDivision));
        assertEquals(1, (double) mathService.getResult(), 0.0);
    }

    @Test
    public void testWithMinMaxBetweenOneAndTen() {
        for (int i = 0; i < 10; i++) {
            String question = mathService.getQuestion(1, 10);
            String[] questionArr = question.split(" ");

            assertEquals(question, 5, questionArr.length);
            int number1 = Integer.parseInt(questionArr[0]);
            String operator = questionArr[1];
            int number2 = Integer.parseInt(questionArr[2]);
            assertTrue(question, "/".equals(operator) || "x".equals(operator));
            int result = getResult(number1, operator, number2);
            assertEquals(question, (double) mathService.getResult(), result, 0.0);
        }
    }

    private int getResult(int number1, String operator, int number2) {
        if ("/".equals(operator)) {
            return number1 / number2;
        } else {
            return number1 * number2;
        }
    }
}