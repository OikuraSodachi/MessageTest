package com.todokanai.messagetest.viewmodel

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun sendBtn(value: String) {
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

    fun permission(activity: AppCompatActivity){
        requestPermission_td(activity, arrayOf(Manifest.permission.POST_NOTIFICATIONS),{})
    }

    val disableSoundOption = listOf(true,false)
    val disableNotibarOption =listOf(true,false)

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


    val temp = test()
    fun test(){
        viewModelScope.launch {
            notifications.run{
                soundTest()
            }
        }
        viewModelScope.launch {
            notifications.notiBarTest()
        }
    }
}