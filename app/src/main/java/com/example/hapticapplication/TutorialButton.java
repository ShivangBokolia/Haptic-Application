package com.example.hapticapplication;

import static java.lang.String.valueOf;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
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
import java.util.Collections;
import java.util.List;

public class TutorialButton extends AppCompatActivity {
    long startTime=0;
    int generatePresses=0;
    String selectedpattern="";
    int patternCondition=0;
    StringBuilder answerPattern = new StringBuilder();
    StringBuilder option1Pattern = new StringBuilder();
    StringBuilder option2Pattern = new StringBuilder();
    AADataGetPattern getPattern = AADataGetPattern.getInstance();
    TextView tvInput;
    List<String> answerList = new ArrayList<String>();
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutorial_button);

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
        getPattern.randomizeLists();
        String fileWriteString="0.3"+","+"ButtonTutorialStart,"+ AAHapticCommon.dateTime()+"\n";
        AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);


        // Making the object for the buttons
        Button generateButton = findViewById(R.id.fourGenButtonTut);
        Button option1Button = findViewById(R.id.fourOpt1ButtonTut);
        Button option2Button = findViewById(R.id.fourOpt2ButtonTut);
        Button option3Button = findViewById(R.id.fourOpt3ButtonTut);
        Button nextButton = findViewById(R.id.fourButtonNextButtonTut);
        Button readyButton=findViewById(R.id.readyButton);
        tvInput=findViewById(R.id.tvButtonInputTut);
        builder = new AlertDialog.Builder(this);


        patternCondition= AAHapticCommon.patternList.get(AAHapticCommon.patternConditionCount);

        Log.e("PattersGesture",String.valueOf(AAHapticCommon.patternList));

        TextView counterTV=findViewById(R.id.tvCounterTutButton);
        int count=getPattern.getCounter()+3*(AAHapticCommon.inputConditionCount*3+ AAHapticCommon.patternConditionCount);
        Log.e("Counter:", String.valueOf(getPattern.getCounter())+","+String.valueOf(AAHapticCommon.inputConditionCount)+","+String.valueOf(AAHapticCommon.patternConditionCount));
        counterTV.setText("Pattern.: "+String.valueOf(patternCondition));//+String.valueOf(patternCondition));



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
        answerList.add(option1Pattern.toString());
        answerList.add(option2Pattern.toString());
        answerList.add(answerPattern.toString());

        Collections.shuffle(answerList);

        // Setting the texts for the buttons
        option1Button.setText(getPattern.convertPatternToText(answerList.get(0)));
        option2Button.setText(getPattern.convertPatternToText(answerList.get(1)));
        option3Button.setText(getPattern.convertPatternToText(answerList.get(2)));

        // Converting pattern from "._." to "{0, 300, 700, 1000, 700, 300}"
        // The vibrator object requires it in the following format.
        long[] convPattern = getPattern.convertPattern(answerPattern.toString(), shortVibrationTime, longVibrationTime);

        // Playing the answer vibration on pressing the generate button
        generateButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                startTime= Calendar.getInstance().getTimeInMillis();
                generatePresses++;
                writeAns("0.1.3","selection","GenerateButton","ButtonTutorial");
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
                selectedpattern=answerList.get(0);
                tvInput.setText(getPattern.convertPatternToText(selectedpattern));
                writeAns("0.1.3","selection",selectedpattern,"ButtonTutorial");

            }
        });

        // Playing the option 2 vibration and moving on to the next activity
        option2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedpattern=answerList.get(1);
                tvInput.setText(getPattern.convertPatternToText(selectedpattern));
                writeAns("0.1.3","selection",selectedpattern,"ButtonTutorial");
            }
        });

        // Playing the option 3 (answer) vibration and moving on to the next activity
        option3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedpattern=answerList.get(2);
                tvInput.setText(getPattern.convertPatternToText(selectedpattern));
                writeAns("0.1.3","selection",selectedpattern,"ButtonTutorial");
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Repeat the same activity
                writeAns("0.5.3","final",selectedpattern,"ButtonTutorial");

                String result="Incorrect";
                if (answerPattern.toString().equals(selectedpattern)){
                    result="Correct";
                }

                builder.setMessage("Required: " + getPattern.convertPatternToText(answerPattern.toString()) + "\nYou entered: " + getPattern.convertPatternToText(selectedpattern))
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

                                Intent sameActivity = new Intent(TutorialButton.this, TutorialButton.class);

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
                String fileWriteString="0.3"+","+"ButtonTutorialFinish,"+ AAHapticCommon.dateTime()+"\n";
                AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);
                Intent sameActivity = new Intent(TutorialButton.this, AAInputButton.class);
                startActivity(sameActivity);
                finish();

            }
        });

    }
    private void writeAns(String index, String tag, String selectedOption, String inputType){
        String fileWriteString=index+","+patternCondition+","+getPattern.getCounter()+","+inputType+","+tag+","+ AAHapticCommon.dateTime()+","+valueOf(startTime)+","+valueOf(Calendar.getInstance().getTimeInMillis())+","+valueOf(generatePresses)+","+answerPattern.toString()+","+selectedOption+"\n";
        AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);

    }
}