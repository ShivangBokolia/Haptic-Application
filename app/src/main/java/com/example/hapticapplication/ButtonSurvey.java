package com.example.hapticapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ButtonSurvey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_survey);

        Button nextButton = findViewById(R.id.surveyButtonSaveButton);
        AADataRandSettings randSettings = AADataRandSettings.getInstance();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Shuffle the vibration params again for the next activity
                randSettings.shufflePageParams();

                // This will find the SECOND activity
                if (randSettings.getFirstActivity().matches("Button")) {
                    String nextActivity = randSettings.getSecondActivity();
                    if (randSettings.getFirstPage() == 3) {
                        if (nextActivity.matches("Pattern")) {
                            Intent intentPattern = new Intent(ButtonSurvey.this, AAInputPattern.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(ButtonSurvey.this, AAInputGesture.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 4) {
                        if (nextActivity.matches("Pattern")) {
                            Intent intentPattern = new Intent(ButtonSurvey.this, FourPatternCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(ButtonSurvey.this, FourGestureCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 5) {
                        if (nextActivity.matches("Pattern")) {
                            Intent intentPattern = new Intent(ButtonSurvey.this, FivePatternCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(ButtonSurvey.this, FiveGestureCond.class);
                            startActivity(intentGesture);
                        }
                    }
                }
                // This will find the THIRD activity
                else if (randSettings.getSecondActivity().matches("Button")) {
                    String nextActivity = randSettings.getThirdActivity();
                    if (randSettings.getFirstPage() == 3) {
                        if (nextActivity.matches("Pattern")) {
                            Intent intentPattern = new Intent(ButtonSurvey.this, AAInputPattern.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(ButtonSurvey.this, AAInputGesture.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 4) {
                        if (nextActivity.matches("Pattern")) {
                            Intent intentPattern = new Intent(ButtonSurvey.this, FourPatternCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(ButtonSurvey.this, FourGestureCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 5) {
                        if (nextActivity.matches("Pattern")) {
                            Intent intentPattern = new Intent(ButtonSurvey.this, FivePatternCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Gesture")) {
                            Intent intentGesture = new Intent(ButtonSurvey.this, FiveGestureCond.class);
                            startActivity(intentGesture);
                        }
                    }
                }
                // This will display the data of the user
                else if (randSettings.getThirdActivity().matches("Button")) {}
            }
        });
    }
}