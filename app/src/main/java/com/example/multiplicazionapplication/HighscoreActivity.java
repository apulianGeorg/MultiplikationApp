package com.example.multiplicazionapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiplicazionapplication.backend.MyFileReader;
import com.example.multiplicazionapplication.backend.PlayerPoints;

import java.util.List;

public class HighscoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindAdapterToListView();
    }

    private void bindAdapterToListView() {
        ListView listView = findViewById(R.id.high_score_name);

        List<PlayerPoints> playerPointsList =
                MyFileReader.

        RowAdapterHighscore adapter = new RowAdapterHighscore(this, tipperListe);
        listView.setAdapter(adapter);
    }
}
