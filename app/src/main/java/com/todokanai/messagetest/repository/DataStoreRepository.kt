package com.todokanai.messagetest.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.todokanai.messagetest.abstracts.MyDataStore
import javax.inject.Singleton

@Singleton
class DataStoreRepository(appContext:Context):MyDataStore(appContext) {

    companion object{
        private val DATASTORE_DISABLE_SOUND = booleanPreferencesKey("datastore_disable_sound")
        private val DATASTORE_DISABLE_VIBRATION = booleanPreferencesKey("datastore_disable_vibration")
        private val DATASTORE_DISABLE_NOTIFICATION_BAR = booleanPreferencesKey("datastore_disable_notification_bar")
    }

    suspend fun saveDisableSound(value:Boolean) = DATASTORE_DISABLE_SOUND.save(value)
    suspend fun getDisableSound() = DATASTORE_DISABLE_SOUND.value()
    val disableSoundFlow = DATASTORE_DISABLE_SOUND.flow()

    suspend fun saveDisableNotificationBar(value: Boolean) = DATASTORE_DISABLE_NOTIFICATION_BAR.save(value)
    suspend fun getDisableNotificationBar() = DATASTORE_DISABLE_NOTIFICATION_BAR.value()
    val disableNotificationBarFlow = DATASTORE_DISABLE_NOTIFICATION_BAR.flow()
}