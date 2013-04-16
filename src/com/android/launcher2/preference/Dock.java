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

import android.os.Bundle;

import com.android.launcher.R;
import com.android.launcher2.LauncherModel;

public class Dock extends LauncherPreferenceActivity {

    private NumberPickerPreference mIconsHorizontal;
    private NumberPickerPreference mIconsVertical;
    private SeekBarDialogPreference mPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences_dock);

        mIconsHorizontal = (NumberPickerPreference)findPreference("ui_dock_icons");
        mIconsVertical = (NumberPickerPreference)findPreference("ui_dock_icons_vertical");
        mIconsHorizontal.setDefaultValue(LauncherModel.getHotseatCellCountX());
        mIconsVertical.setDefaultValue(LauncherModel.getHotseatCellCountY());

        mPercent = (SeekBarDialogPreference)findPreference("ui_dock_icon_scale");
        int scale = PreferencesProvider.getIconScale(getResources().getInteger(
                R.integer.hotseat_item_scale_percentage));
        mPercent.setDefaultValue(scale);
    }
}
