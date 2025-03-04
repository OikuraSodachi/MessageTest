package com.todokanai.messagetest

import android.app.NotificationManager

object Constants {
    const val CHANNEL_ID = "Todokanai_MessageTest_ID"
    /** use this for channelId parameter of [android.app.Notification.Builder] **/
    const val NOTIFICATION_CHANNEL_NAME : String = "Todokanai_MessageTest_Channel"

    const val CHANNEL_IMPORTANCE : Int = NotificationManager.IMPORTANCE_DEFAULT

}