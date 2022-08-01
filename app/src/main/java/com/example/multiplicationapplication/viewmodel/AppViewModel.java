package com.example.multiplicationapplication.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.multiplicationapplication.model.Model;
import com.example.multiplicationapplication.model.MultiplicationController;
import com.example.multiplicationapplication.model.PlayerPoints;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class AppViewModel extends BaseObservable {

    private final Model model;
    private final MultiplicationController multiplicationController;

    // constructor of ViewModel class
    public AppViewModel() {
        multiplicationController = new MultiplicationController();
        model = new Model("Valentin", multiplicationController.getQuestionStr(), null);
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

    public String getResults(){
        try {
            return multiplicationController.evaluateResults(getPlayerName());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public void okClicked() {
        if (multiplicationController.isCorrectAnswer(getResultStr())) {
            setQuestionStr(multiplicationController.getQuestionStr());
            notifyPropertyChanged(BR.questionStr);
            setResultStr("");
            notifyPropertyChanged(BR.resultStr);
        }
    }
}