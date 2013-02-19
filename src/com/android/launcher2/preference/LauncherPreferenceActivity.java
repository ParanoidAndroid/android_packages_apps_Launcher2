package com.android.launcher2.preference;

import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

public class LauncherPreferenceActivity extends PreferenceActivity {

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen,
            Preference preference) {
        String key = preference.getKey();

        if (preference instanceof CheckBoxPreference) {
            CheckBoxPreference cPreference = (CheckBoxPreference) preference;
            PreferencesProvider.setBoolean(key, cPreference.isChecked());
        }

        return true;
    }
}