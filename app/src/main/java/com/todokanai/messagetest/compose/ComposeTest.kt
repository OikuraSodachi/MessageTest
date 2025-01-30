package com.todokanai.messagetest.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.todokanai.messagetest.compose.group.MessagePart
import com.todokanai.messagetest.compose.group.PreferencesPart

@Composable
fun MainScreen(
    message:String,
    soundOption:List<String>,
    notiBarOption:List<String>,
    setSoundOption:(Boolean)->Unit,
    setNotiOption:(Boolean)->Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        MessagePart(
            message = message,
            sendButton = {}
        )

        PreferencesPart(
            soundOption,
            notiBarOption,
            {setSoundOption(it)},
            {setNotiOption(it)}
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview(){
    MainScreen(
        message = "test1",
        listOf("true","false"),
        listOf("1","2"),
        {},
        {},
        modifier = Modifier
            .background(Color.Blue)
    )
}