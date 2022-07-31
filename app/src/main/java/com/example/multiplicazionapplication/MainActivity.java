package com.example.multiplicazionapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiplicazionapplication.backend.MultiplicationController;

import lombok.SneakyThrows;

public class MainActivity extends AppCompatActivity {

    private MultiplicationController multiplicationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        multiplicationController = new MultiplicationController(this);
        multiplicationController.setQuestion();
        handleButtonEvents();
    }

    public String getResult() {
        return ((EditText) findViewById(R.id.result)).getText().toString();
    }

    public String getName() {
        return ((EditText) findViewById(R.id.name)).getText().toString();
    }

    public void setQuestion(String question) {
        ((TextView) findViewById(R.id.question)).setText(question);
    }

    private void handleButtonEvents() {
        findViewById(R.id.ok_button).setOnClickListener(v -> okClicked());
        findViewById(R.id.end_button).setOnClickListener(this::endClicked);
    }

    @SneakyThrows
    private void endClicked(View view) {
        String resultStr = multiplicationController.endClicked();
        showPopupWindow(view, resultStr);
        Intent intent = new Intent(this, HighscoreActivity.class);
        intent.putExtra()
        startActivity(intent);
    }

    private void showPopupWindow(View view, String resultStr) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View popupView = inflater.inflate(R.layout.popup, null);

        //create Window and pass content
        TextView popupText = findViewById(R.id.popup_text);
        popupText.setText(resultStr);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener((v, event) -> {
            popupWindow.dismiss();
            v.performClick();   //stört das???
            return true;
        });
    }

    private void okClicked() {
        multiplicationController.okClicked();
    }
}