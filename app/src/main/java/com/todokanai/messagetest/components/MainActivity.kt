package com.todokanai.messagetest.components

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.asLiveData
import com.todokanai.messagetest.databinding.ActivityMainBinding
import com.todokanai.messagetest.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.run{
            receivedText.asLiveData().observe(this@MainActivity){
                notiTest(it)
                binding.recieved.text = "Message: $it"
            }
            addValueListener()
        }
        binding.run{
            sendBtn.setOnClickListener {
                viewModel.sendString(binding.inputText.text.toString())
            }
            testBtn.setOnClickListener {
                viewModel.test(this@MainActivity)
            }
        }

        setContentView(binding.root)
    }
}