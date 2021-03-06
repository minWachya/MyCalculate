package com.example.mycalculate.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycalculate.close.CloseActivity
import com.example.mycalculate.close_calculate.CalculateActivity
import com.example.mycalculate.databinding.ActivityMainBinding
import com.example.mycalculate.rotate_calculate.RotateCalculateActivity

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // '마감 정산' 버튼 누르면 마감 정산 화면으로 이동
        binding.btnCalculate.setOnClickListener {
            val intent = Intent(this@MainActivity, CalculateActivity::class.java)
            startActivity(intent)
        }

        // '교대 정산' 버튼 누르면 교대 정산 화면으로 이동
        binding.btnRotateCalculate.setOnClickListener {
            val intent = Intent(this@MainActivity, RotateCalculateActivity::class.java)
            startActivity(intent)
        }

        // '마감' 버튼 누르면 마감 화면으로 이동
        binding.btnClose.setOnClickListener {
            val intent = Intent(this@MainActivity, CloseActivity::class.java)
            startActivity(intent)
        }

    }
}