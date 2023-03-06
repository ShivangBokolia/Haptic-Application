package com.example.hapticapplication;

import static java.lang.String.valueOf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

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
        //tutorialButton = findViewById(R.id.tutorial);
        sendDataButton=findViewById(R.id.sendData);

        idText.setText(AAHapticCommon.user);


        // randomize the patterns list:
        AADataGetPattern getPattern = AADataGetPattern.getInstance();
        getPattern.randomizeLists();

        if (!AAHapticCommon.shuffled) {
            AAHapticCommon.shufflePatternList();
            AAHapticCommon.shuffleInputList();
            AAHapticCommon.shuffled = true;
        }

        String fileWriteString="0.0"+","+"Main Page2,"+ AAHapticCommon.dateTime()+"\n";
        AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);
        AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);

        Log.e("StartingInputList",String.valueOf(AAHapticCommon.inputList));
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idText.getText().toString().trim().matches("")) {
                    AAHapticCommon.user=idText.getText().toString().trim();
                    AAHapticCommon.writeAnswerToFile(getApplicationContext(),String.valueOf(id));
                    // Create the number of params for each of the pages:
                    Intent intent = new Intent(AAHapticMainPage.this, AAHapticMainSettings.class);
                    startActivity(intent);

                }
            }
        });

        mainStudyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!idText.getText().toString().trim().matches("") || !id.matches("")) {
                    AAHapticCommon.user=idText.getText().toString().trim();
                    AAHapticCommon.writeAnswerToFile(getApplicationContext(),String.valueOf(id));

                    // Shuffling the activities and the vibration number for each page
                    int condition= AAHapticCommon.inputList.get(AAHapticCommon.inputConditionCount);
                    //condition=3;
                    if (condition==1){
                        Intent surveyIntent = new Intent(AAHapticMainPage.this, TutorialGesture.class);
                        startActivity(surveyIntent);
                    }
                    if (condition==2){
                        Intent surveyIntent = new Intent(AAHapticMainPage.this, TutorialPattern.class);
                        startActivity(surveyIntent);
                    }
                    if (condition==3){
                        Intent surveyIntent = new Intent(AAHapticMainPage.this, TutorialButton.class);
                        startActivity(surveyIntent);
                    }

                }
            }
        });

        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AAHapticCommon.user=idText.getText().toString().trim();
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