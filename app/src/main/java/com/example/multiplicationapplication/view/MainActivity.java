package com.example.multiplicationapplication.view;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.multiplicationapplication.R;
import com.example.multiplicationapplication.databinding.ActivityMainBinding;
import com.example.multiplicationapplication.viewmodel.AppViewModel;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getReadAndWritePermission();
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AppViewModel appViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        activityMainBinding.setObserver(appViewModel.getObserver());

        ((EditText) findViewById(R.id.result)).setOnEditorActionListener(
                appViewModel.getObserver()::afterEditingResult);
    }

    private void getReadAndWritePermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1) {
            System.out.println("Hello");
        }
    }
}