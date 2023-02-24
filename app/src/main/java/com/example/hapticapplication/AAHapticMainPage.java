package com.example.hapticapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// TODO: Fix the participation ID bug

public class AAHapticMainPage extends AppCompatActivity {

    Button settingsButton;
    Button mainStudyButton, tutorialButton, sendDataButton;
    EditText idText;

    String id = AAHapticCommon.user;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participation);

        idText = findViewById(R.id.partID);
        settingsButton = findViewById(R.id.settings);
        mainStudyButton = findViewById(R.id.mainStudy);
        tutorialButton = findViewById(R.id.tutorial);
        sendDataButton=findViewById(R.id.sendData);

        idText.setText(AAHapticCommon.user);

        AADataRandSettings randSettings = AADataRandSettings.getInstance();

        // randomize the patterns list:
        AADataGetPattern getPattern = AADataGetPattern.getInstance();
        getPattern.randomizeLists();

        AAHapticCommon.shufflePatternList();
        AAHapticCommon.shuffleInputList();

        Log.e("StartingInputList",String.valueOf(AAHapticCommon.inputList));
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idText.getText().toString().trim().matches("")) {
                    AAHapticCommon.user=idText.getText().toString().trim();
                    AAHapticCommon.writeAnswerToFile(getApplicationContext(),String.valueOf(id));
                    // Create the number of params for each of the pages:
                    AADataRandSettings randSettings = AADataRandSettings.getInstance();
                    randSettings.shufflePageParams();
                    Log.e("test","HomePage");
                    Intent intent = new Intent(AAHapticMainPage.this, AAHapticMainSettings.class);
                    startActivity(intent);
                }
            }
        });

        mainStudyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idText.getText().toString().trim().matches("") || !id.matches("")) {

                    // Shuffling the activities and the vibration number for each page
                    randSettings.shuffleActivity(); // {"Button", "Pattern", "Gesture"}
                    randSettings.shufflePageParams(); // {3, 4, 5}
                    int condition= AAHapticCommon.inputList.get(AAHapticCommon.inputConditionCount);

                    if (condition==1){
                        Intent surveyIntent = new Intent(AAHapticMainPage.this, AAInputGesture.class);
                        startActivity(surveyIntent);
                    }
                    if (condition==2){
                        Intent surveyIntent = new Intent(AAHapticMainPage.this, AAInputPattern.class);
                        startActivity(surveyIntent);
                    }
                    if (condition==3){
                        Intent surveyIntent = new Intent(AAHapticMainPage.this, AAInputButton.class);
                        startActivity(surveyIntent);
                    }
                    // First Activity has 3 vibrations:
                    /*
                    if (randSettings.getFirstPage() == 3) {
                        Log.e("vibs","3");
                        if (randSettings.getFirstActivity().matches("Button")) {
                            Intent intent = new Intent(Participation.this, ButtonCond.class);
                            startActivity(intent);
                        } else if (randSettings.getFirstActivity().matches("Gesture")) {
                            Intent intent = new Intent(Participation.this, ThreeGestureCond.class);
                            startActivity(intent);
                        } else if (randSettings.getFirstActivity().matches("Pattern")) {
                            Intent intent = new Intent(Participation.this, ThreePatternCond.class);
                            startActivity(intent);
                        }
                    }
                    // First Activity has 4 vibrations:
                    else if (randSettings.getFirstPage() == 4) {
                        Log.e("vibs","4");
                        if (randSettings.getFirstActivity().matches("Button")) {
                            Intent intent = new Intent(Participation.this, FourButtonCond.class);
                            startActivity(intent);
                        } else if (randSettings.getFirstActivity().matches("Gesture")) {
                            Intent intent = new Intent(Participation.this, FourGestureCond.class);
                            startActivity(intent);
                        } else if (randSettings.getFirstActivity().matches("Pattern")) {
                            Intent intent = new Intent(Participation.this, FourPatternCond.class);
                            startActivity(intent);
                        }
                    }
                    // First Activity has 5 vibrations:
                    else if (randSettings.getFirstPage() == 5) {
                        Log.e("vibs","5");
                        if (randSettings.getFirstActivity().matches("Button")) {
                            Intent intent = new Intent(Participation.this, FiveButtonCond.class);
                            startActivity(intent);
                        } else if (randSettings.getFirstActivity().matches("Gesture")) {
                            Intent intent = new Intent(Participation.this, FiveGestureCond.class);
                            startActivity(intent);
                        } else if (randSettings.getFirstActivity().matches("Pattern")) {
                            Intent intent = new Intent(Participation.this, FivePatternCond.class);
                            startActivity(intent);
                        }
                    }*/
                }
            }
        });

        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
                //Intent intent = new Intent(Participation.this, TutorialButton.class);
                //startActivity(intent);
            }

        });


    }

    void sendEmail(){
        getIntent().addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  getIntent().addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        AAHapticCommon.sendEmail(this);
    }
}