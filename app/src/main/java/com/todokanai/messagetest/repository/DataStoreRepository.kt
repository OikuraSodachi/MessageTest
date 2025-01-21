package com.todokanai.messagetest.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import com.todokanai.messagetest.abstracts.MyDataStore
import javax.inject.Singleton

@Singleton
class DataStoreRepository(appContext:Context):MyDataStore(appContext) {

    companion object{
        private val DATASTORE_NOTIFICATION_IMPORTANCE = intPreferencesKey("datastore_notification_importance")
        private val DATASTORE_DISABLE_SOUND = booleanPreferencesKey("datastore_disable_sound")
        private val DATASTORE_DISABLE_VIBRATION = booleanPreferencesKey("datastore_disable_vibration")
        private val DATASTORE_DISABLE_NOTIFICATION_BAR = booleanPreferencesKey("datastore_disable_notification_bar")
    }

    suspend fun saveImportance(value:Int) = DATASTORE_NOTIFICATION_IMPORTANCE.save(value)
    suspend fun getImportance() = DATASTORE_NOTIFICATION_IMPORTANCE.value()
    val importanceFlow = DATASTORE_NOTIFICATION_IMPORTANCE.flow()

    suspend fun saveDisableSound(value:Boolean) = DATASTORE_DISABLE_SOUND.save(value)
    suspend fun getDisableSound() = DATASTORE_DISABLE_SOUND.value()
    val disableSoundFlow = DATASTORE_DISABLE_SOUND.flow()

    suspend fun saveDisableNotificationBar(value: Boolean) = DATASTORE_DISABLE_NOTIFICATION_BAR.save(value)
    suspend fun getDisableNotificationBar() = DATASTORE_DISABLE_NOTIFICATION_BAR.value()
    val disableNotificationBarFlow = DATASTORE_DISABLE_NOTIFICATION_BAR.flow()
}