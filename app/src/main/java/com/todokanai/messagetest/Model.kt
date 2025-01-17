package com.todokanai.messagetest

import com.todokanai.messagetest.Objects.myRef
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Model {

    private val _receivedText = MutableStateFlow("no text")
    val receivedText : StateFlow<String>
        get() = _receivedText

    fun sendString(value: String) {
        myRef.setValue(value)
    }

    fun setReceivedText(value: String){
        _receivedText.value = value
    }
}