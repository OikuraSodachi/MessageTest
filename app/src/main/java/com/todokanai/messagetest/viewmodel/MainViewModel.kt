package com.todokanai.messagetest.viewmodel

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.todokanai.messagetest.Constants
import com.todokanai.messagetest.R
import com.todokanai.messagetest.TestListener
import com.todokanai.messagetest.di.MyApplication.Companion.appContext
import com.todokanai.messagetest.notifications.Notifications
import com.todokanai.messagetest.requestPermission_td
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(firebase:FirebaseDatabase):ViewModel() {

    private val myRef = firebase.getReference(appContext.getString(R.string.firebase_ref))
    private val noti =
        Notifications(
            appContext,
            NotificationChannel(
                Constants.CHANNEL_ID,
                Constants.NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT

            )
        )

    private val _receivedText = MutableStateFlow("no text")
    val receivedText : StateFlow<String>
        get() = _receivedText

    fun sendString(value: String) {
        myRef.setValue(value)
    }

    fun addValueListener(){
        myRef.addValueEventListener(
            TestListener(
                callback = {
                    _receivedText.value = it.value.toString()
                }
            )
        )
    }

    fun notiTest(value:String){
        noti.post(value)
    }

    fun permission(activity:Activity){
        requestPermission_td(
            activity,
            permissions = arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            {}
            )
    }
}