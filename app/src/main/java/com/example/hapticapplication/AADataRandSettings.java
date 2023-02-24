package com.example.hapticapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AADataRandSettings {

    List<Integer> randomPatternPerPage = new ArrayList<Integer>() {{add(3); add(4); add(5);}};
    List<String> randomActivity = new ArrayList<String>() {{add("Button"); add("Gesture"); add("Pattern");}};


    private static AADataRandSettings randInstance = new AADataRandSettings();
    public static AADataRandSettings getInstance() { return randInstance; }

    private AADataRandSettings() {}

    // Randomizing the vibration number
    public void shufflePageParams() {
        Collections.shuffle(randomPatternPerPage);
    }
    // Randomizing the activities
    public void shuffleActivity() { Collections.shuffle(randomActivity); }

    // Getting the first, second and third activity
    public String getFirstActivity() {
        // return "Gesture";
        return randomActivity.get(0);
    }
    public String getSecondActivity() { return randomActivity.get(1); }
    public String getThirdActivity() { return randomActivity.get(2); }

    // Getting the first, second and third vibration number
    public Integer getFirstPage() {
        // return 3;
        return randomPatternPerPage.get(0);
    }
    public Integer getSecondPage() {
        return randomPatternPerPage.get(1);
    }
    public Integer getThirdPage() {
        return randomPatternPerPage.get(2);
    }
}