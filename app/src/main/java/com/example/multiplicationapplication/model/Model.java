package com.example.multiplicationapplication.model;

import android.graphics.drawable.GradientDrawable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Model {
    private String playerName;
    private String questionStr;
    private String resultStr;
    private String pointsStr;
    private String lifeStr;
}
