package com.todokanai.messagetest.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.todokanai.messagetest.compose.group.MessagePart
import com.todokanai.messagetest.compose.group.PreferencesPart

@Composable
fun MainScreen(
    message:String,
    soundOption:List<String>,
    notiBarOption:List<String>,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .background(Color.Red)
                .padding(vertical = 100.dp)
        ) {
            MessagePart(
                message = message,
                sendButton = {}
            )
        }

        Box(
            modifier = Modifier
                .padding(vertical = 50.dp)
        ) {
            PreferencesPart(
                soundOption,
                notiBarOption,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }

}

@Preview
@Composable
private fun MainScreenPreview(){
    Surface{
        MainScreen(
            message = "test1",
            listOf("1","2"),
            listOf("1","2")
        )
    }
}