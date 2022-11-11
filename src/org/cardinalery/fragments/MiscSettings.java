/*
 *  Copyright (C) 2023 CardinalOS
 *  SPDX-License-Identifier: Apache-2.0
 */

package org.cardinalery.fragments;

import com.android.internal.logging.nano.MetricsProto;

import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.search.BaseSearchIndexProvider;
import com.android.settingslib.search.SearchIndexable;

@SearchIndexable(forTarget = SearchIndexable.ALL & ~SearchIndexable.ARC)
public class MiscSettings extends DashboardFragment {

    @Override
    protected int getPreferenceScreenResId() {
        return R.xml.cardinalery_misc;
    }

    @Override
    protected String getLogTag() {
        return "MiscSettings";
    }

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.CARDINAL;
    }

    public static final BaseSearchIndexProvider SEARCH_INDEX_DATA_PROVIDER =
            new BaseSearchIndexProvider(R.xml.cardinalery_misc);
}
