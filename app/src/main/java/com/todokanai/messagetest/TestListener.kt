package com.todokanai.messagetest

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TestListener(val callback:(snapshot:DataSnapshot)->Unit):ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        callback(snapshot)
    }

    override fun onCancelled(error: DatabaseError) {
        println("error_td")
    }
}