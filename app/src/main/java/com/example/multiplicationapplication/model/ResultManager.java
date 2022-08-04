package com.example.multiplicationapplication.model;

import lombok.Data;

@Data
public class ResultManager {

    private int correctAnswers = 0;
    private int falseAnswers = 0;
    private int points = 0;
    private boolean isCorrectAnswer;

    public void evaluateAnswer(double actual, double expected, long timeDuration) {
        if (expected != actual) {
            falseAnswers++;
            setCorrectAnswer(false);
        } else {
            correctAnswers++;
            setCorrectAnswer(true);
        }
        calculatePoints(timeDuration);
    }

    public void reset() {
        points = 0;
        falseAnswers = 0;
        correctAnswers = 0;
    }

    private void calculatePoints(long timeDuration) {
        if (isCorrectAnswer) {
            int estimatedTimePerQuestion = 9000;
            if (timeDuration < estimatedTimePerQuestion) {
                points += (int) ((estimatedTimePerQuestion - timeDuration) / 1000L);
            }
        } else {
            points = Math.max(--points, 0);
        }
    }
}
