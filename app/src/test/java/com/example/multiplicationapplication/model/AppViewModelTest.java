package com.example.multiplicationapplication.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.multiplicationapplication.viewmodel.AppViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AppViewModelTest {

    @InjectMocks
    private AppViewModel appViewModel;

    @Mock
    MultiplicationController multiplicationControllerMock;
    @Mock
    Model modelMock;

    @Before
    public void setup() throws NoSuchFieldException {
        new FieldSetter(appViewModel, appViewModel.getClass().getDeclaredField("model")
                .set(multiplicationControllerMock);
        new FieldSetter(appViewModel, appViewModel.getClass().getDeclaredField("multiplicationController"))
                .set(multiplicationControllerMock);
    }

    @Test
    public void okClickedWithFalseAnswer() {
        when(mathService.getResult()).thenReturn(0.0);
        when(appViewModelMock.getResultStr()).thenReturn("1");

        appViewModel.okClicked();

        assertEquals(0, resultManager.getCorrectAnswers());
        assertEquals(1, resultManager.getFalseAnswers());
        verify(appViewModelMock, times(0)).setQuestionStr(any());
    }

    @Test
    public void okClickedWithCorrectAnswer() {
        when(mathService.getResult()).thenReturn(0.0);
        when(appViewModelMock.getResultStr()).thenReturn("0");

        appViewModel.okClicked();

        assertEquals(1, resultManager.getCorrectAnswers());
        assertEquals(0, resultManager.getFalseAnswers());
        verify(appViewModelMock, times(1)).setQuestionStr(any());
    }

    @Test
    public void okClickedWithMixedAnswer() {
        when(mathService.getResult())
                .thenReturn(0.0)
                .thenReturn(0.0);
        when(appViewModelMock.getResultStr())
                .thenReturn("1")
                .thenReturn("0");

        appViewModel.okClicked();
        appViewModel.okClicked();

        assertEquals(1, resultManager.getCorrectAnswers());
        assertEquals(1, resultManager.getFalseAnswers());
        verify(appViewModelMock, times(1)).setQuestionStr(any());
    }

/*    @Test
    public void setQuestion() {
        String questionStr = "Hugo";
        when(mathService.getQuestion(Constants.MIN, Constants.MAX)).thenReturn(questionStr);

        appViewModel.setQuestionStr();

        verify(mathService, times(1)).getQuestion(Constants.MIN, Constants.MAX);
        verify(appViewModelMock, times(1)).setQuestionStr(questionStr);
        verifyNoMoreInteractions(mathService);
        verifyNoMoreInteractions(appViewModelMock);
    }*/

/*    @Test
    public void endClicked() throws JsonProcessingException {
        String playerName = "Hugo";
        when(appViewModelMock.getPlayerName()).thenReturn(playerName);

        appViewModel.cli();

        verify(highScoreFileManager, times(1)).savePlayerPoints(new PlayerPoints(playerName, 0));
        verify(appViewModelMock, times(1)).getPlayerName();
        verifyNoMoreInteractions(mathService);
        verifyNoMoreInteractions(appViewModelMock);
    }*/


}