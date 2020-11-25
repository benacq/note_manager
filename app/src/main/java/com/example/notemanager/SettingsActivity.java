package com.example.notemanager;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            SwitchPreferenceCompat systemCurrentDisplay = findPreference("system_display_mode");
            SwitchPreferenceCompat nightModeToggle = findPreference("night_mode");


            boolean nightModeState = SettingsPreferenceManager.getPrefBool("night_mode", getContext());
            boolean isSystemDarkEnabled = SettingsPreferenceManager.getPrefBool("system_display_mode", getContext());

            assert nightModeToggle != null;
            nightModeToggle.setChecked(nightModeState);
            nightModeToggle.setEnabled(!isSystemDarkEnabled);


            assert systemCurrentDisplay != null;
            systemCurrentDisplay.setOnPreferenceChangeListener((preference, newValue) -> {
                SettingsPreferenceManager.useDeviceTheme((Boolean) newValue);
                if ((Boolean) newValue) {
                    nightModeToggle.setChecked(false);
                }
                nightModeToggle.setEnabled(!(Boolean) newValue);
                return true;
            });

            nightModeToggle.setOnPreferenceChangeListener((preference, newValue) -> {
                SettingsPreferenceManager.toggleNightMode((Boolean) newValue);
                return true;
            });

        }
    }
}