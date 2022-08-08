package com.example.multiplicationapplication.model;

import static com.example.multiplicationapplication.Constants.MAX;
import static com.example.multiplicationapplication.Constants.MIN;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MultiplicationController {

    private long startMillis;

    private static final String POINTS_PREFIX = "Punkte: ";
    private static final String LIFE_PREFIX = "Leben: ";

    private final MathService mathService;
    private final ResultService resultService;
    private final HighScoreFileManager highScoreFileManager;
    private final BadgeService badgeService;

    public MultiplicationController() {
        mathService = new MathService();
        resultService = new ResultService();
        highScoreFileManager = new HighScoreFileManager();
        badgeService = new BadgeService();
    }

    public void saveResults(String playerName) throws JsonProcessingException {
        int points = resultService.getPoints();
        if (points > 0) {
            saveHighScore(points, playerName);
        }
    }

    public String getPointsStr() {
        return POINTS_PREFIX + resultService.getPoints();
    }

    public String getQuestionStr() {
        startMillis = System.currentTimeMillis();
        return mathService.getQuestion(MIN, MAX);
    }


    public boolean isValidAnswer(String resultStr) {
        return null != resultStr && !"".equals(resultStr) && !".".equals(resultStr);
    }

    public void evaluateAnswer(String resultStr) {
        long timeDuration = getTimeDuration();
        double expected = mathService.getResult();
        double actual = Double.parseDouble(resultStr);
        resultService.evaluateAnswer(actual, expected, timeDuration);
    }

    public boolean isCorrectAnswer() {
        return resultService.isCorrectAnswer();
    }

    public void resetResults() {
        resultService.reset();
    }

    public boolean hasNoMoreLife() {
        return resultService.getLife() == 0;
    }

    public Badge getNewBadge() {
        return badgeService.getNewBadge(resultService.getPoints());
    }

    public String getLifeStr() {
        return LIFE_PREFIX + resultService.getLife();
    }

    private void saveHighScore(int points, String playerName) throws JsonProcessingException {
        PlayerPoints p = new PlayerPoints(playerName, points);
        highScoreFileManager.savePlayerPoints(p);
    }

    private long getTimeDuration() {
        return System.currentTimeMillis() - startMillis;
    }
}

