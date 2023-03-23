package com.example.rentappartmentclient.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PreferenceManager {

    private final static String PREFERENCE_FILE = "com.example.rentappartmentclient.model";
    private final static String USER_ID = "user_id";
    private final SharedPreferences sharedPref;

    public PreferenceManager(Context context) {
        sharedPref = context.getSharedPreferences(PREFERENCE_FILE, Context.MODE_PRIVATE);
    }

    public void saveUserId(int newValue){
        saveValue(USER_ID, newValue);
    }

    public int getUserId(){
        return getValue(USER_ID, -1);
    }

    private void saveValue(String key, int value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
        Log.i("PreferenceManager", "saved value " + value);
    }

    private int getValue(String key, int defaultValue) {
        return sharedPref.getInt(key, defaultValue);
    }
}
