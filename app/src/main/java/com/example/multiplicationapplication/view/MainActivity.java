package com.example.multiplicationapplication.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import com.example.multiplicationapplication.R;
import com.example.multiplicationapplication.databinding.ActivityMainBinding;
import com.example.multiplicationapplication.viewmodel.AppViewModel;


public class MainActivity extends AppCompatActivity {

    private final AppViewModel appViewModel = new AppViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getReadAndWritePermission();
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setAppViewModel(appViewModel);
        activityMainBinding.executePendingBindings();

        findViewById(R.id.end_button).setOnClickListener(this::endClicked);
    }

    private void getReadAndWritePermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
    }

    private void endClicked(View view) {
        String results = appViewModel.getResults();
        Intent intent = new Intent(this, HighScoreActivity.class);
        intent.putExtra("results", results);
        startActivity(intent);
    }

}