package com.example.multiplicationapplication.model;

import static com.example.multiplicationapplication.Constants.MAX;
import static com.example.multiplicationapplication.Constants.MIN;
import static com.example.multiplicationapplication.Constants.RESULT_STR;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MultiplicationController {

    private long startMillis;

    private final MathService mathService;
    private final ResultManager resultManager;
    private final HighScoreFileManager highScoreFileManager;

    public MultiplicationController() {
        mathService = new MathService();
        resultManager = new ResultManager();
        highScoreFileManager = new HighScoreFileManager();
    }

    public String evaluateResults(String playerName) throws JsonProcessingException {
        int correctAnswers = resultManager.getCorrectAnswers();
        int falseAnswers = resultManager.getFalseAnswers();
        int points = resultManager.getPoints();

        if (points > 0) {
            saveHighScore(points, playerName);
        }

        return java.text.MessageFormat.format(
                RESULT_STR, correctAnswers, falseAnswers, points);
    }

    public int getPoints() {
        return resultManager.getPoints();
    }

    public String getQuestionStr() {
        startMillis = System.currentTimeMillis();
        return mathService.getQuestion(MIN, MAX);
    }

    public void evaluateAnswer(String resultStr) {
        long timeDuration = getTimeDuration();
        double expected = mathService.getResult();
        double actual = Double.parseDouble(resultStr);
        resultManager.evaluateAnswer(actual, expected, timeDuration);
    }

    public boolean isCorrectAnswer() {
        return resultManager.isCorrectAnswer();
    }

    public void resetResults() {
        resultManager.reset();
    }

    private void saveHighScore(int points, String playerName) throws JsonProcessingException {
        PlayerPoints p = new PlayerPoints(playerName, points);
        highScoreFileManager.savePlayerPoints(p);
    }

    private long getTimeDuration() {
        return System.currentTimeMillis() - startMillis;
    }
}

