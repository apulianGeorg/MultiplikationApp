package com.example.multiplicationapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.multiplicationapplication.R;

public class BadgeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);
        int fileName = this.getIntent().getIntExtra("fileName", 0);
        String fileText = this.getIntent().getStringExtra("fileText");
        ImageView badgeImage = findViewById(R.id.imageView);
        badgeImage.setImageResource(fileName);
        TextView badgeText = findViewById(R.id.textView);
        badgeText.setText(fileText);
    }

    @Override
    public void onBackPressed(){
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }
}
