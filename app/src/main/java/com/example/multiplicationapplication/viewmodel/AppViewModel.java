package com.example.multiplicationapplication.viewmodel;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.multiplicationapplication.model.Model;
import com.example.multiplicationapplication.model.MultiplicationController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class AppViewModel extends BaseObservable {

    public static final String POINTS_PREFIX = "Punkte: ";
    private final Model model;
    private final MultiplicationController multiplicationController;

    // constructor of ViewModel class
    public AppViewModel() {
        multiplicationController = new MultiplicationController();
        model = new Model("Valentin",
                multiplicationController.getQuestionStr(),
                null,
                POINTS_PREFIX + "0");
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

    public String getResults() {
        try {
            return multiplicationController.evaluateResults(getPlayerName());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public boolean okClicked(int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) ||
                (actionId == EditorInfo.IME_ACTION_DONE)) {
            //do what you want on the press of 'done'
            okClicked();
        }
        return false;
    }

    public void okClicked() {
        if (answerIsInvalid()){
            return;
        }
        multiplicationController.evaluateAnswer(getResultStr());
        setPointsStr(POINTS_PREFIX + multiplicationController.getPoints());
        notifyPropertyChanged(BR.pointsStr);
        actionDependentOnAnswer();
    }

    public void resetResults() {
        multiplicationController.resetResults();
        setPointsStr(POINTS_PREFIX + "0");
    }

    private boolean answerIsInvalid() {
        String answer= getResultStr();
        return null == answer || "".equals(answer) || ".".equals(answer);
    }

    private void actionDependentOnAnswer() {
        if (multiplicationController.isCorrectAnswer()) {
            setQuestionStr(multiplicationController.getQuestionStr());
            notifyPropertyChanged(BR.questionStr);
            setResultStr("");
            notifyPropertyChanged(BR.resultStr);
        }
    }

    /*private GradientDrawable getCircle() {
        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.OVAL);
        gd.setColor(Color.rgb(255, 0, 0));
        gd.setCornerRadius(5);
        gd.setStroke(4, Color.rgb(255, 255, 255));
        return gd;
    }*/
}