package com.todokanai.messagetest.components

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.todokanai.messagetest.adapters.SpinnerListener
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
            importance.run{
                val tempAdapter = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,viewModel.importanceList)
                adapter = tempAdapter
                onItemSelectedListener = SpinnerListener({ position ->
                    tempAdapter.getItem(position)?.let{
                        viewModel.importance(position)
                    }
                })
            }
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