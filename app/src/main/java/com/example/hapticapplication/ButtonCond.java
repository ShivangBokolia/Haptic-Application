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

import java.util.ArrayList;
import java.util.List;

// TODO: Check if the user pressed the correct answer
// TODO: Save the answer provided by the user

public class ButtonCond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_cond);

        // Creating a vibrator object for the vibrations
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Storing the answer selected by the user
        List<String> selectedAnswer = new ArrayList<>();

        // Getting the instance for the patterns and vibration settings
        AADataRandSettings randSettings = AADataRandSettings.getInstance();
        AADataGetPattern getPattern = AADataGetPattern.getInstance();
        vibSettings vibSettings = com.example.hapticapplication.vibSettings.getInstance();

        // Setting the timings for short vibrations and long vibrations
        int shortVibrationTime = vibSettings.getData();
        int longVibrationTime = vibSettings.getData() * 3;

        // Making the object for the buttons
        Button generateButton = findViewById(R.id.threeGenButton);
        Button option1Button = findViewById(R.id.threeOpt1Button);
        Button option2Button = findViewById(R.id.threeOpt2Button);
        Button option3Button = findViewById(R.id.threeOpt3Button);
        Button nextButton = findViewById(R.id.threeButtonNextButton);

        StringBuilder answerPattern = new StringBuilder();
        StringBuilder option1Pattern = new StringBuilder();
        StringBuilder option2Pattern = new StringBuilder();

        if (getPattern.getCounter() == 1) {
            // Generating the answer pattern of length 3 for generate button and option 3.
            // Generating random patterns for option 1 and option 2
            answerPattern.append(getPattern.threePatternOption(1));
            option1Pattern.append(getPattern.threePatternOption(2));
            option2Pattern.append(getPattern.threePatternOption(3));
        } else if (getPattern.getCounter() == 2) {
            // Generating the answer pattern of length 3 for generate button and option 3.
            // Generating random patterns for option 1 and option 2
            answerPattern.append(getPattern.threePatternOption(4));
            option1Pattern.append(getPattern.threePatternOption(5));
            option2Pattern.append(getPattern.threePatternOption(6));
        } else if (getPattern.getCounter() == 3) {
            // Generating the answer pattern of length 3 for generate button and option 3.
            // Generating random patterns for option 1 and option 2
            answerPattern.append(getPattern.threePatternOption(7));
            option1Pattern.append(getPattern.threePatternOption(0));
            option2Pattern.append(getPattern.threePatternOption(1));
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
                if ((getPattern.getCounter() == 1 || getPattern.getCounter() == 2) && !getPattern.isThreeButton()) {
                    getPattern.incrementCounter();
                    Intent sameActivity = new Intent(ButtonCond.this, ButtonCond.class);
                    startActivity(sameActivity);
                }
                // Move to the next activity
                else if (getPattern.getCounter() == 3 && !getPattern.isThreeButton()) {
                    getPattern.resetCounter();
                    getPattern.setThreeButton(true);

                    // This will find the SECOND page for the activities.
                    if (randSettings.getFirstPage() == 3) {
                        int nextPageVib = randSettings.getSecondPage();
                        if (nextPageVib == 4) {
                            Intent fourActivityIntent = new Intent(ButtonCond.this, AAInputButton.class);
                            startActivity(fourActivityIntent);
                        } else if (nextPageVib == 5) {
                            Intent fiveActivityIntent = new Intent(ButtonCond.this, FiveButtonCond.class);
                            startActivity(fiveActivityIntent);
                        }
                    }
                    // This will find the THIRD page for the activities.
                    else if (randSettings.getSecondPage() == 3) {
                        int nextPageVib = randSettings.getThirdPage();
                        if (nextPageVib == 4) {
                            Intent fourActivityIntent = new Intent(ButtonCond.this, AAInputButton.class);
                            startActivity(fourActivityIntent);
                        } else if (nextPageVib == 5) {
                            Intent fiveActivityIntent = new Intent(ButtonCond.this, FiveButtonCond.class);
                            startActivity(fiveActivityIntent);
                        }
                    }
                    // This will find the next different activity and move to that.
                    else if (randSettings.getThirdPage() == 3) {
                        Intent surveyIntent = new Intent(ButtonCond.this, ButtonSurvey.class);
                        startActivity(surveyIntent);
                    }
                }
            }
        });
    }
}