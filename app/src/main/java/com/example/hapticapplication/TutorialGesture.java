package com.example.hapticapplication;

import static java.lang.String.valueOf;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class TutorialGesture extends AppCompatActivity {

    private int shortVibrationTime, longVibrationTime;
    private String answerPattern;
    long startTime=0;
    int generatePresses=0;
    int patternCondition=0;
    AADataGetPattern getPattern = AADataGetPattern.getInstance();
    AlertDialog.Builder builder;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_gesture);

        // Creating a vibrator object for the vibrations
        // Creating a vibrator object for the vibrations
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Log.e("view","threeGesture");
        // Getting the instance for the patterns and vibration settings
        vibSettings vibSettings = com.example.hapticapplication.vibSettings.getInstance();

        // Setting the timings for short vibrations and long vibrations
        shortVibrationTime = vibSettings.getData();
        longVibrationTime = vibSettings.getData() * 3;

        // Making the object for the buttons
        Button genButton = findViewById(R.id.threeGestureGenButton);
        Button inputButton = findViewById(R.id.threeGestureInputButton);
        Button nextButton = findViewById(R.id.threeGestureNextButton);
        Button resetButton = findViewById(R.id.threeGestureResetButton);
        Button readyButton=findViewById(R.id.readyGesture);
        TextView gestureText = findViewById(R.id.threeGestureText);
        TextView inputTV=findViewById(R.id.tvinput);
        builder = new AlertDialog.Builder(this);

        String fileWriteString="0.1"+","+"GestureTutorialStart,"+ AAHapticCommon.dateTime()+"\n";
        AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);


        TextView counterTV=findViewById(R.id.tvCounter);
        int count=getPattern.getCounter()+3*(AAHapticCommon.inputConditionCount*3+ AAHapticCommon.patternConditionCount);
        Log.e("Counter:", String.valueOf(getPattern.getCounter())+","+String.valueOf(AAHapticCommon.inputConditionCount)+","+String.valueOf(AAHapticCommon.patternConditionCount));


        patternCondition= AAHapticCommon.patternList.get(AAHapticCommon.patternConditionCount);

        Log.e("PattersGesture",String.valueOf(AAHapticCommon.patternList));

        counterTV.setText("Pattern.: "+String.valueOf(patternCondition));//+String.valueOf(patternCondition));


        if (patternCondition==3){
            Log.e("GestureTest",String.valueOf(patternCondition));
            if ( getPattern.getCounter() == 1) {
                // Getting the three vibration pattern
                // The number three is chosen at random:
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

        if (patternCondition==4){
            Log.e("GestureTest",String.valueOf(patternCondition));
            if ( getPattern.getCounter() == 1) {
                // Getting the three vibration pattern
                // The number three is chosen at random:
                answerPattern = getPattern.fourPatternOption(1);
            } else if (getPattern.getCounter() == 2) {
                // Getting the three vibration pattern
                // The number 2 is chosen at random:
                answerPattern = getPattern.fourPatternOption(2);
            } else if (getPattern.getCounter() == 3) {
                // Getting the three vibration pattern
                // The number 3 is chosen at random:
                answerPattern = getPattern.fourPatternOption(3);
            }
        }

        if (patternCondition==5){
            Log.e("GestureTest",String.valueOf(patternCondition));
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
                startTime= Calendar.getInstance().getTimeInMillis();
                generatePresses++;
                writeAns("0.1.1","GenstureInput","GenerateButton","Gesture");
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
                Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startTime = SystemClock.elapsedRealtime();
                    vb.vibrate(10000); // 10 seconds
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    vb.cancel();
                    endTime = SystemClock.elapsedRealtime();
                }
                float totalTime = ((float) endTime - (float) startTime);
                if (totalTime > 0) {
                    if (totalTime > shortVibrationTime) {
                        userCreatedPattern.add("Dash");
                        writeAns("0.1.1","gestureInput","-","Gesture");
                    } else {
                        userCreatedPattern.add("Dot");
                        writeAns("0.1.1","gestureInput",".","GestureTutorial");
                    }
//                    String inputText="";
//                    for (int i=0;i<userCreatedPattern.size();i++){
//                        inputText=inputText+userCreatedPattern
//                    }
                    inputTV.setText(getPattern.convertPatternToText(getPattern.convertToDotDash(userCreatedPattern)));
                    Log.e("userAns",String.valueOf(userCreatedPattern));
                }

                return true;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeAns("3.1","final",getPattern.convertToDotDash(userCreatedPattern),"GestureTutorial");
                // Same activity to be repeated again
                String result="Incorrect";
                if (answerPattern.toString().equals(getPattern.convertToDotDash(userCreatedPattern))){
                    result="Correct";
                }

                builder.setMessage("Required: " + getPattern.convertPatternToText(answerPattern.toString()) + "\nYou entered: " + getPattern.convertPatternToText(getPattern.convertToDotDash(userCreatedPattern)))
                        .setPositiveButton("Okay!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                if ((getPattern.getCounter() == 1 || getPattern.getCounter() == 2)) {
                                    getPattern.incrementCounter();

                                } else {
                                    getPattern.resetCounter();
                                    AAHapticCommon.patternConditionCount++;
                                    if (AAHapticCommon.patternConditionCount == 3) {
                                        AAHapticCommon.patternConditionCount = 0;
                                    }
                                }

                                Intent sameActivity = new Intent(TutorialGesture.this, TutorialGesture.class);

                                startActivity(sameActivity);

                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle(result);

                alert.show();
            }
        });

        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Repeat the same activity
                AAHapticCommon.patternConditionCount=0;
                getPattern.resetCounter();
                String fileWriteString="0.1"+","+"GestureTutorialFinish,"+ AAHapticCommon.dateTime()+"\n";
                AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);
                Intent sameActivity = new Intent(TutorialGesture.this, AAInputGesture.class);
                startActivity(sameActivity);
                finish();

            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCreatedPattern.clear();
                inputTV.setText("");
                writeAns("0.1.1","gestureInput","Reset","GestureTutorial");
            }
        });
    }
    private void writeAns(String index, String tag, String selectedOption, String inputType){
        String fileWriteString=index+","+patternCondition+","+getPattern.getCounter()+","+inputType+","+tag+","+ AAHapticCommon.dateTime()+","+valueOf(startTime)+","+valueOf(Calendar.getInstance().getTimeInMillis())+","+valueOf(generatePresses)+","+answerPattern.toString()+","+selectedOption+"\n";
        AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);

    }
}