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
import java.util.List;

public class TutorialButton extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_button);

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
        Button generateButton = findViewById(R.id.buttonTuTGenButton);
        Button option1Button = findViewById(R.id.opt1TutButton);
        Button option2Button = findViewById(R.id.opt2TutButton);
        Button option3Button = findViewById(R.id.opt3TutButton);
        Button nextButton = findViewById(R.id.buttonTutNextButton);
        TextView topText = findViewById(R.id.buttonTutText);

        // Generating the answer pattern of length 3 for generate button and option 3.
        // Generating random patterns for option 1 and option 2
        String answerPattern = getPattern.threePatternOption(1);
        String option1Pattern = getPattern.threePatternOption(2);
        String option2Pattern = getPattern.threePatternOption(3);

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
                topText.setText("Wrong Answer");
            }
        });

        // Playing the option 2 vibration and moving on to the next activity
        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topText.setText("Wrong Answer");
            }
        });

        // Playing the option 3 (answer) vibration and moving on to the next activity
        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topText.setText("Correct Answer");
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TutorialButton.this, TutorialPattern.class);
                startActivity(intent);
            }
        });
    }
}