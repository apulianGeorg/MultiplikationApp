package com.example.multiplicazionapplication.backend;

import static com.example.multiplicazionapplication.Constants.MAX;
import static com.example.multiplicazionapplication.Constants.MIN;
import static com.example.multiplicazionapplication.Constants.RESULT_STR;

import com.example.multiplicazionapplication.MainActivity;
import com.fasterxml.jackson.core.JsonProcessingException;

public class MultiplicationController {

    private long startMillis;

    private MathService mathService;
    private ResultManager resultManager;
    private HighScoreFileManager highScoreFileManager;

    private final MainActivity mainActivity;


    public MultiplicationController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        initialize();
    }

    public String endClicked() throws JsonProcessingException {
        long timeDuration = getTimeDuration();
        int correctAnswers = resultManager.getCorrectAnswers();
        int falseAnswers = resultManager.getFalseAnswers();
        int points = resultManager.getPoints(timeDuration);

        saveHighScore(points);

        return java.text.MessageFormat.format(
                RESULT_STR, timeDuration / 1000, correctAnswers, falseAnswers, points);
    }

    public void setQuestion(){
        mainActivity.setQuestion(mathService.getQuestion(MIN, MAX));
    }

    public void okClicked() {
        double expected = mathService.getResult();
        double actual = Double.parseDouble(mainActivity.getResult());
        if (resultManager.isCorrectAnswer(actual, expected)) {
            setQuestion();
        }
    }

    private void initialize() {
        startMillis = System.currentTimeMillis();
        resultManager = new ResultManager();
        mathService = new MathService();
        highScoreFileManager = new HighScoreFileManager();
    }

    private void saveHighScore(int points) throws JsonProcessingException {
        PlayerPoints p = new PlayerPoints(mainActivity.getName(), points);
        highScoreFileManager.savePlayerPoints(p);
    }

    private long getTimeDuration() {
        return System.currentTimeMillis() - startMillis;
    }
}
