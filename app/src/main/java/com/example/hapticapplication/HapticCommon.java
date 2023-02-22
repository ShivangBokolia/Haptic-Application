package com.example.hapticapplication;

import static androidx.core.content.FileProvider.getUriForFile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HapticCommon {
    static String user="TestUser";

    static Context context;
    static private Uri paths;
    static String startDateTime;
    static String participant="P-test";
    static int[] patternConditionArray = new int[] {4,5,3};
    static int[] inputConditionArray = new int[] {3,2,1};
    static int patternConditionCount=0;
    static int inputConditionCount=0;



    public static void writeAnswerToFile(Context cntx, String textToWrite){
        try {
            String fileName;
            File answerFile;
            File filePath;


            // Creates a file in the primary external storage space of the
            // current application.
            // If the file does not exists, it is created.
            context=cntx;
            fileName="AnswerFileOf-"+user+".txt";
            String ansString=textToWrite;
            filePath = new File(cntx.getFilesDir(), "docs");
            if (!filePath.exists()){
                filePath.mkdir();
            }
            //--------------------
            answerFile= new File(cntx.getExternalFilesDir(null), fileName);
            if (!answerFile.exists()){
                answerFile.createNewFile();
                // Adds a line to the file
            }

            //answerFile = new File(filePath, fileName);
            Log.e("cntx",String.valueOf(cntx));
            paths = getUriForFile(cntx, "com.example.hapticapplication.provider", answerFile);
            //Log.e("URIFilePath",paths.toString());
            BufferedWriter writerImage = new BufferedWriter(new FileWriter(answerFile, true /*append*/));
            //writerImage.write("Current number is :" + String.valueOf(currentNumber)+"\n");
            writerImage.write(ansString);
            writerImage.close();
            Log.e("FilePath",cntx.getApplicationContext().getApplicationContext().getPackageName()+"/TestFile.txt");
            MediaScannerConnection.scanFile(cntx,
                    new String[]{answerFile.toString()},
                    null,
                    null);
            //------------------------
            //Writing data to a backup file in case emailing is not done properly
            File backUpFile = new File(cntx.getExternalFilesDir(null), "BackupAnsof"+user+".txt");
            if (!backUpFile.exists()){
                backUpFile.createNewFile();
                // Adds a line to the file
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(backUpFile, true /*append*/));
            writer.write(ansString);
            writer.close();
            MediaScannerConnection.scanFile(cntx,
                    new String[]{backUpFile.toString()},
                    null,
                    null);
            // Refresh the data so it can seen when the device is plugged in a
            // computer. You may have to unplug and replug the device to see the
            // latest changes. This is not necessary if the user should not modify
            // the files.


        } catch (IOException e) {
            Log.e("ReadWriteFile", "Unable to write to the file."+e);
        }


    }

    public static void sendEmail(Activity activity){



        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        // set the type to 'email'
        emailIntent.setType("vnd.android.cursor.dir/email");
        String to[] = {"roshan82@gmail.com"};
        //String to[] = {"roshan82@gmail.com","embodiedroshan@gmail.com"};
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        // the attachment

        emailIntent.putExtra(Intent.EXTRA_STREAM, paths);


        // the mail subject
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "HAEvaluation-Ans From.."+user);
        activity.startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }




    public static String dateTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy,HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime.toString();
    }
}
