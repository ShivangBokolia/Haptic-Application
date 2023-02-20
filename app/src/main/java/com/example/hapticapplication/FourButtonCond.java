package com.example.hapticapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// TODO: Check if the user pressed the correct answer
// TODO: Save the answer provided by the user
// TODO: The dot is not being said by the voice (FIX IT)

public class FourButtonCond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_button_cond);

        // Creating a vibrator object for the vibrations
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Storing the answer selected by the user
        List<String> selectedAnswer = new ArrayList<>();

        // Getting the instance for the patterns and vibration settings
        randSettings randSettings = com.example.hapticapplication.randSettings.getInstance();
        getPattern getPattern = com.example.hapticapplication.getPattern.getInstance();
        vibSettings vibSettings = com.example.hapticapplication.vibSettings.getInstance();

        // Setting the timings for short vibrations and long vibrations
        int shortVibrationTime = vibSettings.getData();
        int longVibrationTime = vibSettings.getData() * 3;

        // Making the object for the buttons
        Button generateButton = findViewById(R.id.fourGenButton);
        Button option1Button = findViewById(R.id.fourOpt1Button);
        Button option2Button = findViewById(R.id.fourOpt2Button);
        Button option3Button = findViewById(R.id.fourOpt3Button);
        Button nextButton = findViewById(R.id.fourButtonNextButton);

        // Generating the answer pattern of length 4 for generate button and option 3.
        // Generating random patterns for option 1 and option 2
        String answerPattern = getPattern.fourPatternOption(1);
        String option1Pattern = getPattern.fourPatternOption(3);
        String option2Pattern = getPattern.fourPatternOption(5);

        // Setting the texts for the buttons
        option1Button.setText(option1Pattern);
        option2Button.setText(option2Pattern);
        option3Button.setText(answerPattern);

        // Converting pattern from "._." to "{0, 300, 700, 1000, 700, 300}"
        // The vibrator object requires it in the following format.
        long[] convPattern = getPattern.convertPattern(answerPattern, shortVibrationTime, longVibrationTime);

        // Playing the answer vibration on pressing the generate button
        generateButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final VibrationEffect generateVibration;
                generateVibration = VibrationEffect.createWaveform(convPattern, VibrationEffect.DEFAULT_AMPLITUDE);

                vibrator.cancel();
                vibrator.vibrate(generateVibration);
            }
        });

        // Playing the option 1 vibration and moving on to the next activity
        option1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer.add(option1Pattern);
            }
        });

        // Playing the option 2 vibration and moving on to the next activity
        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer.add(option2Pattern);
            }
        });

        // Playing the option 3 (answer) vibration and moving on to the next activity
        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer.add(answerPattern);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will find the SECOND page for the activities.
                if (randSettings.getFirstPage() == 4) {
                    int nextPageVib = randSettings.getSecondPage();
                    if (nextPageVib == 3) {
                        Intent fourActivityIntent = new Intent(FourButtonCond.this, ButtonCond.class);
                        startActivity(fourActivityIntent);
                    } else if (nextPageVib == 5) {
                        Intent fiveActivityIntent = new Intent(FourButtonCond.this, FiveButtonCond.class);
                        startActivity(fiveActivityIntent);
                    }
                }
                // This will find the THIRD page for the activities.
                else if (randSettings.getSecondPage() == 4) {
                    int nextPageVib = randSettings.getThirdPage();
                    if (nextPageVib == 3) {
                        Intent fourActivityIntent = new Intent(FourButtonCond.this, ButtonCond.class);
                        startActivity(fourActivityIntent);
                    } else if (nextPageVib == 5) {
                        Intent fiveActivityIntent = new Intent(FourButtonCond.this, FiveButtonCond.class);
                        startActivity(fiveActivityIntent);
                    }
                }
                // This will find the next different activity and move to that.
                else if (randSettings.getThirdPage() == 4) {

                    // Shuffle the vibration params again for the next activity
                    randSettings.shufflePageParams();

                    // This will find the SECOND activity
                    if (randSettings.getFirstActivity().matches("Button")) {
                        String nextActivity = randSettings.getSecondActivity();
                        if (randSettings.getFirstPage() == 3) {
                            if (nextActivity.matches("Pattern")) {
                                Intent intentPattern = new Intent(FourButtonCond.this, ThreePatternCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(FourButtonCond.this, ThreeGestureCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 4) {
                            if (nextActivity.matches("Pattern")) {
                                Intent intentPattern = new Intent(FourButtonCond.this, FourPatternCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(FourButtonCond.this, FourGestureCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 5) {
                            if (nextActivity.matches("Pattern")) {
                                Intent intentPattern = new Intent(FourButtonCond.this, FivePatternCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(FourButtonCond.this, FiveGestureCond.class);
                                startActivity(intentGesture);
                            }
                        }
                    }
                    // This will find the THIRD activity
                    else if (randSettings.getSecondActivity().matches("Button")) {
                        String nextActivity = randSettings.getThirdActivity();
                        if (randSettings.getFirstPage() == 3) {
                            if (nextActivity.matches("Pattern")) {
                                Intent intentPattern = new Intent(FourButtonCond.this, ThreePatternCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(FourButtonCond.this, ThreeGestureCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 4) {
                            if (nextActivity.matches("Pattern")) {
                                Intent intentPattern = new Intent(FourButtonCond.this, FourPatternCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(FourButtonCond.this, FourGestureCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 5) {
                            if (nextActivity.matches("Pattern")) {
                                Intent intentPattern = new Intent(FourButtonCond.this, FivePatternCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(FourButtonCond.this, FiveGestureCond.class);
                                startActivity(intentGesture);
                            }
                        }
                    }
                    // This will display the data of the user
                    else if (randSettings.getThirdActivity().matches("Button")) {}
                }
            }
        });
    }
}