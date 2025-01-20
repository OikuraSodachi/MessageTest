package com.todokanai.messagetest.viewmodel

import android.Manifest
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
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
class MainViewModel @Inject constructor(firebase:FirebaseDatabase,private val notifications: Notifications):ViewModel() {

    private val myRef = firebase.getReference(appContext.getString(R.string.firebase_ref))

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
        notifications.postNotification(title = "Title",value)
    }

    fun test(activity: AppCompatActivity){
        requestPermission_td(activity, arrayOf(Manifest.permission.POST_NOTIFICATIONS),{})
    }
}