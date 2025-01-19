package com.todokanai.messagetest.viewmodel

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.todokanai.messagetest.Constants
import com.todokanai.messagetest.R
import com.todokanai.messagetest.TestListener
import com.todokanai.messagetest.di.MyApplication.Companion.appContext
import com.todokanai.messagetest.notifications.Notifications
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
        println("test: $value")
        noti.post(value)
    }

    fun test(activity: AppCompatActivity){
        requestPermission_td(activity, arrayOf(Manifest.permission.POST_NOTIFICATIONS),{})
    }

    fun requestPermission_td(
        activity: Activity,
        permissions: Array<String>,
        permissionNotice:()->Unit,
        requestCode:Int = 111
    ){
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                permissions.first()
            )
        ) {
            permissionNotice()
        } else {
            ActivityCompat.requestPermissions(
                activity,
                permissions,
                requestCode
            )
        }
    }
}