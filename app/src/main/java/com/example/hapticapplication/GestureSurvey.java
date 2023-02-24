package com.example.hapticapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class GestureSurvey extends AppCompatActivity {
    EditText a1,a2,a3,a4,a5,a6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_survey);

        Button nextButton = findViewById(R.id.surveyGestureSaveButton);
        a1=findViewById(R.id.a1);
        a2=findViewById(R.id.a2);
        a3=findViewById(R.id.a3);
        a4=findViewById(R.id.a4);
        a5=findViewById(R.id.a5);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int condition= AAHapticCommon.inputList.get(AAHapticCommon.inputConditionCount);
                String fileWriteString="0."+String.valueOf(condition)+","+ AAHapticCommon.dateTime()+","+","+String.valueOf(Calendar.getInstance().getTimeInMillis())+","
                        +a1.getText()+","
                        +a2.getText()+","
                        +a3.getText()+","
                        +a4.getText()+","
                        +a5.getText()+","
                        +"\n";
                AAHapticCommon.writeAnswerToFile(getApplicationContext(),fileWriteString);
                AAHapticCommon.inputConditionCount++;
                if (AAHapticCommon.inputConditionCount>2){
                    //go to Qual answers
                    Intent surveyIntent = new Intent(GestureSurvey.this, AAHapticMainQualAns.class);
                    startActivity(surveyIntent);
                }else{
                    AAHapticCommon.patternConditionCount=0;
                    condition= AAHapticCommon.inputList.get(AAHapticCommon.inputConditionCount);
                    if (condition==1){
                        Intent surveyIntent = new Intent(GestureSurvey.this, AAInputGesture.class);
                        startActivity(surveyIntent);
                    }
                    if (condition==2){
                        Intent surveyIntent = new Intent(GestureSurvey.this, AAInputPattern.class);
                        startActivity(surveyIntent);
                    }
                    if (condition==3){
                        Intent surveyIntent = new Intent(GestureSurvey.this, AAInputButton.class);
                        startActivity(surveyIntent);
                    }
                }
                // Shuffle the vibration params again for the next activity
                /*randSettings.shufflePageParams();

                // This will find the SECOND activity
                if (randSettings.getFirstActivity().matches("Gesture")) {

                    String nextActivity = randSettings.getSecondActivity();
                    if (randSettings.getFirstPage() == 3) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, ButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, ThreePatternCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 4) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, FourButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, FourPatternCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 5) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, FiveButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, FivePatternCond.class);
                            startActivity(intentGesture);
                        }
                    }
                }
                // This will find the THIRD activity
                else if (randSettings.getSecondActivity().matches("Gesture")) {
                    String nextActivity = randSettings.getThirdActivity();
                    if (randSettings.getFirstPage() == 3) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, ButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, ThreePatternCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 4) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, FourButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, FourPatternCond.class);
                            startActivity(intentGesture);
                        }
                    } else if (randSettings.getFirstPage() == 5) {
                        if (nextActivity.matches("Button")) {
                            Intent intentPattern = new Intent(GestureSurvey.this, FiveButtonCond.class);
                            startActivity(intentPattern);
                        } else if (nextActivity.matches("Pattern")) {
                            Intent intentGesture = new Intent(GestureSurvey.this, FivePatternCond.class);
                            startActivity(intentGesture);
                        }
                    }
                }
                // This will display the data of the user
                else if (randSettings.getThirdActivity().matches("Gesture")) {}*/
            }
        });
    }
}