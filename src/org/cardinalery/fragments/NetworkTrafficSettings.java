/*
 * Copyright (C) 2023 CardinalOS
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
package org.cardinalery.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.SearchIndexableResource;
import android.provider.Settings;
import android.view.View;

import androidx.preference.DropDownPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.SwitchPreference;

import com.android.internal.logging.nano.MetricsProto.MetricsEvent;
import com.android.settings.R;
import com.android.settings.Utils;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;

import org.cardinalos.framework.preferences.SystemSettingSwitchPreference;
import org.cardinalos.framework.preferences.SystemSettingMainSwitchPreference;

import java.util.ArrayList;
import java.util.List;

@SearchIndexable
public class NetworkTrafficSettings extends DashboardFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String TAG = "NetworkTrafficSettings";

    private DropDownPreference mNetTrafficUnitType;

    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.cardinalery_network_traffic;
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        final ContentResolver resolver = getActivity().getContentResolver();

        mNetTrafficUnitType = findPreference(Settings.System.NETWORK_TRAFFIC_UNIT_TYPE);
        mNetTrafficUnitType.setOnPreferenceChangeListener(this);
        int units = Settings.System.getInt(resolver,
                Settings.System.NETWORK_TRAFFIC_UNIT_TYPE, /* Bytes */ 0);
        mNetTrafficUnitType.setValue(String.valueOf(units));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ContentResolver resolver = getActivity().getContentResolver();
        if (preference == mNetTrafficUnitType) {
            int unitType = Integer.valueOf((String) newValue);
            Settings.System.putInt(getActivity().getContentResolver(),
                    Settings.System.NETWORK_TRAFFIC_UNIT_TYPE, unitType);
            return true;
        }
        return false;
    }

    @Override
    public int getMetricsCategory() {
        return MetricsEvent.CARDINAL;
    }

    @Override
    protected String getLogTag() {
        return TAG;
    }

    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.cardinalery_network_traffic);
}
