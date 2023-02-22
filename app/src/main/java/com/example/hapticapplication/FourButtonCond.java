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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

// TODO: Check if the user pressed the correct answer
// TODO: Save the answer provided by the user

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

        StringBuilder answerPattern = new StringBuilder();
        StringBuilder option1Pattern = new StringBuilder();
        StringBuilder option2Pattern = new StringBuilder();

        int patternCondition=HapticCommon.patternConditionArray[HapticCommon.patternConditionCount];
        if (patternCondition==3) {
            Log.e("ButtonTest", String.valueOf(patternCondition));
            if (getPattern.getCounter() == 1) {
                Log.e("3", "1");
                // Generating the answer pattern of length 4 for generate button and option 3.
                // Generating random patterns for option 1 and option 2
                answerPattern.append(getPattern.threePatternOption(0));
                option1Pattern.append(getPattern.threePatternOption(1));
                option2Pattern.append(getPattern.threePatternOption(2));
            } else if (getPattern.getCounter() == 2) {
                Log.e("3", "2");
                // Generating the answer pattern of length 4 for generate button and option 3.
                // Generating random patterns for option 1 and option 2
                answerPattern.append(getPattern.threePatternOption(3));
                option1Pattern.append(getPattern.threePatternOption(4));
                option2Pattern.append(getPattern.threePatternOption(5));
            } else if (getPattern.getCounter() == 3) {
                Log.e("3", "3");
                // Generating the answer pattern of length 4 for generate button and option 3.
                // Generating random patterns for option 1 and option 2
                answerPattern.append(getPattern.threePatternOption(6));
                option1Pattern.append(getPattern.threePatternOption(7));
                option2Pattern.append(getPattern.threePatternOption(5));
            }
        }

        if (patternCondition==4) {
            Log.e("ButtonTest", String.valueOf(patternCondition));
            if (getPattern.getCounter() == 1) {
                Log.e("4", "1");
                // Generating the answer pattern of length 4 for generate button and option 3.
                // Generating random patterns for option 1 and option 2
                answerPattern.append(getPattern.fourPatternOption(0));
                option1Pattern.append(getPattern.fourPatternOption(1));
                option2Pattern.append(getPattern.fourPatternOption(2));
            } else if (getPattern.getCounter() == 2) {
                Log.e("4", "2");
                // Generating the answer pattern of length 4 for generate button and option 3.
                // Generating random patterns for option 1 and option 2
                answerPattern.append(getPattern.fourPatternOption(3));
                option1Pattern.append(getPattern.fourPatternOption(4));
                option2Pattern.append(getPattern.fourPatternOption(5));
            } else if (getPattern.getCounter() == 3) {
                Log.e("4", "3");
                // Generating the answer pattern of length 4 for generate button and option 3.
                // Generating random patterns for option 1 and option 2
                answerPattern.append(getPattern.fourPatternOption(6));
                option1Pattern.append(getPattern.fourPatternOption(7));
                option2Pattern.append(getPattern.fourPatternOption(5));
            }
        }
        if (patternCondition==5) {
            Log.e("ButtonTest", String.valueOf(patternCondition));
            if (getPattern.getCounter() == 1) {
                Log.e("5", "1");
                // Generating the answer pattern of length 5 for generate button and option 3.
                // Generating random patterns for option 1 and option 2
                answerPattern.append(getPattern.fivePatternOption(0));
                option1Pattern.append(getPattern.fivePatternOption(1));
                option2Pattern.append(getPattern.fivePatternOption(2));
            } else if (getPattern.getCounter() == 2) {
                Log.e("5", "2");
                // Generating the answer pattern of length 5 for generate button and option 3.
                // Generating random patterns for option 1 and option 2
                answerPattern.append(getPattern.fivePatternOption(3));
                option1Pattern.append(getPattern.fivePatternOption(4));
                option2Pattern.append(getPattern.fivePatternOption(5));
            } else if (getPattern.getCounter() == 3) {
                Log.e("5", "3");
                // Generating the answer pattern of length 5 for generate button and option 3.
                // Generating random patterns for option 1 and option 2
                answerPattern.append(getPattern.fivePatternOption(6));
                option1Pattern.append(getPattern.fivePatternOption(7));
                option2Pattern.append(getPattern.fivePatternOption(5));
            }
        }

            // Setting the texts for the buttons
        option1Button.setText(getPattern.convertPatternToText(option1Pattern.toString()));
        option2Button.setText(getPattern.convertPatternToText(option2Pattern.toString()));
        option3Button.setText(getPattern.convertPatternToText(answerPattern.toString()));

        // Converting pattern from "._." to "{0, 300, 700, 1000, 700, 300}"
        // The vibrator object requires it in the following format.
        long[] convPattern = getPattern.convertPattern(answerPattern.toString(), shortVibrationTime, longVibrationTime);

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
                selectedAnswer.add(option1Pattern.toString());
            }
        });

        // Playing the option 2 vibration and moving on to the next activity
        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer.add(option2Pattern.toString());
            }
        });

        // Playing the option 3 (answer) vibration and moving on to the next activity
        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedAnswer.add(answerPattern.toString());
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Repeat the same activity
                if ((getPattern.getCounter() == 1 || getPattern.getCounter() == 2) && !getPattern.isFourButton()) {
                    getPattern.incrementCounter();
                    Intent sameActivity = new Intent(FourButtonCond.this, FourButtonCond.class);
                    startActivity(sameActivity);
                }
                // Move to the next activity
                else if (getPattern.getCounter() == 3 && !getPattern.isFourButton()) {

                    getPattern.resetCounter();
                    HapticCommon.patternConditionCount++;
                    if (HapticCommon.patternConditionCount>2){
                        Intent surveyIntent = new Intent(FourButtonCond.this, GestureSurvey.class);
                        startActivity(surveyIntent);
                    }else{
                        Intent intent = new Intent(FourButtonCond.this, FourButtonCond.class);
                        startActivity(intent);
                    }

                    /*getPattern.resetCounter();
                    getPattern.setFourButton(true);

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
                        Intent surveyIntent = new Intent(FourButtonCond.this, ButtonSurvey.class);
                        startActivity(surveyIntent);
                    }*/
                }
            }
        });
    }
}