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

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

public final class PreferencesProvider {

    public static final String PREFERENCES_KEY = "com.android.launcher2_preferences";

    public static final String PREFERENCES_CHANGED = "preferences_changed";

    private static SharedPreferences mPreferences;
    private static Map sKeyValues;

    public static void load(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCES_KEY, 0);
        sKeyValues = mPreferences.getAll();
    }

    public static int getInt(String key, int def) {
        return sKeyValues.containsKey(key) && sKeyValues.get(key) instanceof Integer ? (Integer) sKeyValues
                .get(key) : def;
    }

    public static boolean getBoolean(String key, boolean def) {
        return sKeyValues.containsKey(key) && sKeyValues.get(key) instanceof Boolean ? (Boolean) sKeyValues
                .get(key) : def;
    }

    public static String getString(String key, String def) {
        return sKeyValues.containsKey(key) && sKeyValues.get(key) instanceof String ? (String) sKeyValues
                .get(key) : def;
    }

    public static void setInt(String key, Integer value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(key, value);
        editor.putBoolean(PreferencesProvider.PREFERENCES_CHANGED, true);
        editor.commit();
        sKeyValues.put(key, value);
    }

    public static void setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(key, value);
        editor.putBoolean(PreferencesProvider.PREFERENCES_CHANGED, true);
        editor.commit();
        sKeyValues.put(key, value);
    }

    public static void setString(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.putBoolean(PreferencesProvider.PREFERENCES_CHANGED, true);
        editor.commit();
        sKeyValues.put(key, value);
    }

    public static int getNumberHomescreens() {
        return getInt("ui_homescreen_screens", 5);
    }

    public static int getDefaultHomescreen(int def) {
        return getInt("ui_homescreen_default_screen", def + 1) - 1;
    }

    public static int getCellCountX(int def) {
        String[] values = getString("ui_homescreen_grid", "0|" + def).split("\\|");
        try {
            return Integer.parseInt(values[1]);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static int getCellCountY(int def) {
        String[] values = getString("ui_homescreen_grid", def + "|0").split("\\|");
        try {
            return Integer.parseInt(values[0]);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    public static boolean getShowSearchBar() {
        return getBoolean("ui_homescreen_general_search", true);
    }

    public static boolean getHideIconLabels() {
        return getBoolean("ui_homescreen_general_hide_icon_labels", false);
    }

    public static boolean getHomescreenShowScrollingIndicator() {
        return getBoolean("ui_homescreen_indicator_enable", true);
    }

    public static boolean getHomescreenFadeScrollingIndicator() {
        return getBoolean("ui_homescreen_indicator_fade", true);
    }

    public static int getHomescreenScrollingIndicatorPosition() {
        return Integer.parseInt(getString("ui_homescreen_indicator_position", "0"));
    }

    public static boolean getJoinWidgetsApps() {
        return getBoolean("ui_drawer_widgets_join_apps", true);
    }

    public static String getHiddenApps() {
        return getString("ui_drawer_hidden_apps", "");
    }

    public static boolean getDrawerShowScrollingIndicator() {
        return getBoolean("ui_drawer_indicator_enable", true);
    }

    public static boolean getDrawerFadeScrollingIndicator() {
        return getBoolean("ui_drawer_indicator_fade", true);
    }

    public static int getDrawerScrollingIndicatorPosition() {
        return Integer.parseInt(getString("ui_drawer_indicator_position", "0"));
    }

    public static boolean getShowDock() {
        return getBoolean("ui_dock_enabled", true);
    }

    public static int getNumberIcons(int def) {
        return getInt("ui_dock_icons", def);
    }

    public static int getNumberIconsVertical(int def) {
        return getInt("ui_dock_icons_vertical", def);
    }

    public static int getIconScale(int def) {
        return getInt("ui_dock_icon_scale", def);
    }

    public static boolean getShowDivider() {
        return getBoolean("ui_dock_divider", true);
    }

    public static boolean getAutoRotate(boolean def) {
        return getBoolean("ui_general_orientation", def);
    }

    public static boolean getPinchExpanded() {
        return getBoolean("ui_pinch_expanded", false);
    }
}
