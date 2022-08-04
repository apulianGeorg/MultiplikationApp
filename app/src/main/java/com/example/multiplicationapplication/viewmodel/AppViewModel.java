package com.example.multiplicationapplication.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.multiplicationapplication.model.Model;
import com.example.multiplicationapplication.model.MultiplicationController;
import com.example.multiplicationapplication.view.HighScoreActivity;

import lombok.SneakyThrows;

public class AppViewModel extends BaseObservable {

    public static final String POINTS_PREFIX = "Punkte: ";
    public static final String LIFE_PREFIX = "Leben: ";
    private final Model model;
    private final MultiplicationController multiplicationController;

    // constructor of ViewModel class
    public AppViewModel() {
        multiplicationController = new MultiplicationController();
        model = new Model("Valentin",
                multiplicationController.getQuestionStr(),
                null,
                POINTS_PREFIX + "0",
                LIFE_PREFIX + "5");
    }

    @Bindable
    public String getPlayerName() {
        return model.getPlayerName();
    }

    public void setPlayerName(String playerName) {
        model.setPlayerName(playerName);
    }

    @Bindable
    public String getQuestionStr() {
        return model.getQuestionStr();
    }

    public void setQuestionStr(String questionStr) {
        model.setQuestionStr(questionStr);
    }

    @Bindable
    public String getResultStr() {
        return model.getResultStr();
    }

    public void setResultStr(String resultStr) {
        model.setResultStr(resultStr);
    }

    @Bindable
    public String getPointsStr() {
        return model.getPointsStr();
    }

    public void setPointsStr(String pointsStr) {
        model.setPointsStr(pointsStr);
    }

    @Bindable
    public String getLifeStr() {
        return model.getLifeStr();
    }

    public void setLifeStr(String lifeStr) {
        model.setLifeStr(lifeStr);
    }

    public boolean afterEditingResult(View view, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) ||
                (actionId == EditorInfo.IME_ACTION_DONE)) {
            //do what you want on the press of 'done'
            okClicked(view);
        }
        return false;
    }

    @SneakyThrows
    public void endClicked(View view) {
        multiplicationController.saveResults(getPlayerName());
        resetResults();
        Context context = view.getContext();
        Intent intent = new Intent(view.getContext(), HighScoreActivity.class);
        intent.putExtra("results", multiplicationController.getResultStr());
        context.startActivity(intent);
    }

    private void okClicked(View view) {
        if (answerIsInvalid()) {
            return;
        }
        multiplicationController.evaluateAnswer(getResultStr());
        setPointsStr(POINTS_PREFIX + multiplicationController.getPoints());
        notifyPropertyChanged(BR.pointsStr);
        actionDependentOnAnswer(view);
    }

    private void resetResults() {
        multiplicationController.resetResults();
        setPointsStr(POINTS_PREFIX + "0");
    }

    private boolean answerIsInvalid() {
        String answer = getResultStr();
        return null == answer || "".equals(answer) || ".".equals(answer);
    }

    private void actionDependentOnAnswer(View view) {
        if (multiplicationController.isCorrectAnswer()) {
            setQuestionStr(multiplicationController.getQuestionStr());
            notifyPropertyChanged(BR.questionStr);
            setResultStr("");
            notifyPropertyChanged(BR.resultStr);
            return;
        }
        if (multiplicationController.hasNoMoreLife()) {
            endClicked(view);
        }
    }
}