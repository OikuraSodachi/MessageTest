package com.todokanai.messagetest.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.todokanai.messagetest.compose.group.MessagePart
import com.todokanai.messagetest.compose.group.PreferencesPart

@Composable
fun MainScreen(
    message:String,
    soundOption:List<String>,
    notiBarOption:List<String>
){

    val temp = Modifier
        .wrapContentSize()

    Column(
        modifier = temp,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MessagePart(
            message = message,
            sendButton = {}
        )

        PreferencesPart(
            soundOption,
            notiBarOption
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview(){
    Surface(){
        MainScreen(
            message = "test1",
            listOf("1","2"),
            listOf("1","2")
        )
    }
}