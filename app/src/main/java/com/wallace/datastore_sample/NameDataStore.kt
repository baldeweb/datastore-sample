package com.wallace.datastore_sample

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

class NameDataStore(
    context: Context,
    prefsKey: Preferences.Key<String> = stringPreferencesKey("name_preferences")
) : DataStoreManager<String>(context, prefsKey)