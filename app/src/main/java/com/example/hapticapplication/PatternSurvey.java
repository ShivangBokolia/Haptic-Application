package com.example.hapticapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatternSurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pattern_survey);

        Button nextButton = findViewById(R.id.surveyPatternSaveButton);
        randSettings randSettings = com.example.hapticapplication.randSettings.getInstance();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Shuffle the vibration params again for the next activity
                randSettings.shufflePageParams();

                // This will find the SECOND activity
                if (randSettings.getFirstActivity().matches("Pattern")) {
                    String nextActivity = randSettings.getSecondActivity();
                    if (randSettings.getFirstPage() == 3) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(PatternSurvey.this, ButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(PatternSurvey.this, ThreeGestureCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 4) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(PatternSurvey.this, FourButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(PatternSurvey.this, FourGestureCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 5) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(PatternSurvey.this, FiveButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(PatternSurvey.this, FiveGestureCond.class);
                            startActivity(intentGesture);
                        }
                    }
                }
                // This will find the THIRD activity
                else if (randSettings.getSecondActivity().matches("Pattern")) {
                    String nextActivity = randSettings.getThirdActivity();
                    if (randSettings.getFirstPage() == 3) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(PatternSurvey.this, ButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(PatternSurvey.this, ThreeGestureCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 4) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(PatternSurvey.this, FourButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(PatternSurvey.this, FourGestureCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 5) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(PatternSurvey.this, FiveButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(PatternSurvey.this, FiveGestureCond.class);
                            startActivity(intentGesture);
                        }
                    }
                }
                // This will display the data of the user
                else if (randSettings.getThirdActivity().matches("Pattern")) {}

            }
        });
    }
}