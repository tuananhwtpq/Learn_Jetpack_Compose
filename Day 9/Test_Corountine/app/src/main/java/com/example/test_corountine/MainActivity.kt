package com.example.test_corountine

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.test_corountine.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tranferDataBtn.setOnClickListener {
            loadDataSync()
        }

    }

    private fun loadDataSync(){
        lifecycleScope.launch {
            binding.resultText.text = "Đang tải dữ liệu..."
            delay(3000)
            binding.resultText.text = "Tải dữ liệu hoàn tất!"
        }
    }
}