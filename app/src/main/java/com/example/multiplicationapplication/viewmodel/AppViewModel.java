package com.example.multiplicationapplication.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.example.multiplicationapplication.R;
import com.example.multiplicationapplication.model.Badge;
import com.example.multiplicationapplication.model.Model;
import com.example.multiplicationapplication.model.MultiplicationController;
import com.example.multiplicationapplication.view.BadgeActivity;
import com.example.multiplicationapplication.view.HighScoreActivity;
import com.example.multiplicationapplication.view.MainActivity;

import lombok.SneakyThrows;

public class AppViewModel extends BaseObservable {
//https://medium.com/halcyon-mobile/android-architecture-components-databinding-dependent-properties-6e8eba6c8b13
    public static final String POINTS_PREFIX = "Punkte: ";
    public static final String LIFE_PREFIX = "Leben: ";
    private final Model model;
    private final MultiplicationController multiplicationController;

    // constructor of ViewModel class
    public AppViewModel(MainActivity mainActivity) {
        multiplicationController = new MultiplicationController();
        model = new Model();
        model.setPlayerName("Valentin");
        model.setQuestionStr(multiplicationController.getQuestionStr());
        setPointsStr("0");
        setLifeStr("5");
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
        model.setPointsStr(POINTS_PREFIX + pointsStr);
    }

    @Bindable
    public String getLifeStr() {
        return model.getLifeStr();
    }

    public void setLifeStr(String lifeStr) {
        model.setLifeStr(LIFE_PREFIX + lifeStr);
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
            context.startActivity(intent );
        }
    }
}
