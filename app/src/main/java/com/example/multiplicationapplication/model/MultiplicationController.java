package com.example.multiplicationapplication.model;

import static com.example.multiplicationapplication.Constants.MAX;
import static com.example.multiplicationapplication.Constants.MIN;
import static com.example.multiplicationapplication.Constants.RESULT_STR;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class MultiplicationController {
    public static final int STARTUP_TIME = 2000;
    private final long startMillis;

    private final MathService mathService;
    private final ResultManager resultManager;
    private final HighScoreFileManager highScoreFileManager;

    public MultiplicationController() {
        startMillis = System.currentTimeMillis();
        mathService = new MathService();
        resultManager = new ResultManager();
        highScoreFileManager = new HighScoreFileManager();
    }

    public String evaluateResults(String playerName) throws JsonProcessingException {
        long timeDuration = getTimeDuration();
        int correctAnswers = resultManager.getCorrectAnswers();
        int falseAnswers = resultManager.getFalseAnswers();
        int points = resultManager.getPoints(timeDuration);

        if (points > 0){
            saveHighScore(points, playerName);
        }

        return java.text.MessageFormat.format(
                RESULT_STR, timeDuration / 1000, correctAnswers, falseAnswers, points);
    }

    public String getQuestionStr() {
        return mathService.getQuestion(MIN, MAX);
    }

    public boolean isCorrectAnswer(String actualStr) {
        double expected = mathService.getResult();
        double actual = Double.parseDouble(actualStr);
        return resultManager.isCorrectAnswer(actual, expected);
    }

    private void saveHighScore(int points, String playerName) throws JsonProcessingException {
        PlayerPoints p = new PlayerPoints(playerName, points);
        highScoreFileManager.savePlayerPoints(p);
    }

    private long getTimeDuration() {
        return System.currentTimeMillis() - startMillis - STARTUP_TIME;
    }
}

