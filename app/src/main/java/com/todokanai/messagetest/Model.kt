package com.todokanai.messagetest

import com.google.firebase.Firebase
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Model {

    private val _receivedText = MutableStateFlow("no text")
    val receivedText : StateFlow<String>
        get() = _receivedText

    fun sendString(value: String) {
        val database = Firebase.database
        val myRef = database.getReference("message")
        myRef.setValue(value)
    }

    private fun setReceivedText(value: String){
        _receivedText.value = value
    }
}