package com.example.multiplicationapplication.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ResultManagerTest {
    private ResultManager resultManager = new ResultManager();

    @Test
    public void initTest() {
        assertEquals(0, resultManager.getCorrectAnswers());
        assertEquals(0, resultManager.getFalseAnswers());
        assertEquals(0, resultManager.getPoints(10000));
        assertEquals(0, resultManager.getPoints(1000));
    }


    @Test
    public void onlyFalseAnswersTest() {
        assertFalse(resultManager.isCorrectAnswer(0, 1));
        assertFalse(resultManager.isCorrectAnswer(0, 1));
        assertFalse(resultManager.isCorrectAnswer(0, 1));
        assertEquals(0, resultManager.getCorrectAnswers());
        assertEquals(3, resultManager.getFalseAnswers());
        assertEquals(0, resultManager.getPoints(10000));
    }

    @Test
    public void onlyCorrectAnswersTest() {
        assertTrue(resultManager.isCorrectAnswer(1, 1));
        assertTrue(resultManager.isCorrectAnswer(1, 1));
        assertTrue(resultManager.isCorrectAnswer(1, 1));
        assertEquals(3, resultManager.getCorrectAnswers());
        assertEquals(0, resultManager.getFalseAnswers());
        assertEquals(12, resultManager.getPoints(10000));
    }

    @Test
    public void mixedAnswersTest() {
        assertTrue(resultManager.isCorrectAnswer(1, 1));
        assertTrue(resultManager.isCorrectAnswer(1, 1));
        assertTrue(resultManager.isCorrectAnswer(1, 1));
        assertFalse(resultManager.isCorrectAnswer(0, 1));
        assertTrue(resultManager.isCorrectAnswer(1, 1));
        assertFalse(resultManager.isCorrectAnswer(0, 1));

        assertEquals(4, resultManager.getCorrectAnswers());
        assertEquals(2, resultManager.getFalseAnswers());
        assertEquals(16, resultManager.getPoints(10000));
    }
}