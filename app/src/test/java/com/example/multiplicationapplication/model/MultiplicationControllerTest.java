package com.example.multiplicationapplication.model;

import static com.example.multiplicationapplication.Constants.MAX;
import static com.example.multiplicationapplication.Constants.MIN;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;

public class MultiplicationControllerTest {
    private final MultiplicationController multiplicationController = new MultiplicationController();

    MathService mathServiceMock = Mockito.mock(MathService.class);
    ResultService resultServiceMock = Mockito.mock(ResultService.class);
    HighScoreFileManager highScoreFileManagerMock = Mockito.mock(HighScoreFileManager.class);
    BadgeService badgeServiceMock = Mockito.mock(BadgeService.class);

    @Before
    public void init() throws NoSuchFieldException {
        new FieldSetter(multiplicationController, multiplicationController.getClass().getDeclaredField("mathService"))
                .set(mathServiceMock);
        new FieldSetter(multiplicationController, multiplicationController.getClass().getDeclaredField("resultService"))
                .set(resultServiceMock);
        new FieldSetter(multiplicationController, multiplicationController.getClass().getDeclaredField("highScoreFileManager"))
                .set(highScoreFileManagerMock);
        new FieldSetter(multiplicationController, multiplicationController.getClass().getDeclaredField("badgeService"))
                .set(badgeServiceMock);
        new FieldSetter(multiplicationController, multiplicationController.getClass().getDeclaredField("startMillis"))
                .set(System.currentTimeMillis());
    }


    @Test
    public void resetTest() {
        multiplicationController.resetResults();
        Mockito.verify(resultServiceMock, times(1)).reset();
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void saveResultsWithZeroPointsTest() throws JsonProcessingException {
        Mockito.when(resultServiceMock.getPoints()).thenReturn(0);
        multiplicationController.saveResults("Hugo");
        Mockito.verify(resultServiceMock, times(1)).getPoints();
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void saveResultsWithPointsTest() throws JsonProcessingException {
        Mockito.when(resultServiceMock.getPoints()).thenReturn(1);
        multiplicationController.saveResults("Hugo");
        Mockito.verify(resultServiceMock, times(1)).getPoints();
        Mockito.verify(highScoreFileManagerMock, times(1)).savePlayerPoints(new PlayerPoints("Hugo", 1));
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void getQuestionStrTest() {
        multiplicationController.getQuestionStr();
        Mockito.verify(mathServiceMock, times(1)).getQuestion(MIN, MAX);
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void hasNoMoreLifeTest() {
        multiplicationController.hasNoMoreLife();
        Mockito.verify(resultServiceMock, times(1)).getLife();
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void getNewBadgeTest() {
        int points = 1;
        when(resultServiceMock.getPoints()).thenReturn(points);

        multiplicationController.getNewBadge();

        Mockito.verify(resultServiceMock, times(1)).getPoints();
        Mockito.verify(badgeServiceMock, times(1)).getNewBadge(points);
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void getLifeTest() {
        multiplicationController.getLifeStr();
        Mockito.verify(resultServiceMock, times(1)).getLife();
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void getPointsTest() {
        multiplicationController.getPointsStr();
        Mockito.verify(resultServiceMock, times(1)).getPoints();
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void evaluateAnswerTest() {
        String resultStr = "1";
        double expected = 2;
        when(mathServiceMock.getResult()).thenReturn(expected);
        multiplicationController.evaluateAnswer(resultStr);
        Mockito.verify(mathServiceMock, times(1)).getResult();
        double actual = Double.parseDouble(resultStr);
        Mockito.verify(resultServiceMock, times(1)).evaluateAnswer(eq(actual), eq(expected), anyLong());
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void isCorrectAnswerTest() {
        multiplicationController.isCorrectAnswer();
        Mockito.verify(resultServiceMock, times(1)).isCorrectAnswer();
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }

    @Test
    public void isValidAnswerTest() {
        assertFalse(multiplicationController.isValidAnswer(null));
        assertFalse(multiplicationController.isValidAnswer("."));
        assertFalse(multiplicationController.isValidAnswer(""));
        assertTrue(multiplicationController.isValidAnswer("2"));
        assertTrue(multiplicationController.isValidAnswer("1.2"));
    }

    @Test
    public void resetResultsTest() {
        multiplicationController.resetResults();
        Mockito.verify(resultServiceMock, times(1)).reset();
        Mockito.verifyNoMoreInteractions(resultServiceMock);
        Mockito.verifyNoMoreInteractions(mathServiceMock);
        Mockito.verifyNoMoreInteractions(highScoreFileManagerMock);
        Mockito.verifyNoMoreInteractions(badgeServiceMock);
    }
}