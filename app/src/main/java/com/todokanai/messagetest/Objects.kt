package com.todokanai.messagetest

import android.app.NotificationChannel
import android.app.NotificationManager

object Objects {

    val testChannel = NotificationChannel(
        Constants.CHANNEL_ID,
        Constants.NOTIFICATION_CHANNEL_NAME,
        NotificationManager.IMPORTANCE_DEFAULT
    )
}