package com.todokanai.messagetest.components

import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.todokanai.messagetest.notifications.Notifications
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/** FirebaseMessagingService 는 startService 할 필요 없음 **/
@AndroidEntryPoint
class FCMService : FirebaseMessagingService() {

    @Inject
    lateinit var notification: Notifications

    @Inject
    lateinit var fcm:FirebaseMessaging

    override fun onCreate() {
        super.onCreate()
        println("onCreate")
        fcm.token.addOnCompleteListener{ task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }
            // Get new FCM registration token
            val token = task.result
            println(token)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("onNewToken: ${token}")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val content = message.data
        println("onMessageReceived: $content")
        notification.displayNotification(this,"backgroundTest",content.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy: FCMService")
    }
}