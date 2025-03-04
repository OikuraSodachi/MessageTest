package com.todokanai.messagetest.viewmodel

import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.todokanai.messagetest.R
import com.todokanai.messagetest.TestListener
import com.todokanai.messagetest.di.MyApplication.Companion.appContext
import com.todokanai.messagetest.notifications.NotificationsNew
import com.todokanai.messagetest.repository.DataStoreRepository
import com.todokanai.messagetest.requestPermission_td
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    firebase:FirebaseDatabase,
    private val dsRepo:DataStoreRepository,
    private val notiTest:NotificationsNew
):ViewModel() {

    val myRef = firebase.getReference(appContext.getString(R.string.firebase_ref))

    private val _receivedText = MutableStateFlow("no text")
    val receivedText : StateFlow<String>
        get() = _receivedText

    fun sendBtn(context: Context, value: String) {
        myRef.setValue(value)
        notiTest.displayNotification(
            context = context,
            title = value,
            contentText = value
        )
    }

    fun addValueListener(){
        myRef.addValueEventListener(
            TestListener(
                callback = {
                    val result = it.value.toString()
                    println("addValueListener: $result")
                    _receivedText.value = result
                }
            )
        )
    }

    fun notiTest(context: Context,value:String){
       // notifications.postNotification(context = context, title = "Title",value)
    }
    fun permission(activity: Activity){
        requestPermission_td(activity, arrayOf(Manifest.permission.POST_NOTIFICATIONS),{})
    }

    val disableSoundOption = listOf(true,false)
    val disableNotibarOption =listOf(true,false)

    val soundOption = dsRepo.disableSoundFlow.map { it.toString() }
    val notiOption = dsRepo.disableNotificationBarFlow.map{ it.toString() }

    fun soundOption(value:Boolean){
        CoroutineScope(Dispatchers.IO).launch {
            dsRepo.saveDisableSound(value)
        }
    }

    fun notibarOption(value: Boolean){
        CoroutineScope(Dispatchers.IO).launch {
            dsRepo.saveDisableNotificationBar(value)
        }
    }
    //------------------
}