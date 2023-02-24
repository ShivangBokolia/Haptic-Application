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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AAHapticMainSettings extends AppCompatActivity {

    EditText speedText;
    Button playButton, saveButton;

    int timing = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        speedText = findViewById(R.id.speedNo);
        playButton = findViewById(R.id.playButton);
        saveButton = findViewById(R.id.saveButton);

        vibSettings vibSettings = com.example.hapticapplication.vibSettings.getInstance();
        AADataGetPattern getPattern = AADataGetPattern.getInstance();
        AADataRandSettings randSettings = AADataRandSettings.getInstance();

        playButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if (!speedText.getText().toString().trim().matches("")) {
                    int speed = Integer.parseInt(speedText.getText().toString());
                    if (speed > 0 && speed < 101) {
                        timing = speed * 3; //(speed/100)*300
                        String fileWriteString="2.0,settings,keylog,"+ AAHapticCommon.dateTime()+","+valueOf(Calendar.getInstance().getTimeInMillis())+","+String.valueOf(timing)+"\n";
                        AAHapticCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);

                        long[] patternTemplate = {0, timing, AAHapticCommon.pauseLength, timing*3, AAHapticCommon.pauseLength, timing};
                        VibrationEffect playVibration = VibrationEffect.createWaveform(patternTemplate, VibrationEffect.DEFAULT_AMPLITUDE);

                        vibrator.cancel();
                        vibrator.vibrate(playVibration);
                    }
                } else {
                    long[] patternTemplate = {0, timing, 700, timing* 3L, 700, timing};
                    VibrationEffect playVibration = VibrationEffect.createWaveform(patternTemplate, VibrationEffect.DEFAULT_AMPLITUDE);

                    vibrator.cancel();
                    vibrator.vibrate(playVibration);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setting the vibration value globally
                vibSettings.setData(timing);
                Intent intent = new Intent(AAHapticMainSettings.this, AAHapticMainPage.class);
                startActivity(intent);
            }
        });
    }
}