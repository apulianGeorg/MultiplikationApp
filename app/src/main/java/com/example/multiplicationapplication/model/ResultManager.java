package com.example.multiplicationapplication.model;

public class ResultManager {

    private int correctAnswers = 0;
    private int falseAnswers = 0;
    private final int estimatedTimePerQuestion = 7;
    private int points = 0;

    public boolean isCorrectAnswer(double expected, double actual) {
        if (expected == actual) {
            correctAnswers++;
            return true;
        }
        falseAnswers++;
        return false;
    }

    public int getPoints(long timeDuration) {
        if (correctAnswers == 0){
            return 0;
        }
        long timePerQuestion = timeDuration / (1000 * correctAnswers);
        long pointsPerQuestion = estimatedTimePerQuestion - timePerQuestion;
        points = Math.max((int) ((pointsPerQuestion * correctAnswers) - 2 * falseAnswers), 0);
        return points;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getFalseAnswers() {
        return falseAnswers;
    }


}
