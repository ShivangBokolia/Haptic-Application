package com.example.hapticapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

// TODO: Check if the user created the correct answer
// TODO: Save the answer provided by the user
// TODO: The dot is not being said by the voice (FIX IT)

public class FourGestureCond extends AppCompatActivity {

    private int shortVibrationTime, longVibrationTime;
    private String answerPattern;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_gesture_cond);

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
        Button genButton = findViewById(R.id.fourGestureGenButton);
        Button inputButton = findViewById(R.id.fourGestureInputButton);
        Button nextButton = findViewById(R.id.fourGestureNextButton);
        Button resetButton = findViewById(R.id.fourGestureResetButton);
        TextView gestureText = findViewById(R.id.fourGestureText);

        // Getting the three vibration pattern
        // The number three is chosen at random:
        answerPattern = getPattern.fourPatternOption(6);
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

        inputButton.setOnTouchListener(new View.OnTouchListener() {
            long startTime = 0L;
            long endTime = 0L;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startTime = SystemClock.elapsedRealtime();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    endTime = SystemClock.elapsedRealtime();
                }
                float totalTime = ((float) endTime - (float) startTime)/1000;
                if (totalTime > 0) {
                    if (totalTime > 0.8) {
                        userCreatedPattern.add("Dash");
                    } else {
                        userCreatedPattern.add("Dot");
                    }
                }
                return true;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answerPattern.matches(getPattern.convertToDotDash(userCreatedPattern))) {
                    gestureText.setText("Correct Answer");
                } else {
                    gestureText.setText("Wrong Answer");
                }

                if (randSettings.getFirstPage() == 4) {
                    int nextPageVib = randSettings.getSecondPage();
                    if (nextPageVib == 3) {
                        Intent threeActivityIntent = new Intent(FourGestureCond.this, ThreeGestureCond.class);
                        startActivity(threeActivityIntent);
                    } else if (nextPageVib == 5) {
                        Intent fiveActivityIntent = new Intent(FourGestureCond.this, FiveGestureCond.class);
                        startActivity(fiveActivityIntent);
                    }
                } else if (randSettings.getSecondPage() == 4) {
                    int nextPageVib = randSettings.getThirdPage();
                    if (nextPageVib == 3) {
                        Intent threeActivityIntent = new Intent(FourGestureCond.this, ThreeGestureCond.class);
                        startActivity(threeActivityIntent);
                    } else if (nextPageVib == 5) {
                        Intent fiveActivityIntent = new Intent(FourGestureCond.this, FiveGestureCond.class);
                        startActivity(fiveActivityIntent);
                    }
                } else if (randSettings.getThirdPage() == 4) {

                    // Shuffle the vibration params again for the next activity
                    randSettings.shufflePageParams();

                    // This will find the SECOND activity
                    if (randSettings.getFirstActivity().matches("Gesture")) {
                        String nextActivity = randSettings.getSecondActivity();
                        if (randSettings.getFirstPage() == 3) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(FourGestureCond.this, ButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Pattern")) {
                                Intent intentGesture = new Intent(FourGestureCond.this, ThreePatternCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 4) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(FourGestureCond.this, FourButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Pattern")) {
                                Intent intentGesture = new Intent(FourGestureCond.this, FourPatternCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 5) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(FourGestureCond.this, FiveButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Pattern")) {
                                Intent intentGesture = new Intent(FourGestureCond.this, FivePatternCond.class);
                                startActivity(intentGesture);
                            }
                        }
                    }
                    // This will find the THIRD activity
                    else if (randSettings.getSecondActivity().matches("Gesture")) {
                        String nextActivity = randSettings.getThirdActivity();
                        if (randSettings.getFirstPage() == 3) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(FourGestureCond.this, ButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Pattern")) {
                                Intent intentGesture = new Intent(FourGestureCond.this, ThreePatternCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 4) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(FourGestureCond.this, FourButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Pattern")) {
                                Intent intentGesture = new Intent(FourGestureCond.this, FourPatternCond.class);
                                startActivity(intentGesture);
                            }
                        } else if (randSettings.getFirstPage() == 5) {
                            if (nextActivity.matches("Button")) {
                                Intent intentPattern = new Intent(FourGestureCond.this, FiveButtonCond.class);
                                startActivity(intentPattern);
                            } else if (nextActivity.matches("Pattern")) {
                                Intent intentGesture = new Intent(FourGestureCond.this, FivePatternCond.class);
                                startActivity(intentGesture);
                            }
                        }
                    }
                    // This will display the data of the user
                    else if (randSettings.getThirdActivity().matches("Gesture")) {}
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCreatedPattern.clear();
            }
        });
    }
}