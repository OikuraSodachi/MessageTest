package com.todokanai.messagetest.compose.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MessagePart(
    message:String,
    sendButton:(String)->Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
    ) {

        var text by rememberSaveable { mutableStateOf("Text") }
        TextField(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
            },
            label = {Text("Message")}
        )

        Spacer(
            modifier = Modifier
                .height(80.dp)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(),
            text = message
        )

        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Button(
            onClick = { sendButton(text) },
            content = {Text("Send")},
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
        )
    }
}

@Preview
@Composable
private fun MessagePartPreview(){
    Surface {
        MessagePart(
            message = "testMessage",
            sendButton = {}
        )
    }
}