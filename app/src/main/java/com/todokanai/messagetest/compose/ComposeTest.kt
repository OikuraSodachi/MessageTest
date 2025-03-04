package com.todokanai.messagetest.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.todokanai.messagetest.compose.group.MessagePart
import com.todokanai.messagetest.compose.group.PreferencesPart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun MainScreen(
    message:String,
    soundOption:List<String>,
    notiBarOption:List<String>,
    setSoundOption:(String)->Unit,
    setNotiOption:(String)->Unit,
    sound: Flow<String>,
    noti:Flow<String>,
    onSend:(String)->Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        MessagePart(
            message = message,
            sendButton = {onSend(it)},
            modifier = Modifier
                .fillMaxWidth()
        )

        PreferencesPart(
            soundOption,
            notiBarOption,
            {setSoundOption(it)},
            {setNotiOption(it)},
            sound = sound,
            noti = noti,
            modifier = Modifier
                .fillMaxWidth()
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
        emptyFlow(),
        emptyFlow(),
        {},
        modifier = Modifier
    )
}