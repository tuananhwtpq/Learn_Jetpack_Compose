package com.example.quizzapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizzapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf(
        "What is the built-in database in Android Studio?",
        "What is the full form APK in Android Development?",
        "In which year, first android was released by Google?"
    )

    private val options = arrayOf(
        arrayOf("MySQL", "SQLite", "Firebase"),
        arrayOf(
            "Application Programming Interface",
            "Android Programming Interface",
            "Android Package Information"
        ),
        arrayOf("2010", "2008", "2006"),
    )

    private val correctAnswers = arrayOf(1, 0, 2)
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.btn1.setOnClickListener { checkAnswers(0) }
        binding.btn2.setOnClickListener { checkAnswers(1) }
        binding.btn3.setOnClickListener { checkAnswers(2) }
        binding.btnReset.setOnClickListener { resetQuizz() }
    }

    private fun correctButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.btn1.setBackgroundColor(Color.GREEN)
            1 -> binding.btn2.setBackgroundColor(Color.GREEN)
            2 -> binding.btn3.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.btn1.setBackgroundColor(Color.RED)
            1 -> binding.btn2.setBackgroundColor(Color.RED)
            2 -> binding.btn3.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors() {
        binding.btn1.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.btn2.setBackgroundColor(Color.rgb(50, 59, 96))
        binding.btn3.setBackgroundColor(Color.rgb(50, 59, 96))
    }

    //Kết thúc thì mới được nhấn nút Reset
    private fun showResult() {
        Toast.makeText(this, "Your score: $score out of ${questions.size}", Toast.LENGTH_LONG)
            .show()
        binding.btnReset.isEnabled = true
    }

    private fun displayQuestion() {
        binding.question.text = questions[currentQuestionIndex]
        binding.btn1.text = options[currentQuestionIndex][0]
        binding.btn2.text = options[currentQuestionIndex][1]
        binding.btn3.text = options[currentQuestionIndex][2]
        resetButtonColors()
    }

    private fun checkAnswers(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]
        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            binding.question.postDelayed({ displayQuestion() }, 3000)

        } else {
            showResult()
        }
    }

    private fun resetQuizz(){
        currentQuestionIndex = 0
        score
        displayQuestion()
        binding.btnReset.isEnabled = false
    }
}