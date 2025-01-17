package com.todokanai.messagetest.interfaces

import com.google.firebase.database.DataSnapshot

interface FcmMessaging {

    fun send(value:String)

    fun receive(value:DataSnapshot)
}