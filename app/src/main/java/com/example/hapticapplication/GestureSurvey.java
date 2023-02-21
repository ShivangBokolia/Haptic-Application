package com.example.hapticapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GestureSurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_survey);

        Button nextButton = findViewById(R.id.surveyGestureSaveButton);
        randSettings randSettings = com.example.hapticapplication.randSettings.getInstance();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Shuffle the vibration params again for the next activity
                randSettings.shufflePageParams();

                // This will find the SECOND activity
                if (randSettings.getFirstActivity().matches("Gesture")) {

                    String nextActivity = randSettings.getSecondActivity();
                    if (randSettings.getFirstPage() == 3) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, ButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, ThreePatternCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 4) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, FourButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, FourPatternCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 5) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, FiveButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, FivePatternCond.class);
                            startActivity(intentGesture);
                        }
                    }
                }
                // This will find the THIRD activity
                else if (randSettings.getSecondActivity().matches("Gesture")) {
                    String nextActivity = randSettings.getThirdActivity();
                    if (randSettings.getFirstPage() == 3) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, ButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, ThreePatternCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 4) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, FourButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, FourPatternCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 5) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, FiveButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, FivePatternCond.class);
                            startActivity(intentGesture);
                        }
                    }
                }
                // This will display the data of the user
                else if (randSettings.getThirdActivity().matches("Gesture")) {}
            }
        });
    }
}