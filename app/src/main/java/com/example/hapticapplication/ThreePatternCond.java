package com.example.hapticapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Pattern;

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
        Log.e("view","threePattern");

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

        int patternCondition=HapticCommon.patternConditionArray[HapticCommon.patternConditionCount];
        if (patternCondition==3) {
            Log.e("PatternTest", String.valueOf(patternCondition));
            if (getPattern.getCounter() == 1) {
                // Getting the three vibration pattern
                // The number 1 is chosen at random:
                answerPattern = getPattern.threePatternOption(1);
            } else if (getPattern.getCounter() == 2) {
                // Getting the three vibration pattern
                // The number 2 is chosen at random:
                answerPattern = getPattern.threePatternOption(2);
            } else if (getPattern.getCounter() == 3) {
                // Getting the three vibration pattern
                // The number 3 is chosen at random:
                answerPattern = getPattern.threePatternOption(3);
            }
        }
        if (patternCondition==4) {
            Log.e("PatternTest", String.valueOf(patternCondition));
            if ( getPattern.getCounter() == 1) {
                // Getting the four vibration pattern
                // The number 1 is chosen at random:
                answerPattern = getPattern.fourPatternOption(1);
            } else if (getPattern.getCounter() == 2) {
                // Getting the four vibration pattern
                // The number 2 is chosen at random:
                answerPattern = getPattern.fourPatternOption(2);
            } else if (getPattern.getCounter() == 3) {
                // Getting the four vibration pattern
                // The number 3 is chosen at random:
                answerPattern = getPattern.fourPatternOption(3);
            }
        }
        if (patternCondition==5) {
            Log.e("PatternTest", String.valueOf(patternCondition));
            if ( getPattern.getCounter() == 1) {
                // Getting the five vibration pattern
                // The number 1 is chosen at random:
                answerPattern = getPattern.fivePatternOption(1);
            } else if (getPattern.getCounter() == 2) {
                // Getting the five vibration pattern
                // The number 2 is chosen at random:
                answerPattern = getPattern.fivePatternOption(2);
            } else if (getPattern.getCounter() == 3) {
                // Getting the five vibration pattern
                // The number 3 is chosen at random:
                answerPattern = getPattern.fivePatternOption(3);
            }
        }

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

                // Same activity to be repeated again
                if ((getPattern.getCounter() == 1 || getPattern.getCounter() == 2) && !getPattern.isThreePattern()) {
                    getPattern.incrementCounter();
                    Intent sameActivity = new Intent(ThreePatternCond.this, ThreePatternCond.class);
                    startActivity(sameActivity);
                }
                // Move on to a different activity
                else if (getPattern.getCounter() == 3 && !getPattern.isThreePattern()) {
                    getPattern.resetCounter();

                    HapticCommon.patternConditionCount++;
                    if (HapticCommon.patternConditionCount>2){
                        Intent surveyIntent = new Intent(ThreePatternCond.this, GestureSurvey.class);
                        startActivity(surveyIntent);
                    }else{
                        Intent intent = new Intent(ThreePatternCond.this, ThreePatternCond.class);
                        startActivity(intent);
                    }


                    /*getPattern.setThreePattern(true);

                    // Logic for different activities transitioning
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
                        Intent surveyIntent = new Intent(ThreePatternCond.this, PatternSurvey.class);
                        startActivity(surveyIntent);
                    }*/
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