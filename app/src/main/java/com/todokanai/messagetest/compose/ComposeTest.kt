package com.todokanai.messagetest.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.todokanai.messagetest.compose.group.MessagePart

@Composable
fun MainScreen(
    message:String,
    modifier: Modifier = Modifier
){
    Column(){
        MessagePart(message = message)

    }

}

@Preview
@Composable
private fun MainScreenPreview(){
    Surface{
        MainScreen(
            message = "test1"
        )
    }
}