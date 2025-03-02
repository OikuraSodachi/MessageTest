package com.todokanai.messagetest.components

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.todokanai.messagetest.compose.MainScreen
import com.todokanai.messagetest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) // Todo: Release build 에서는 제거할 것
        viewModel.addValueListener()
        setContent{
            val messageString = viewModel.receivedText.collectAsStateWithLifecycle()
            MainScreen(
                message = messageString.value,
                soundOption = viewModel.disableSoundOption.map { it.toString() },
                notiBarOption = viewModel.disableNotibarOption.map{it.toString()},
                setSoundOption = {viewModel.soundOption(it=="true")},
                setNotiOption = {viewModel.notibarOption(it=="true")},
                sound = viewModel.soundOption,
                noti = viewModel.notiOption,
                onSend = {viewModel.sendBtn(this,it)}
            )
        }
    }
}