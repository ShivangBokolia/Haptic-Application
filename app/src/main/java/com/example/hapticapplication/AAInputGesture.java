package com.example.hapticapplication;

import static java.lang.String.valueOf;

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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

// TODO: Check if the user created the correct answer
// TODO: Save the answer provided by the user
// TODO: The dot is not being said by the voice (FIX IT)

public class AAInputGesture extends AppCompatActivity {

    private int shortVibrationTime, longVibrationTime;
    private String answerPattern;
    long startTime=0;
    int generatePresses=0;
    int patternCondition=0;
    AADataGetPattern getPattern = AADataGetPattern.getInstance();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        // Creating a vibrator object for the vibrations
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Log.e("view","threeGesture");
        // Getting the instance for the patterns and vibration settings
        AADataRandSettings randSettings = AADataRandSettings.getInstance();
        vibSettings vibSettings = com.example.hapticapplication.vibSettings.getInstance();

        // Setting the timings for short vibrations and long vibrations
        shortVibrationTime = vibSettings.getData();
        longVibrationTime = vibSettings.getData() * 3;

        // Making the object for the buttons
        Button genButton = findViewById(R.id.threeGestureGenButton);
        Button inputButton = findViewById(R.id.threeGestureInputButton);
        Button nextButton = findViewById(R.id.threeGestureNextButton);
        Button resetButton = findViewById(R.id.threeGestureResetButton);
        TextView gestureText = findViewById(R.id.threeGestureText);
        TextView inputTV=findViewById(R.id.tvinput);


        TextView counterTV=findViewById(R.id.tvCounter);
        int count=getPattern.getCounter()+3*(AAHapticCommon.inputConditionCount*3+ AAHapticCommon.patternConditionCount);
        Log.e("Counter:", String.valueOf(getPattern.getCounter())+","+String.valueOf(AAHapticCommon.inputConditionCount)+","+String.valueOf(AAHapticCommon.patternConditionCount));


        patternCondition= AAHapticCommon.patternList.get(AAHapticCommon.patternConditionCount);

        Log.e("PattersGesture",String.valueOf(AAHapticCommon.patternList));

        counterTV.setText("Trial No.: "+String.valueOf(count)+"/27");//+String.valueOf(patternCondition));


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
                startTime=Calendar.getInstance().getTimeInMillis();
                generatePresses++;
                writeAns("1.1","GenstureInput","GenerateButton","Gesture");
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
                        writeAns("1.1","gestureInput","-","Gesture");
                    } else {
                        userCreatedPattern.add("Dot");
                        writeAns("1.1","gestureInput",".","Gesture");
                    }
//                    String inputText="";
//                    for (int i=0;i<userCreatedPattern.size();i++){
//                        inputText=inputText+userCreatedPattern
//                    }
                    inputTV.setText(getPattern.convertToDotDash(userCreatedPattern));
                    Log.e("userAns",String.valueOf(userCreatedPattern));
                }

                return true;
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeAns("2.1","final",getPattern.convertToDotDash(userCreatedPattern),"Gesture");
                // Same activity to be repeated again
                if ((getPattern.getCounter() == 1 || getPattern.getCounter() == 2) && !getPattern.isThreeGesture()) {
                    getPattern.incrementCounter();
                    Intent sameActivity = new Intent(AAInputGesture.this, AAInputGesture.class);
                    startActivity(sameActivity);
                }
                // Move on to a different activity
                else if (getPattern.getCounter() == 3) {
                    getPattern.resetCounter();
                    AAHapticCommon.patternConditionCount++;
                    if (AAHapticCommon.patternConditionCount>2){
                        AAHapticCommon.shufflePatternList();
                        Intent surveyIntent = new Intent(AAInputGesture.this, GestureSurvey.class);
                        startActivity(surveyIntent);
                    }else{
                        Intent intent = new Intent(AAInputGesture.this, AAInputGesture.class);
                        startActivity(intent);
                    }

                    /*getPattern.resetCounter();
                    // Marking activity as done
                    getPattern.setThreeGesture(true);

                    if (answerPattern.matches(getPattern.convertToDotDash(userCreatedPattern))) {
                        gestureText.setText("Correct Answer");
                    } else {
                        gestureText.setText("Wrong Answer");
                    }

                    if (randSettings.getFirstPage() == 3) {
                        int nextPageVib = randSettings.getSecondPage();
                        if (nextPageVib == 4) {
                            Intent fourActivityIntent = new Intent(ThreeGestureCond.this, FourGestureCond.class);
                            startActivity(fourActivityIntent);
                        } else if (nextPageVib == 5) {
                            Intent fiveActivityIntent = new Intent(ThreeGestureCond.this, FiveGestureCond.class);
                            startActivity(fiveActivityIntent);
                        }
                    } else if (randSettings.getSecondPage() == 3) {
                        int nextPageVib = randSettings.getThirdPage();
                        if (nextPageVib == 4) {
                            Intent fourActivityIntent = new Intent(ThreeGestureCond.this, FourGestureCond.class);
                            startActivity(fourActivityIntent);
                        } else if (nextPageVib == 5) {
                            Intent fiveActivityIntent = new Intent(ThreeGestureCond.this, FiveGestureCond.class);
                            startActivity(fiveActivityIntent);
                        }
                    } else if (randSettings.getThirdPage() == 3) {
                        Intent surveyIntent = new Intent(ThreeGestureCond.this, GestureSurvey.class);
                        startActivity(surveyIntent);
                    }*/
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCreatedPattern.clear();
                inputTV.setText("");
                writeAns("1.1","gestureInput","Reset","Gesture");
            }
        });
    }
    private void writeAns(String index, String tag, String selectedOption, String inputType){
        String fileWriteString=index+","+patternCondition+","+getPattern.getCounter()+","+inputType+","+tag+","+ AAHapticCommon.dateTime()+","+valueOf(startTime)+","+valueOf(Calendar.getInstance().getTimeInMillis())+","+valueOf(generatePresses)+","+answerPattern.toString()+","+selectedOption+"\n";
        AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);

    }
}