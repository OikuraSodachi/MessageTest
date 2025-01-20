package com.todokanai.messagetest.di

import androidx.core.app.NotificationManagerCompat
import com.todokanai.messagetest.notifications.Notifications
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
    fun provideNotifications(notificationManager: NotificationManagerCompat): Notifications {
        return Notifications(notificationManager)
    }
}