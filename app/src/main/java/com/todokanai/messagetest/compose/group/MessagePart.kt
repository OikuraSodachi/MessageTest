package com.todokanai.messagetest.compose.group

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var text by rememberSaveable { mutableStateOf("Text") }
        TextField(
            modifier = modifier,
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
            modifier = Modifier,
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