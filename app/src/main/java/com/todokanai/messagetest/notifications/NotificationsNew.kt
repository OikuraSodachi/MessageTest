package com.todokanai.messagetest.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.todokanai.messagetest.R

class NotificationsNew(
    private val notificationManager: NotificationManager,
    private val channel: NotificationChannel
){

    fun displayNotification(context: Context, title:String, contentText:String, notificationId:Int = 45) {
        val notification = Notification.Builder(context, channel.id)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(contentText)
            .build()

        notificationManager.notify(notificationId, notification)
    }

    fun createNotificationChannel() {
        notificationManager.createNotificationChannel(channel)
    }
}