package com.example.multiplicationapplication.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ResultServiceTest {
    private final ResultService resultService = new ResultService();

    @Test
    public void initTest() {
        resultService.reset();
        assertEquals(0, resultService.getCorrectAnswers());
        assertEquals(0, resultService.getFalseAnswers());
        assertEquals(0, resultService.getPoints());
        assertEquals(5, resultService.getLife());
        assertFalse(resultService.isCorrectAnswer());
    }

    @Test
    public void falseAnswersTest() {
        resultService.reset();
        resultService.evaluateAnswer(1, 2, 1000);
        assertFalse(resultService.isCorrectAnswer());
        assertEquals(1, resultService.getFalseAnswers());
        assertEquals(0, resultService.getCorrectAnswers());
        assertEquals(0, resultService.getPoints());
        assertEquals(4, resultService.getLife());
    }

    @Test
    public void correctAnswersTest() {
        resultService.reset();
        resultService.evaluateAnswer(1, 1, 1000);
        assertTrue(resultService.isCorrectAnswer());
        assertEquals(0, resultService.getFalseAnswers());
        assertEquals(1, resultService.getCorrectAnswers());
        assertEquals(8, resultService.getPoints());
        assertEquals(5, resultService.getLife());
    }

    @Test
    public void pointsTest() {
        resultService.reset();
        resultService.evaluateAnswer(1, 1, 999);
        assertEquals(8, resultService.getPoints());

        resultService.reset();
        resultService.evaluateAnswer(1, 1, 4100);
        assertEquals(4, resultService.getPoints());

        resultService.reset();
        resultService.evaluateAnswer(1, 1, 6600);
        assertEquals(2, resultService.getPoints());

        resultService.reset();
        resultService.evaluateAnswer(1, 1, 8999);
        assertEquals(0, resultService.getPoints());

        resultService.reset();
        resultService.evaluateAnswer(1, 1, 9000);
        assertEquals(0, resultService.getPoints());
    }

    @Test
    public void mixedAnswersTest() {
        resultService.reset();
        resultService.evaluateAnswer(1, 1, 1000);
        resultService.evaluateAnswer(1, 0, 1000);
        resultService.evaluateAnswer(0, 1, 1000);
        resultService.evaluateAnswer(1, 1, 6000);
        resultService.evaluateAnswer(1, 0, 6000);

        assertFalse(resultService.isCorrectAnswer());
        assertEquals(3, resultService.getFalseAnswers());
        assertEquals(2, resultService.getCorrectAnswers());
        assertEquals(11, resultService.getPoints());
        assertEquals(2, resultService.getLife());
    }

}