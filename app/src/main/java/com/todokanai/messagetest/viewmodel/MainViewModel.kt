package com.todokanai.messagetest.viewmodel

import androidx.lifecycle.ViewModel
import com.todokanai.messagetest.Model
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val model: Model):ViewModel() {

    val receivedText = model.receivedText

    fun send(value:String){
        println("input: $value")
        model.sendString(value)
    }
}