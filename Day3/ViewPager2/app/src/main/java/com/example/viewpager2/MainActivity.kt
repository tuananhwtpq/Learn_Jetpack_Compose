package com.example.viewpager2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.viewpager2.adapter.ViewPagerAdapter
import com.example.viewpager2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewPagerAdapter = ViewPagerAdapter()

    private val itemList = listOf(
        "Lorem cabahsbdhabshdbab ahsjhdjkahjsdhash dahsdjhajshdj asjd ajhsd ashd a sdh asdkjasd",
        "asdhjashdj asjdhajshdjahsjdhwjhaud sai  ajsdjhjha sd ajshdjahs djahs dh ahsdh ajshd asd",
        "ajhsjdhjaqshj dhiiawjdhjhagshgdha sd asdjahjsdhj asyywjahjdhjhs ahdasgd asnd"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager2.adapter = viewPagerAdapter
        viewPagerAdapter.submitData(itemList)
    }
}