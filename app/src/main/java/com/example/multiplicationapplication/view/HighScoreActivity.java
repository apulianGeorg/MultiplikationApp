package com.example.multiplicationapplication.view;

import android.os.Bundle;
import android.widget.ListView;

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
    }

    private void bindAdapterToListView() throws JsonProcessingException {
        HighScoreFileManager highScoreFileManager = new HighScoreFileManager();
        ListView listView = findViewById(R.id.listview_activity_main);
        List<PlayerPoints> playerPointsList = highScoreFileManager.getPlayerPointsList();

        RowAdapterHighScore adapter =
                new RowAdapterHighScore(this, R.layout.list_row_highscore, playerPointsList);

        listView.setAdapter(adapter);
    }
}
