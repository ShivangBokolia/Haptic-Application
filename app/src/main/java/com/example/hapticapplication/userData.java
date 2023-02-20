package com.example.hapticapplication;

public class userData {

    String userID;

    private static userData userDataInstance = new userData();
    public static userData getInstance() { return userDataInstance; }

    private userData() {}


}
