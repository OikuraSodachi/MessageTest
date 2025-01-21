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
            permission(this@MainActivity)
            receivedText.asLiveData().observe(this@MainActivity){
                notiTest(it)
                binding.recieved.text = "Message: $it"
            }
            addValueListener()
        }
        binding.run{
            disableSound.run{
                val tempAdapter = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,viewModel.disableSoundOption)
                adapter = tempAdapter
                onItemSelectedListener = SpinnerListener { position ->
                    tempAdapter.getItem(position)?.let {
                        viewModel.soundOption(it)
                    }
                }
            }
            disableNotiBar.run {
                val tempAdapter = ArrayAdapter(this@MainActivity,android.R.layout.simple_spinner_dropdown_item,viewModel.disableNotibarOption)
                adapter = tempAdapter
                onItemSelectedListener = SpinnerListener { position ->
                    tempAdapter.getItem(position)?.let {
                        viewModel.notibarOption(it)
                    }
                }
            }

            sendBtn.setOnClickListener {
                viewModel.sendBtn(binding.inputText.text.toString())
            }
        }
        setContentView(binding.root)
    }
}