package com.todokanai.messagetest.viewmodel

import android.Manifest
import android.app.Activity
import android.app.NotificationManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import com.todokanai.messagetest.R
import com.todokanai.messagetest.TestListener
import com.todokanai.messagetest.di.MyApplication.Companion.appContext
import com.todokanai.messagetest.notifications.Notifications
import com.todokanai.messagetest.repository.DataStoreRepository
import com.todokanai.messagetest.requestPermission_td
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    firebase:FirebaseDatabase,
    private val notifications: Notifications,
    private val dsRepo:DataStoreRepository
):ViewModel() {

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

    val importanceList = listOf(
        NotificationManager.IMPORTANCE_HIGH,
        NotificationManager.IMPORTANCE_DEFAULT,
        NotificationManager.IMPORTANCE_LOW,
        NotificationManager.IMPORTANCE_MIN,
        NotificationManager.IMPORTANCE_NONE
        ).reversed()

    val importanceFlow = dsRepo.importanceFlow
    fun importance(importance:Int){
        CoroutineScope(Dispatchers.IO).launch {
            if(importance==0){
                dsRepo.saveDisableSound(true)
            }else{
                dsRepo.saveDisableSound(false)
            }
            //dsRepo.saveImportance(importance)
        }
    }
}