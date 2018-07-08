package com.fiqri.webinar;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    private String KEY_NAME = "NAMA";
    private String PREF_NAME = "SIMPAN";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Preference(Context c) {
        sharedPreferences = c.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
    }


    public String getLocLogin() {
        return sharedPreferences.getString(KEY_NAME, null);
    }


    public void setLocLogin(String nama) {
        editor = sharedPreferences.edit();
        // metode penyimpanan key dan value
        editor.putString(KEY_NAME, nama).apply();
    }


    public void logout() {
        editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}
