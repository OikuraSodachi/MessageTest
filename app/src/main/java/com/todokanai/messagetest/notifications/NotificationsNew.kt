package com.todokanai.messagetest.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import com.todokanai.messagetest.Constants
import com.todokanai.messagetest.R
import com.todokanai.messagetest.di.MyApplication.Companion.appContext

class NotificationsNew(
    private val notificationManager: NotificationManager
){
    private val channelId = Constants.NOTIFICATION_CHANNEL_NAME

    fun displayNotification(title:String,contentText:String,notificationId:Int = 45) {
        val notification = Notification.Builder(appContext, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(contentText)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    fun createNotificationChannel(name: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT // set importance
        val channel = NotificationChannel(channelId , name, importance)
        // Register the channel with the system
        notificationManager.createNotificationChannel(channel)
    }
}