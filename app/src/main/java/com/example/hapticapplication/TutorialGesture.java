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

public class TutorialGesture extends AppCompatActivity {

    private int shortVibrationTime, longVibrationTime;
    private String answerPattern;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial_gesture);

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
        Button genButton = findViewById(R.id.gestureTutGenButton);
        Button inputButton = findViewById(R.id.inputTutButton);
        Button nextButton = findViewById(R.id.gestureTutNextButton);
        Button resetButton = findViewById(R.id.gestureTutResetButton);
        TextView gestureText = findViewById(R.id.gestureTutText);

        // Getting the three vibration pattern
        // The number three is chosen at random:
        answerPattern = getPattern.threePatternOption(4);

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

                Intent intent = new Intent(TutorialGesture.this, Participation.class);
                startActivity(intent);
            }
        });
    }
}