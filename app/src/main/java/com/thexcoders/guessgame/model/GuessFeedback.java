package com.thexcoders.guessgame.model;

import com.thexcoders.guessgame.R;

public enum GuessFeedback {

    TOO_COLD("Too Cold", R.drawable.ic_cold),
    LITTLE_COLD("A little Cold", R.drawable.ic_coldish),
    WARM("Warm", R.drawable.ic_warm),
    HOT("Hot", R.drawable.ic_hotish),
    SO_HOT("So Hot", R.drawable.ic_hot),
    SUPER_HOT("Super Hot", R.drawable.ic_super_hot),
    FOUND("You found it!", R.drawable.ic_well_done);

    String message;
    int resId;
    public static final int SIZE = GuessFeedback.values().length - 1;

    GuessFeedback(String val, int id) {
        this.message = val;
        this.resId = id;
    }

    public int getResId() {
        return resId;
    }
    public String getMessage(){
        return message;
    }
}
