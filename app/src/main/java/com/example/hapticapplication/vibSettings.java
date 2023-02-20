package com.example.hapticapplication;

public class vibSettings {

    int vibrationSettings = 300;

    private static vibSettings varInstance = new vibSettings();
    public static vibSettings getInstance() {
        return varInstance;
    }

    private vibSettings() {}

    public void setData(int data) {
        this.vibrationSettings = data;
    }

    public int getData() {
        return vibrationSettings;
    }
}
