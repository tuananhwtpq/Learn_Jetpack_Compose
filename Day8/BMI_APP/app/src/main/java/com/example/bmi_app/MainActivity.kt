package com.example.bmi_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmi_app.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            bmiCalculate()
        }

    }

    private fun bmiCalculate(){

        val height = binding.heightEdit.text.toString().toFloatOrNull()
        val weight = binding.weightEdit.text.toString().toFloatOrNull()

        if (weight != null && height != null){
            val bmi = weight / (height/100).pow(2)
            val bmiResult = String.format("%.2f", bmi)

            val bmiCategory = when {
                bmi < 18.5 -> "Under weight"
                bmi < 25 -> "Normal weight"
                bmi > 30 -> "Over weight"
                else -> "Obese"
            }

            binding.resultText.text = "BMI: $bmiResult\nCategoty: $bmiCategory"
        } else {
            binding.resultText.text = "Invalid input"
        }
    }

}