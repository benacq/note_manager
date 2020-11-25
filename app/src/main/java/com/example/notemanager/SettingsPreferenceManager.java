package com.example.notemanager;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

import static androidx.preference.PreferenceManager.*;

public class SettingsPreferenceManager {

    public static void putPref(String key, String value, Context context) {
        SharedPreferences prefs = getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPrefString(String key, Context context) {
        SharedPreferences preferences = getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static Boolean getPrefBool(String key, Context context) {
        SharedPreferences preferences = getDefaultSharedPreferences(context);
        return preferences.getBoolean(key, false);
    }

    static void toggleNightMode(boolean state) {
        if (state) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    static void useDeviceTheme(boolean state) {
        if (state) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
