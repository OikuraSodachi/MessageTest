package com.todokanai.messagetest.di

import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat
import com.todokanai.messagetest.notifications.Notifications
import com.todokanai.messagetest.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Etc {

    @Singleton
    @Provides
    fun provideNotifications(
        notificationManager: NotificationManagerCompat,
        dataStoreRepository: DataStoreRepository
    ): Notifications {
        return Notifications(notificationManager,dataStoreRepository).apply {
            createNotificationChannel()
        }
    }
}