package com.example.stopwatchapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.stopwatchapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var timeSecond = 0
    private var isRunning = false

    // Dùng handler và runnable
//    private val handler = Handler(Looper.getMainLooper())
//    private val runnable = object : Runnable {
//        override fun run() {
//            timeSecond++
//            val hours = timeSecond / 3600
//            val minutes = (timeSecond % 3600) / 60
//            val seconds = timeSecond % 60
//
//            val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
//            binding.timeText.text = time
//
//            handler.postDelayed(this, 1000)
//        }
//
//    }
    //Dùng Corountine
    private var timeJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            startTimer()
        }

        binding.stopBtn.setOnClickListener {
            stopTimer()
        }

        binding.resetBtn.setOnClickListener {
            resetTimer()
        }

    }

    private fun startTimer(){
        if (!isRunning){
            timeJob = lifecycleScope.launch {
                while (true){
                    delay(1000)
                    timeSecond++

                    val hours = timeSecond / 3600
                    val minutes = (timeSecond % 3600) / 60
                    val seconds = timeSecond % 60

                    val time = String.format("%02d:%02d:%02d", hours, minutes, seconds)
                    binding.timeText.text = time
                }
            }

            isRunning = true
            binding.startBtn.isEnabled = false
            binding.stopBtn.isEnabled = true
            binding.resetBtn.isEnabled = true

        }
    }

    private fun stopTimer(){
        if (isRunning){

            timeJob?.cancel()
            timeJob = null
            isRunning = false

            binding.stopBtn.isEnabled = false
            binding.startBtn.isEnabled = true
            binding.startBtn.text = "Resume"
            binding.resetBtn.isEnabled = true
        }
    }

    private fun resetTimer(){
        stopTimer()
        timeSecond = 0

        binding.startBtn.isEnabled = true
        binding.stopBtn.isEnabled = false
        binding.startBtn.text = "Start"
        binding.timeText.text = "00:00:00"
    }



}