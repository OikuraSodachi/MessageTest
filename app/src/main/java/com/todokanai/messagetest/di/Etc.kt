package com.todokanai.messagetest.di

import android.app.NotificationChannel
import android.app.NotificationManager
import com.todokanai.messagetest.Constants
import com.todokanai.messagetest.notifications.NotificationsNew
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class Etc {

    @Provides
    @Singleton
    fun provideNotificationsNew(notificationManager: NotificationManager):NotificationsNew{
        val channel = NotificationChannel( Constants.NOTIFICATION_CHANNEL_NAME, Constants.NOTIFICATION_CHANNEL_NAME, Constants.CHANNEL_IMPORTANCE)

        return NotificationsNew(notificationManager,channel).apply {
            createNotificationChannel()
        }
    }
}