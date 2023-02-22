package com.example.hapticapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Set;

// TODO: Fix the participation ID bug

public class Participation extends AppCompatActivity {

    Button settingsButton;
    Button mainStudyButton, tutorialButton;
    EditText idText;

    String id = HapticCommon.user;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participation);

        idText = findViewById(R.id.partID);
        settingsButton = findViewById(R.id.settings);
        mainStudyButton = findViewById(R.id.mainStudy);
        tutorialButton = findViewById(R.id.tutorial);
        idText.setText(HapticCommon.user);

        randSettings randSettings = com.example.hapticapplication.randSettings.getInstance();

        // randomize the patterns list:
        getPattern getPattern = com.example.hapticapplication.getPattern.getInstance();
        getPattern.randomizeLists();

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idText.getText().toString().trim().matches("")) {
                    HapticCommon.user=idText.getText().toString().trim();
                    HapticCommon.writeAnswerToFile(getApplicationContext(),String.valueOf(id));
                    // Create the number of params for each of the pages:
                    randSettings randSettings = com.example.hapticapplication.randSettings.getInstance();
                    randSettings.shufflePageParams();
                    Log.e("test","HomePage");
                    Intent intent = new Intent(Participation.this, Settings.class);
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
                    int condition=HapticCommon.inputConditionArray[HapticCommon.inputConditionCount];
                    if (condition==1){
                        Intent surveyIntent = new Intent(Participation.this, ThreeGestureCond.class);
                        startActivity(surveyIntent);
                    }
                    if (condition==2){
                        Intent surveyIntent = new Intent(Participation.this, ThreePatternCond.class);
                        startActivity(surveyIntent);
                    }
                    if (condition==3){
                        Intent surveyIntent = new Intent(Participation.this, FourButtonCond.class);
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

        tutorialButton.setOnClickListener(new View.OnClickListener() {
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
        HapticCommon.sendEmail(this);
    }
}