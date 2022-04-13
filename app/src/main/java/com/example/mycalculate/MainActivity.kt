package com.example.mycalculate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycalculate.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // '정산' 버튼 누르면 정산 화면으로 이동
        binding.btnCalculate.setOnClickListener {
            val intent = Intent(this@MainActivity, CalculateActivity::class.java)
            startActivity(intent)
        }

        // '마감' 버튼 누르면 마감 화면으로 이동
        binding.btnClose.setOnClickListener {
            val intent = Intent(this@MainActivity, CloseActivity::class.java)
            startActivity(intent)
        }

    }
}