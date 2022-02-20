package com.thexcoders.guessgame.model;

import android.util.Log;

public class Guess {

    /*
     * Make one player enter and the other guess
     * make the number random
     * change the max and min of the guess result
     * change the domain of the numbers
     *
     * keep just for the session
     * persist using sharedPreferences
     * persist using a simple database
     * */

    private int number;
    private int tries = 0;
    public static  int MIN = 0;
    public static  int MAX = 100;

    public static int SUPER_HOT = 5;
    public static int SO_HOT = 10;
    public static int HOT = 15;
    public static int WARM = 20;
    public static int LITTLE_COLD = 30;


    public Guess() {
        generateNum();
    }

    public GuessFeedback getFeedback(int val) {
        tries++;
        int diff = Math.abs(val - number);
        if (diff == 0) return GuessFeedback.FOUND;
        if (diff < SUPER_HOT) return GuessFeedback.SUPER_HOT;
        if (diff < SO_HOT) return GuessFeedback.SO_HOT;
        if (diff < HOT) return GuessFeedback.HOT;
        if (diff < WARM) return GuessFeedback.WARM;
        if (diff < LITTLE_COLD) return GuessFeedback.LITTLE_COLD;
        return GuessFeedback.TOO_COLD;
    }

    public void regenerate() {
        generateNum();
        tries = 0;
    }

    private void generateNum() {
        double rand = Math.random();
        number = (int) (rand * (MAX - MIN));
        Log.e("Number is ", ""+number);
    }


    public int getNumber() {
        return number;
    }

    public int getTries() {
        return tries;
    }
}
