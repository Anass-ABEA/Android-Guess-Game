package com.thexcoders.guessgame.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Score {

    private final String playerName;
    private final int tries;
    private Date date = new Date();

    public Score(String playerName, int tries) {
        this.playerName = playerName;
        this.tries = tries;
    }

    public Score(String playerName, int tries, String date) {
        this.playerName = playerName;
        this.tries = tries;
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            this.date = new Date();
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getTries() {
        return tries;
    }

    public String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

}
