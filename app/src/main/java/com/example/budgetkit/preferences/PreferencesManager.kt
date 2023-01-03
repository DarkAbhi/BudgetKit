package com.example.budgetkit.preferences

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class PreferencesManager(context: Context) {

    private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private var preferences = EncryptedSharedPreferences.create(
        "USER_PRIVATE",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    companion object {
        const val LAST_SYNC_TIME = "last_sync_time"
    }

    var lastSyncTime: Long
        get() = preferences.getLong(LAST_SYNC_TIME, 0)
        set(value) = preferences.edit().putLong(LAST_SYNC_TIME, value).apply()

}