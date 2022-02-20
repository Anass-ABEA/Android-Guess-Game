package com.thexcoders.guessgame.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageHelper {

    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    public StorageHelper(Context context, boolean isEditable) {
        sp = context.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        if (isEditable) spEditor = sp.edit();
    }

    public StorageHelper(Context context) {
        this(context, false);
    }

    public int getMax() {
        return sp.getInt("max", 100);
    }

    public int getMin() {
        return sp.getInt("min", 0);
    }

    public int getLittleCold() {
        return sp.getInt("little_cold", 30);
    }

    public int getWarm() {
        return sp.getInt("warm", 20);
    }

    public int getHot() {
        return sp.getInt("hot", 15);
    }

    public int getSoHot() {
        return sp.getInt("so_hot", 10);
    }

    public int getSuperHot() {
        return sp.getInt("super_hot", 5);
    }

    // setters

    public void setMax(int val) {
        spEditor.putInt("max", val);
    }

    public void setMin(int val) {
        spEditor.putInt("min", val);
    }

    public void setLittleCold(int val) {
        spEditor.putInt("little_cold", val);
    }

    public void setWarm(int val) {
        spEditor.putInt("warm", val);
    }

    public void setHot(int val) {
        spEditor.putInt("hot", val);
    }

    public void setSoHot(int val) {
        spEditor.putInt("so_hot", val);
    }

    public void setSuperHot(int val) {
        spEditor.putInt("super_hot", val);
    }

}
