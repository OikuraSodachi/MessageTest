package com.todokanai.messagetest.components

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FCMService : FirebaseMessagingService() {

 //   @Inject
   // lateinit var notification: Notifications

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        println("onNewToken: ${token}")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val content = message.data
        println("onMessageReceived: $content")
     //   notification.postNotification(this,"backgroundTest",content.toString())
    }
}