package com.example.multiplicationapplication.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModel;

import com.example.multiplicationapplication.model.Badge;
import com.example.multiplicationapplication.model.Model;
import com.example.multiplicationapplication.model.MultiplicationController;
import com.example.multiplicationapplication.view.BadgeActivity;
import com.example.multiplicationapplication.view.HighScoreActivity;

import lombok.SneakyThrows;

public class AppViewModel extends ViewModel {
    private final Observer observer = new Observer();

    public Observer getObserver() {
        return observer;
    }

    public static class Observer extends BaseObservable {
        private final MultiplicationController multiplicationController;
        private final Model model;

        public Observer() {
            multiplicationController = new MultiplicationController();
            model = new Model("Valentin",
                    multiplicationController.getQuestionStr(),
                    null,
                    multiplicationController.getPointsStr(),
                    multiplicationController.getLifeStr());
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
            notifyPropertyChanged(BR.questionStr);
        }

        @Bindable
        public String getResultStr() {
            return model.getResultStr();
        }

        public void setResultStr(String resultStr) {
            model.setResultStr(resultStr);
            notifyPropertyChanged(BR.resultStr);
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
                okClicked(view);
            }
            return false;
        }

        @SneakyThrows
        public void endClicked(View view) {
            multiplicationController.saveResults(getPlayerName());
            multiplicationController.resetResults();
            setPointsStr("0");
            startHighScoreActivity(view.getContext());
        }

        private void startHighScoreActivity(Context context) {
            Intent intent = new Intent(context, HighScoreActivity.class);
            context.startActivity(intent);
        }

        private void okClicked(View view) {
            if (!multiplicationController.isValidAnswer(getResultStr())) {
                return;
            }

            multiplicationController.evaluateAnswer(getResultStr());
            if (multiplicationController.isCorrectAnswer()) {
                correctAnswerAction(view);
            } else {
                falseAnswerAction(view);
            }
        }

        private void falseAnswerAction(View view) {
            setLifeStr(multiplicationController.getLifeStr());
            notifyPropertyChanged(BR.lifeStr);
            if (multiplicationController.hasNoMoreLife()) {
                endClicked(view);
            }
        }

        private void correctAnswerAction(View view) {
            setPointsStr(multiplicationController.getPointsStr());
            notifyPropertyChanged(BR.pointsStr);
            //TODO: Falls neues Bage kommt läuft ab hier bereits die Zeit.
            // Das sollte eigentlich erst beim Rücksprung zu der Activity losgehen
            badgeHandling(view);
            setQuestionStr(multiplicationController.getQuestionStr());
            setResultStr("");
        }

        private void badgeHandling(View view) {
            Badge badge = multiplicationController.getNewBadge();
            if (badge != null) {
                Context context = view.getContext();
                Intent intent = new Intent(context, BadgeActivity.class);
                intent.putExtra("fileName", badge.getFile());
                intent.putExtra("fileText", badge.getFileText());
                context.startActivity(intent);
            }
        }

    }
}
