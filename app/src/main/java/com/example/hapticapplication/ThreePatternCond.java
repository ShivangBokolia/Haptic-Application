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
import android.widget.TextView;

import java.util.ArrayList;

// TODO: Check if the user created the correct answer
// TODO: Save the answer provided by the user
// TODO: The dot is not being said by the voice (FIX IT)

public class ThreePatternCond extends AppCompatActivity {

    private int shortVibrationTime, longVibrationTime;
    private String answerPattern;
    private TextView textAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_pattern_cond);

        // Creating a vibrator object for the vibrations
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Getting the instance for the patterns and vibration settings
        randSettings randSettings = com.example.hapticapplication.randSettings.getInstance();
        getPattern getPattern = com.example.hapticapplication.getPattern.getInstance();
        vibSettings vibSettings = com.example.hapticapplication.vibSettings.getInstance();

        // Setting the timings for short vibrations and long vibrations
        shortVibrationTime = vibSettings.getData();
        longVibrationTime = vibSettings.getData() * 3;

        // Making the object for the buttons
        Button genButton = findViewById(R.id.threePatternGenButton);
        Button dotButton = findViewById(R.id.threeDotButton);
        Button dashButton = findViewById(R.id.threeDashButton);
        Button nextButton = findViewById(R.id.threePatternNextButton);
        Button resetButton = findViewById(R.id.threePatternResettButton);
        textAnswer = findViewById(R.id.dotDashAnswer);

        // Getting the three vibration pattern
        // The number three is chosen at random:
        answerPattern = getPattern.threePatternOption(3);
        long[] convAnswerPattern = getPattern.convertPattern(answerPattern, shortVibrationTime, longVibrationTime);

        // Creating an array to store the pattern made by the user
        ArrayList<String> userCreatedPattern = new ArrayList<>();

        // Perform actions on pressing the Generate Button
        genButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final VibrationEffect generateVibration;
                generateVibration = VibrationEffect.createWaveform(convAnswerPattern, VibrationEffect.DEFAULT_AMPLITUDE);

                vibrator.cancel();
                vibrator.vibrate(generateVibration);
            }
        });

        // Add the "Dot" to the user created pattern array
        dotButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                userCreatedPattern.add("Dot");
                final VibrationEffect generateVibration;
                generateVibration = VibrationEffect.createOneShot(shortVibrationTime, VibrationEffect.DEFAULT_AMPLITUDE);

                vibrator.cancel();
                vibrator.vibrate(generateVibration);
            }
        });

        // Add the "Dash" to the user created pattern array
        dashButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                userCreatedPattern.add("Dash");
                final VibrationEffect generateVibration;
                generateVibration = VibrationEffect.createOneShot(longVibrationTime, VibrationEffect.DEFAULT_AMPLITUDE);

                vibrator.cancel();
                vibrator.vibrate(generateVibration);
            }
        });

        // Check whether the input provided by the user matches the vibrations generated by the app
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkAnswer = getPattern.convertToDotDash(userCreatedPattern);
                if (checkAnswer.matches(answerPattern)) {
                    textAnswer.setText("Correct Answer");
                } else {
                    textAnswer.setText("Wrong Answer");
                }

                // This will find the SECOND page of the same activity.
                if (randSettings.getFirstPage() == 3) {
                    int nextPageVib = randSettings.getSecondPage();
                    if (nextPageVib == 4) {
                        Intent fourActivityIntent = new Intent(ThreePatternCond.this, FourPatternCond.class);
                        startActivity(fourActivityIntent);
                    } else if (nextPageVib == 5) {
                        Intent fiveActivityIntent = new Intent(ThreePatternCond.this, FivePatternCond.class);
                        startActivity(fiveActivityIntent);
                    }
                }
                // This will find the THIRD page of the same activity.
                else if (randSettings.getSecondPage() == 3) {
                    int nextPageVib = randSettings.getThirdPage();
                    if (nextPageVib == 4) {
                        Intent fourActivityIntent = new Intent(ThreePatternCond.this, FourPatternCond.class);
                        startActivity(fourActivityIntent);
                    } else if (nextPageVib == 5) {
                        Intent fiveActivityIntent = new Intent(ThreePatternCond.this, FivePatternCond.class);
                        startActivity(fiveActivityIntent);
                    }
                }
                // This will change the activity
                else if (randSettings.getThirdPage() == 3) {

                    // Shuffle the vibration params again for the next activity
                    randSettings.shufflePageParams();

                    // This will find the SECOND activity
                    if (randSettings.getFirstActivity().matches("Pattern")) {
                        String nextActivity = randSettings.getSecondActivity();
                        if (randSettings.getFirstPage() == 3) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(ThreePatternCond.this, ButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(ThreePatternCond.this, ThreeGestureCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 4) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(ThreePatternCond.this, FourButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(ThreePatternCond.this, FourGestureCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 5) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(ThreePatternCond.this, FiveButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(ThreePatternCond.this, FiveGestureCond.class);
                                startActivity(intentGesture);
                            }
                        }
                    }
                    // This will find the THIRD activity
                    else if (randSettings.getSecondActivity().matches("Pattern")) {
                        String nextActivity = randSettings.getThirdActivity();
                        if (randSettings.getFirstPage() == 3) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(ThreePatternCond.this, ButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(ThreePatternCond.this, ThreeGestureCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 4) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(ThreePatternCond.this, FourButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(ThreePatternCond.this, FourGestureCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 5) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(ThreePatternCond.this, FiveButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Gesture")) {
                                Intent intentGesture = new Intent(ThreePatternCond.this, FiveGestureCond.class);
                                startActivity(intentGesture);
                            }
                        }
                    }
                    // This will display the data of the user
                    else if (randSettings.getThirdActivity().matches("Pattern")) {}
                }
            }
        });

        // Reset the complete input
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCreatedPattern.clear();
            }
        });
    }


}