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

public class TutorialPattern extends AppCompatActivity {

    private int shortVibrationTime, longVibrationTime;
    private String answerPattern;
    private TextView textAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_pattern);

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
        Button genButton = findViewById(R.id.patternTutGenButton);
        Button dotButton = findViewById(R.id.dotTutButton);
        Button dashButton = findViewById(R.id.dashTutButton);
        Button nextButton = findViewById(R.id.patternTutNextButton);
        Button resetButton = findViewById(R.id.threePatternResettButton);
        textAnswer = findViewById(R.id.dotDashTutAnswer);

        // Getting the three vibration pattern
        // The number 1 is chosen at random:
        answerPattern = getPattern.threePatternOption(5);

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

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic for different activities transitioning
                String checkAnswer = getPattern.convertToDotDash(userCreatedPattern);
                if (checkAnswer.matches(answerPattern)) {
                    textAnswer.setText("Correct Answer");
                } else {
                    textAnswer.setText("Wrong Answer");
                }

                Intent intent = new Intent(TutorialPattern.this, TutorialGesture.class);
                startActivity(intent);
            }
        });
    }
}