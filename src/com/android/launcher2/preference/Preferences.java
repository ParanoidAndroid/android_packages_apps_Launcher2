/*
 * Copyright (C) 2013 ParanoidAndroid Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher2.preference;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;

import com.android.launcher.R;

public class Preferences extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        OnPreferenceChangeListener listener = new OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                PreferencesProvider.setBoolean(preference.getKey(), (Boolean) newValue);
                return true;
            }
        };

        findPreference("ui_general_orientation").setOnPreferenceChangeListener(listener);
        findPreference("ui_pinch_expanded").setOnPreferenceChangeListener(listener);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        String key = preference.getKey();
        Intent i = null;
        if ("preferences_homescreen_section".equals(key)) {
            i = new Intent(Preferences.this, Homescreen.class);
        } else if ("preferences_drawer_section".equals(key)) {
            i = new Intent(Preferences.this, Drawer.class);
        } else if ("preferences_dock_section".equals(key)) {
            i = new Intent(Preferences.this, Dock.class);
        } else if ("preferences_restart_launcher".equals(key)) {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
        if (i != null) startActivity(i);
        return true;
    }
}
