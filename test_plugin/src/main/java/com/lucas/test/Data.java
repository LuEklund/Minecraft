package com.lucas.test;

import java.util.Date;

public class Data {
    private String playerName;
    private boolean value;
    private Date date;

    public Data(String playerName, boolean value, Date date){
        this.playerName = playerName;
        this.value = value;
        this.date = date;
    }

    public String getPlayerName() {return playerName;}
    public boolean getValue() {return value;}
    public Date getDate() {return date;}



}
