package com.todokanai.messagetest.components

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.google.firebase.Firebase
import com.google.firebase.database.database
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
                binding.recieved.text = it
            }
        }
        binding.sendBtn.setOnClickListener {
            viewModel.send(binding.inputText.text.toString())
        }



        setContentView(binding.root)
    }
}