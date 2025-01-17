package com.todokanai.messagetest

import com.google.firebase.Firebase
import com.google.firebase.database.database

object Objects {

    val database = Firebase.database
    val myRef = database.getReference("message")

}