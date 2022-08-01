package com.example.multiplicationapplication.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.example.multiplicationapplication.Constants;
import com.example.multiplicationapplication.view.MainActivity;
import com.example.multiplicationapplication.viewmodel.AppViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AppViewModelTest {

    @Mock
    MainActivity mainActivityMock;

    @InjectMocks
    private AppViewModel appViewModel;

    ResultManager resultManager = new ResultManager();
    @Mock
    MathService mathService;
    @Mock
    HighScoreFileManager highScoreFileManager;

    @Before
    public void setup() throws NoSuchFieldException {
        new FieldSetter(appViewModel, appViewModel.getClass().getDeclaredField("resultManager"))
                .set(resultManager);
        new FieldSetter(appViewModel, appViewModel.getClass().getDeclaredField("mathService"))
                .set(mathService);
        new FieldSetter(appViewModel, appViewModel.getClass().getDeclaredField("highScoreFileManager"))
                .set(highScoreFileManager);
    }

    @Test
    public void okClickedWithFalseAnswer() {
        when(mathService.getResult()).thenReturn(0.0);
        when(mainActivityMock.getResult()).thenReturn("1");

        appViewModel.okClicked();

        assertEquals(0, resultManager.getCorrectAnswers());
        assertEquals(1, resultManager.getFalseAnswers());
        verify(mainActivityMock, times(0)).setQuestion(any());
    }

    @Test
    public void okClickedWithCorrectAnswer() {
        when(mathService.getResult()).thenReturn(0.0);
        when(mainActivityMock.getResult()).thenReturn("0");

        appViewModel.okClicked();

        assertEquals(1, resultManager.getCorrectAnswers());
        assertEquals(0, resultManager.getFalseAnswers());
        verify(mainActivityMock, times(1)).setQuestion(any());
    }

    @Test
    public void okClickedWithMixedAnswer() {
        when(mathService.getResult())
                .thenReturn(0.0)
                .thenReturn(0.0);
        when(mainActivityMock.getResult())
                .thenReturn("1")
                .thenReturn("0");

        appViewModel.okClicked();
        appViewModel.okClicked();

        assertEquals(1, resultManager.getCorrectAnswers());
        assertEquals(1, resultManager.getFalseAnswers());
        verify(mainActivityMock, times(1)).setQuestion(any());
    }

    @Test
    public void setQuestion() {
        String questionStr = "Hugo";
        when(mathService.getQuestion(Constants.MIN, Constants.MAX)).thenReturn(questionStr);

        appViewModel.setQuestion();

        verify(mathService, times(1)).getQuestion(Constants.MIN, Constants.MAX);
        verify(mainActivityMock, times(1)).setQuestion(questionStr);
        verifyNoMoreInteractions(mathService);
        verifyNoMoreInteractions(mainActivityMock);
    }

    @Test
    public void endClicked() throws JsonProcessingException {
        String playerName = "Hugo";
        when(mainActivityMock.getName()).thenReturn(playerName);

        appViewModel.endClicked();

        verify(highScoreFileManager, times(1)).savePlayerPoints(new PlayerPoints(playerName, 0));
        verify(mainActivityMock, times(1)).getName();
        verifyNoMoreInteractions(mathService);
        verifyNoMoreInteractions(mainActivityMock);
    }


}