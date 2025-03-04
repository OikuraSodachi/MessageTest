package com.todokanai.messagetest.di

import android.app.NotificationManager
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
        return NotificationsNew(notificationManager).apply {
            createNotificationChannel()
        }
    }
}