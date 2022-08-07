package com.example.multiplicationapplication.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ResultServiceTest {
    private ResultService resultService = new ResultService();

    @Test
    public void initTest() {
        assertEquals(0, resultService.getCorrectAnswers());
        assertEquals(0, resultService.getFalseAnswers());
        assertEquals(0, resultService.getPoints(10000));
        assertEquals(0, resultService.getPoints(1000));
    }


    @Test
    public void onlyFalseAnswersTest() {
        assertFalse(resultService.isCorrectAnswer(0, 1));
        assertFalse(resultService.isCorrectAnswer(0, 1));
        assertFalse(resultService.isCorrectAnswer(0, 1));
        assertEquals(0, resultService.getCorrectAnswers());
        assertEquals(3, resultService.getFalseAnswers());
        assertEquals(0, resultService.getPoints(10000));
    }

    @Test
    public void onlyCorrectAnswersTest() {
        assertTrue(resultService.isCorrectAnswer(1, 1));
        assertTrue(resultService.isCorrectAnswer(1, 1));
        assertTrue(resultService.isCorrectAnswer(1, 1));
        assertEquals(3, resultService.getCorrectAnswers());
        assertEquals(0, resultService.getFalseAnswers());
        assertEquals(12, resultService.getPoints(10000));
    }

    @Test
    public void mixedAnswersTest() {
        assertTrue(resultService.isCorrectAnswer(1, 1));
        assertTrue(resultService.isCorrectAnswer(1, 1));
        assertTrue(resultService.isCorrectAnswer(1, 1));
        assertFalse(resultService.isCorrectAnswer(0, 1));
        assertTrue(resultService.isCorrectAnswer(1, 1));
        assertFalse(resultService.isCorrectAnswer(0, 1));

        assertEquals(4, resultService.getCorrectAnswers());
        assertEquals(2, resultService.getFalseAnswers());
        assertEquals(16, resultService.getPoints(10000));
    }
}