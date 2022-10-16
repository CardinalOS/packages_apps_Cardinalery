/*
 *  Copyright (C) 2023 CardinalOS
 *  SPDX-License-Identifier: Apache-2.0
 */


package org.cardinalery;

import android.os.Bundle;
import com.android.settings.SettingsPreferenceFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import com.android.settings.R;

import com.android.internal.logging.nano.MetricsProto.MetricsEvent;

public class CardinalTeam extends SettingsPreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.cardinal_team);
    }

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.CARDINAL;
    }
}
