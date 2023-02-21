package com.example.hapticapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class getPattern {

    private int counter = 1;
    private boolean threeButton = false;
    private boolean fourButton = false;
    private boolean fiveButton = false;

    private boolean threeGesture = false;
    private boolean fourGesture = false;
    private boolean fiveGesture = false;

    private boolean threePattern = false;
    private boolean fourPattern = false;
    private boolean fivePattern = false;

    List<String> patternSize3 = new ArrayList<String>() {{
        add("...");
        add("_..");
        add("._.");
        add("__.");
        add(".._");
        add("_._");
        add(".__");
        add("___");
    }};

    List<String> patternSize4 = new ArrayList<String>() {{
        add("....");
        add("_...");
        add("._..");
        add("__..");
        add(".._.");
        add("_._.");
        add(".__.");
        add("___.");
        add("..._");
        add("_.._");
        add("._._");
        add("__._");
        add("..__");
        add("_.__");
        add(".___");
        add("____");
    }};

    List<String> patternSize5 = new ArrayList<String>() {{
        add(".....");
        add("_....");
        add("._...");
        add("__...");
        add(".._..");
        add("_._..");
        add(".__..");
        add("___..");
        add("..._.");
        add("_.._.");
        add("._._.");
        add("__._.");
        add("..__.");
        add("_.__.");
        add(".___.");
        add("____.");
        add("...._");
        add("_..._");
        add("._.._");
        add("__.._");
        add(".._._");
        add("_._._");
        add(".__._");
        add("___._");
        add("...__");
        add("_..__");
        add("._.__");
        add("__.__");
        add("..___");
        add("_.___");
        add(".____");
        add("_____");
    }};

    private static getPattern getPatternInstance = new getPattern();
    public static getPattern getInstance() { return getPatternInstance; }

    private getPattern() {}

    public void randomizeLists () {
        Collections.shuffle(patternSize3);
        Collections.shuffle(patternSize4);
        Collections.shuffle(patternSize5);
    }

    public String threePatternOption (int pos) {
        return patternSize3.get(pos);
    }

    public String fourPatternOption (int pos) {
        return patternSize4.get(pos);
    }

    public String fivePatternOption (int pos) {
        return patternSize5.get(pos);
    }

    // This logic converts your "._." to "{0, 300, 700, 1000, 700, 300}".
    public long[] convertPattern(String pattern, int shortVibrationTime, int longVibrationTime) {
        ArrayList<Long> timePattern = new ArrayList<>();
        timePattern.add(0L);
        for (int i=0; i<pattern.length(); i++){
            if (pattern.charAt(i) == '.') {
                timePattern.add((long) shortVibrationTime);
                timePattern.add(700L);
            } else if (pattern.charAt(i) == '_') {
                timePattern.add((long) longVibrationTime);
                timePattern.add(700L);
            }
        }

        long[] timePatternLong = new long[timePattern.size()];
        for (int i=0; i<timePattern.size(); i++) {
            timePatternLong[i] = timePattern.get(i);
        }

        return timePatternLong;
    }

    public String convertToDotDash( ArrayList<String> userCreatedPattern ) {
        StringBuilder dotDashPattern = new StringBuilder();
        for (String pattern : userCreatedPattern) {
            if (pattern.matches("Dot")) {
                dotDashPattern.append(".");
            } else if (pattern.matches("Dash")) {
                dotDashPattern.append("_");
            }
        }
        return dotDashPattern.toString();
    }

    public String convertPatternToText(String pattern) {
        StringBuilder textPattern = new StringBuilder();
        for (int i=0; i<pattern.length(); i++){
            if (pattern.charAt(i) == '.') {
                textPattern.append("Dot ");
            } else if (pattern.charAt(i) == '_') {
                textPattern.append("Dash ");
            }
        }
        return textPattern.toString();
    }

    public int getCounter() { return counter; }

    public void incrementCounter() { counter += 1; }

    public void resetCounter() { counter = 1; }

    public boolean isThreeButton() {
        return threeButton;
    }

    public void setThreeButton(boolean threeButton) {
        this.threeButton = threeButton;
    }

    public boolean isFourButton() {
        return fourButton;
    }

    public void setFourButton(boolean fourButton) {
        this.fourButton = fourButton;
    }

    public boolean isFiveButton() {
        return fiveButton;
    }

    public void setFiveButton(boolean fiveButton) {
        this.fiveButton = fiveButton;
    }

    public boolean isThreeGesture() {
        return threeGesture;
    }

    public void setThreeGesture(boolean threeGesture) {
        this.threeGesture = threeGesture;
    }

    public boolean isFourGesture() {
        return fourGesture;
    }

    public void setFourGesture(boolean fourGesture) {
        this.fourGesture = fourGesture;
    }

    public boolean isFiveGesture() {
        return fiveGesture;
    }

    public void setFiveGesture(boolean fiveGesture) {
        this.fiveGesture = fiveGesture;
    }

    public boolean isThreePattern() {
        return threePattern;
    }

    public void setThreePattern(boolean threePattern) {
        this.threePattern = threePattern;
    }

    public boolean isFourPattern() {
        return fourPattern;
    }

    public void setFourPattern(boolean fourPattern) {
        this.fourPattern = fourPattern;
    }

    public boolean isFivePattern() {
        return fivePattern;
    }

    public void setFivePattern(boolean fivePattern) {
        this.fivePattern = fivePattern;
    }
}
