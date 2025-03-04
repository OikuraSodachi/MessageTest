package com.todokanai.messagetest.di

import android.app.NotificationManager
import com.todokanai.messagetest.Objects
import com.todokanai.messagetest.R
import com.todokanai.messagetest.notifications.Notifications
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
    fun provideNotifications(notificationManager: NotificationManager):Notifications{
        return Notifications(
            notificationManager,
            Objects.testChannel,
            R.drawable.ic_launcher_foreground
        ).apply {
            createNotificationChannel()
        }
    }
}