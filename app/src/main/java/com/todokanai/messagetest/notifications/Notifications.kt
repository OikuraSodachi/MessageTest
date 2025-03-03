package com.todokanai.messagetest.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import com.todokanai.messagetest.Constants
import com.todokanai.messagetest.R
import com.todokanai.messagetest.abstracts.BaseNotification
import com.todokanai.messagetest.repository.DataStoreRepository

class Notifications(
    val notificationManager:NotificationManager,
    val dsRepo:DataStoreRepository
): BaseNotification() {

    companion object{

        /** Makes a sound and appears as a heads-up notification **/
        private const val URGENT = NotificationManager.IMPORTANCE_HIGH

        /** Makes a sound **/
        private const val HIGH = NotificationManager.IMPORTANCE_DEFAULT

        /** Makes no sound **/
        private const val MEDIUM = NotificationManager.IMPORTANCE_LOW

        /** Makes no sound and doesn't appear in the status bar **/
        private const val LOW = NotificationManager.IMPORTANCE_MIN

        /** Makes no sound and doesn't appear in the status bar or shade **/
        private const val NONE = NotificationManager.IMPORTANCE_NONE

        /** the notification's full content shows on the lock screen **/
        private const val PUBLIC = NotificationCompat.VISIBILITY_PUBLIC

        /** no part of the notification shows on the lock screen **/
        private const val SECRET = NotificationCompat.VISIBILITY_SECRET

        /** only basic information, such as the notification's icon and the content title, shows on the lock screen. The notification's full content doesn't show **/
        private const val PRIVATE = NotificationCompat.VISIBILITY_PRIVATE
    }

    private val channelId : Int = 2
    val notificationChannel by lazy {
        NotificationChannel(
            Constants.CHANNEL_ID,
            Constants.NOTIFICATION_CHANNEL_NAME,
            HIGH            //  알림의 중요도
        )
    }

    override fun appIcon(): Int {
        return R.drawable.ic_launcher_foreground
    }

    override fun postNotification(context: Context,title: String, contentText: String) {
        println("postNotification")
        val temp = Notification.Builder(context)
            .setContentTitle(title)
            .setContentText(contentText)
            .setSmallIcon(appIcon())
            .build()
        notificationManager.notify(channelId,temp)
    }

    suspend fun notiBarTest(){
        dsRepo.disableNotificationBarFlow.collect{
            disableNotificationBar(it)
        }
    }
    suspend fun soundTest(){
        dsRepo.disableSoundFlow.collect {
            disableSound(it)
        }
    }

    override fun disableNotificationBar(value: Boolean) {
        println("disableBar: $value")
    }

    override fun disableSound(value: Boolean) {
        println("disableSound: $value")
    }

    override fun createNotificationChannel() {
        notificationManager.createNotificationChannel(notificationChannel)
    }
}