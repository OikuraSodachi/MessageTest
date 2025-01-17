package com.todokanai.messagetest

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Model {

    private val _receivedText = MutableStateFlow("no text")
    val receivedText : StateFlow<String>
        get() = _receivedText

    fun sendString(value: String) {
        setReceivedText(value)
    }

    private fun setReceivedText(value: String){
        _receivedText.value = value
    }
}