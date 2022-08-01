package com.example.multiplicationapplication.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiplicationapplication.R;
import com.example.multiplicationapplication.model.HighScoreFileManager;
import com.example.multiplicationapplication.model.PlayerPoints;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        try {
            bindAdapterToListView();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        String results = getIntent().getStringExtra("results");
        TextView resultTextView = findViewById(R.id.result);
        resultTextView.setText(results);
        //showPopupWindow(findViewById(R.id.listview_activity_main), results);
    }

    private void bindAdapterToListView() throws JsonProcessingException {
        HighScoreFileManager highScoreFileManager = new HighScoreFileManager();
        ListView listView = findViewById(R.id.listview_activity_main);
        List<PlayerPoints> playerPointsList = highScoreFileManager.getPlayerPointsList();

        RowAdapterHighScore adapter =
                new RowAdapterHighScore(this, R.layout.list_row_highscore, playerPointsList);

        listView.setAdapter(adapter);
    }

    private void showPopupWindow(View view, String results) {
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View popupView = inflater.inflate(R.layout.popup, null);
        TextView popupText = popupView.findViewById(R.id.popup_text);
        popupText.setText(results);

        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        //create Window and pass content

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // dismiss the popup window when touched
        popupView.setOnTouchListener((v, event) -> {
            v.performClick();
            popupWindow.dismiss();
            return true;
        });
    }
}
