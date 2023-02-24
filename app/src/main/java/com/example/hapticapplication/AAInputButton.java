package com.example.hapticapplication;

import static java.lang.String.valueOf;

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
import java.util.Calendar;
import java.util.List;

// TODO: Check if the user pressed the correct answer
// TODO: Save the answer provided by the user

public class AAInputButton extends AppCompatActivity {
    long startTime=0;
    int generatePresses=0;
    String selectedpattern="";
    int patternCondition=0;
    StringBuilder answerPattern = new StringBuilder();
    StringBuilder option1Pattern = new StringBuilder();
    StringBuilder option2Pattern = new StringBuilder();
    AADataGetPattern getPattern = AADataGetPattern.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_button_cond);

        // Creating a vibrator object for the vibrations
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Storing the answer selected by the user
        List<String> selectedAnswer = new ArrayList<>();

        // Getting the instance for the patterns and vibration settings
        AADataRandSettings randSettings = AADataRandSettings.getInstance();
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


        patternCondition= AAHapticCommon.patternList.get(AAHapticCommon.patternConditionCount);

        Log.e("PattersGesture",String.valueOf(AAHapticCommon.patternList));

        TextView counterTV=findViewById(R.id.tvCounter);
        int count=getPattern.getCounter()+3*(AAHapticCommon.inputConditionCount*3+ AAHapticCommon.patternConditionCount);
        Log.e("Counter:", String.valueOf(getPattern.getCounter())+","+String.valueOf(AAHapticCommon.inputConditionCount)+","+String.valueOf(AAHapticCommon.patternConditionCount));
        counterTV.setText("Trial No.: "+String.valueOf(count)+"/27");//+String.valueOf(patternCondition));


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
                startTime=Calendar.getInstance().getTimeInMillis();
                generatePresses++;
                writeAns("1.3","selection","GenerateButton","Button");
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
                selectedpattern=option1Pattern.toString();
                writeAns("1.3","selection",selectedpattern,"button");

            }
        });

        // Playing the option 2 vibration and moving on to the next activity
        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedpattern=option2Pattern.toString();
                writeAns("1.3","selection",selectedpattern,"button");
            }
        });

        // Playing the option 3 (answer) vibration and moving on to the next activity
        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedpattern=(answerPattern.toString());
                writeAns("1.3","selection",selectedpattern,"button");
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Repeat the same activity
                writeAns("2.1","final",selectedpattern,"button");
                if ((getPattern.getCounter() == 1 || getPattern.getCounter() == 2) && !getPattern.isFourButton()) {
                    getPattern.incrementCounter();
                    Intent sameActivity = new Intent(AAInputButton.this, AAInputButton.class);
                    startActivity(sameActivity);
                }
                // Move to the next activity
                else if (getPattern.getCounter() == 3 && !getPattern.isFourButton()) {

                    getPattern.resetCounter();

                    AAHapticCommon.patternConditionCount++;
                    if (AAHapticCommon.patternConditionCount>2){
                        AAHapticCommon.shufflePatternList();
                        Intent surveyIntent = new Intent(AAInputButton.this, GestureSurvey.class);
                        startActivity(surveyIntent);
                    }else{
                        Intent intent = new Intent(AAInputButton.this, AAInputButton.class);
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
    private void writeAns(String index, String tag, String selectedOption, String inputType){
        String fileWriteString=index+","+patternCondition+","+getPattern.getCounter()+","+inputType+","+tag+","+ AAHapticCommon.dateTime()+","+valueOf(startTime)+","+valueOf(Calendar.getInstance().getTimeInMillis())+","+valueOf(generatePresses)+","+answerPattern.toString()+","+selectedOption+"\n";
        AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);

    }
}